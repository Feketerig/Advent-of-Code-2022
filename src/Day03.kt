fun main(){

    fun priority(char: Char): Int{
        return if (char < 'a') char - 'A' + 1 + 26 else char - 'a' + 1
    }

    fun findCommonElement(line: String): Int {
        val firstHalf = line.subSequence(0, line.length / 2).toSet()
        val secondHalf = line.subSequence(line.length / 2, line.length).toSet()
        val commonChar = firstHalf.intersect(secondHalf).first()
        return priority(commonChar)
    }

    fun part1(input: List<String>): Int{
        return input.sumOf { findCommonElement(it) }
    }

    fun findCommonInThreeLine(lines: List<String>): Int{
        val commonChar = lines[0].toSet().intersect(lines[1].toSet()).intersect(lines[2].toSet()).first()
        return priority(commonChar)
    }

    fun part2(input: List<String>): Int{
        val lines = mutableListOf<String>()
        var sum = 0
        input.forEachIndexed { index, line ->
            lines.add(line)
            if ((index + 1) % 3 == 0){
                sum += findCommonInThreeLine(lines)
                lines.clear()
            }
        }
        return sum
    }

    val testInput = readInput("Day03_test_input")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03_input")
    println(part1(input))
    println(part2(input))
}