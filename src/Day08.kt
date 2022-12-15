fun main(){

    fun part1(input: List<String>): Int{
        val map = input.map { line ->
            line.map { digit -> digit.digitToInt() }
        }

        val xValues = input.first().indices
        val yValues = input.indices

        val visible = mutableSetOf<Pair<Int, Int>>()

        // rows
        for (y in yValues) {
            var max = -1
            for (x in xValues) {
                val height = map[y][x]
                if (height > max) {
                    visible += (x to y)
                    max = height
                }
            }
        }
        for (y in yValues) {
            var max = -1
            for (x in xValues.reversed()) {
                val height = map[y][x]
                if (height > max) {
                    visible += (x to y)
                    max = height
                }
            }
        }
        for (x in xValues) {
            var max = -1
            for (y in yValues) {
                val height = map[y][x]
                if (height > max) {
                    visible += (x to y)
                    max = height
                }
            }
        }
        for (x in xValues) {
            var max = -1
            for (y in yValues.reversed()) {
                val height = map[y][x]
                if (height > max) {
                    visible += (x to y)
                    max = height
                }
            }
        }

        return visible.size
    }

    fun part2(input: List<String>): Int{
        val xValues = input.first().indices
        val yValues = input.indices

        var maxScenicScore = 0

        for (y in yValues) {
            for (x in xValues) {
                val height = input[y][x]

                val left = input[y].substring(0, x)
                    .indexOfLast { it >= height }
                    .let { if (it == -1) x else x - it }

                val right = input[y].substring(x + 1)
                    .indexOfFirst { it >= height }
                    .let { if (it == -1) xValues.last - x else it + 1 }

                val top = input
                    .take(y)
                    .indexOfLast { it[x] >= height }
                    .let { if (it == -1) y else y - it }

                val bottom = input
                    .drop(y + 1)
                    .indexOfFirst { it[x] >= height }
                    .let { if (it == -1) yValues.last - y else it + 1 }

                val score = left * right * top * bottom

                if (score > maxScenicScore) {
                    maxScenicScore = score
                }
            }
        }

        return maxScenicScore
    }


    val testInput = readInput("Day08_test_input")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08_input")
    println(part1(input))
    println(part2(input))
}