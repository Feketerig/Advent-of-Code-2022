import java.util.InputMismatchException

fun main(){

    data class Move(val count: Int, val from: Int, val to: Int)

    fun parse(input: List<String>): Pair<List<MutableList<Char>>, List<Move>>{
        val emptyLineIndex = input.indexOfFirst { line -> line.isBlank() }

        val numberOfStacks = input[emptyLineIndex - 1].last().digitToInt()
        val crates = List(size = numberOfStacks){ mutableListOf<Char>() }
        input.subList(0, emptyLineIndex - 1).asReversed().forEach { line ->
            line.chunked(4).forEachIndexed { index, content ->
                content[1].takeIf(Char::isLetter)?.let(crates[index]::plusAssign)
            }
        }

        val moves = input.subList(emptyLineIndex + 1, input.size)
            .map {
                val split = it.split(" ")
                Move(split[1].toInt(), split[3].toInt() - 1, split[5].toInt() - 1)
            }

        return Pair(crates, moves)
    }

    fun part1(input: List<String>): String{
        val (crates, moves) = parse(input)

        moves.forEach { move ->
            repeat(move.count) {
                crates[move.to] += crates[move.from].removeLast()
            }
        }

        return crates.map { it.last() }.joinToString("")
    }

    fun part2(input: List<String>): String{
        val (crates, moves) = parse(input)

        moves.forEach { move ->
            val moveObject = crates[move.from].subList(crates[move.from].size - move.count, crates[move.from].size)
            crates[move.to] += moveObject
            for (i in 0 until move.count) {
                crates[move.from].removeLast()
            }
        }

        return crates.map { it.last() }.joinToString("")
    }

    val testInput = readInput("Day05_test_input")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05_input")
    println(part1(input))
    println(part2(input))
}