fun main(){

    fun buildFileSystem(input: List<String>): Dir {
        val root = Dir("/", mutableListOf(), null)
        var currentDir = root
        input.drop(1).forEach {line ->
            when(line.substring(0,4)){
                "$ ls" -> {

                }
                "$ cd" -> {
                    val path = line.removePrefix("$ cd ")
                    if (path == ".."){
                        currentDir = currentDir.parent!!
                    }else{
                        currentDir = currentDir.files.find { it.name == path } as Dir
                    }
                }
                "dir " -> {
                    val name = line.removePrefix("dir ")
                    currentDir.files.add(Dir(name, mutableListOf(), currentDir))
                }
                else -> {
                    val (size, name) = line.split(" ")
                    currentDir.files.add(File(name, size.toInt(), currentDir))
                }
            }
        }
        return root
    }

    fun part1(input: List<String>): Int{
        val root = buildFileSystem(input)
        val folders = root.findFolders(100_000)
        return folders.sumOf { it.getSystemSize() }
    }

    fun part2(input: List<String>): Int{
        val root = buildFileSystem(input)
        val totalDisk = 70_000_000
        val usedSpace = root.getSystemSize()
        val freeSpace = totalDisk - usedSpace
        val neededSpace = 30_000_000
        val freeUpSpace = neededSpace - freeSpace
        val folders = root.findFolders(Int.MAX_VALUE)
        return folders.filter { it.getSystemSize() > freeUpSpace }.minOf { it.getSystemSize() }
    }


    val testInput = readInput("Day07_test_input")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07_input")
    println(part1(input))
    println(part2(input))
}

sealed class SystemPart(val name: String, val parent: Dir?){
    abstract fun getSystemSize(): Int
}
open class File(name: String, val size: Int, parent: Dir?): SystemPart(name, parent){
    override fun getSystemSize(): Int = size
}
class Dir(name: String, val files: MutableList<SystemPart>, parent: Dir?): SystemPart(name, parent){
    override fun getSystemSize():Int {
        return files.sumOf { it.getSystemSize() }
    }

    fun findFolders(maxSize: Int): List<Dir> {
        val children = files.filterIsInstance<Dir>().flatMap { it.findFolders(maxSize) }
        return if(getSystemSize() <= maxSize) {
            children + this
        } else {
            children
        }
    }
}