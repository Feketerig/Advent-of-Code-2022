fun main(){

    fun String.unique(): Boolean {
        return toSet().size == length
    }

    fun findMarker(input: String, numberOfDifferent: Int): Int {
        val index = input.windowed(numberOfDifferent).indexOfFirst { it.unique() }
        return index + numberOfDifferent
    }

    fun part1(input: List<String>): Int{
        return findMarker(input.first(),4)
    }

    fun part2(input: List<String>): Int{
        return findMarker(input.first(), 14)
    }


    val testInput = readInput("Day06_test_input")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06_input")
    println(part1(input))
    println(part2(input))
}