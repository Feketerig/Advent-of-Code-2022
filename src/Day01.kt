fun main() {

    fun readCalories(input: List<String>): List<Int>{
        val calories = mutableListOf<Int>()
        var calorie = 0
        input.forEach {line ->
            if (line.isEmpty()){
                calories.add(calorie)
                calorie = 0
            }else{
                calorie += line.toInt()
            }
        }
        calories.add(calorie)
        return calories
    }

    fun part1(input: List<String>): Int {
        val calories = readCalories(input)
        return calories.max()
    }

    fun part2(input: List<String>): Int {
        val calories = readCalories(input)
        return calories.sortedDescending().subList(0,3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test_input")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01_input")
    println(part1(input))
    println(part2(input))
}
