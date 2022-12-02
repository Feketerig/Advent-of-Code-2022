enum class Type(val score: Int){
    ROCK(1), PAPER(2), SCISSORS(3)
}

fun main() {

    fun fight(first: Type, second: Type): Int{
        if (first == second){
            return 3 + second.score
        }
        return when(second){
            Type.ROCK -> {
                if (first == Type.PAPER){
                    0 + second.score
                }else{
                    6 + second.score
                }
            }
            Type.PAPER -> {
                if (first == Type.SCISSORS){
                    0 + second.score
                }else{
                    6 + second.score
                }
            }
            Type.SCISSORS -> {
                if (first == Type.ROCK){
                    0 + second.score
                }else{
                    6 + second.score
                }
            }
        }
    }

    fun parseInput(input: List<String>): List<Pair<Type, Type>>{
        return input.map { line ->
            val first = when(line.first()){
                'A' -> Type.ROCK
                'B' -> Type.PAPER
                'C' -> Type.SCISSORS
                else -> Type.ROCK
            }
            val last  = when(line.last()){
                'X' -> Type.ROCK
                'Y' -> Type.PAPER
                'Z' -> Type.SCISSORS
                else -> Type.ROCK
            }
            Pair(first, last)
        }
    }

    fun part1(input: List<String>): Int {
        val parsed = parseInput(input)
        var sum = 0
        parsed.forEach {
            sum += fight(it.first, it.second)
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val parsed = parseInput(input)
        var sum = 0
        parsed.forEach {
            sum += when(it.second){
                Type.ROCK -> {
                    when (it.first) {
                        Type.ROCK -> {
                            Type.SCISSORS.score
                        }

                        Type.PAPER -> {
                            Type.ROCK.score
                        }

                        Type.SCISSORS -> {
                            Type.PAPER.score
                        }
                    }
                }
                Type.PAPER -> {
                    3 + it.first.score
                }
                Type.SCISSORS -> {
                    when (it.first) {
                        Type.ROCK -> {
                            6 + Type.PAPER.score
                        }

                        Type.PAPER -> {
                            6 + Type.SCISSORS.score
                        }

                        Type.SCISSORS -> {
                            6 + Type.ROCK.score
                        }
                    }
                }
            }
        }
        return sum
    }

    val testInput = readInput("Day02_test_input")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02_input")
    println(part1(input))
    println(part2(input))
}