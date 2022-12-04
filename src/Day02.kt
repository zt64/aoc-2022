enum class Shape(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

fun main() {
    val input = readInput("Day02")

    val mappings = mapOf(
        "A" to Shape.ROCK,
        "B" to Shape.PAPER,
        "C" to Shape.SCISSORS,
        "X" to Shape.ROCK,
        "Y" to Shape.PAPER,
        "Z" to Shape.SCISSORS
    )

    fun part1(input: String): Int {
        var score = 0

        input.lines().forEach { line ->
            val (attack, response) = line.split(" ")
                .zipWithNext { a, b -> mappings[a]!! to mappings[b]!! }
                .single()

            score += response.score + when (response to attack) {
                Shape.ROCK to Shape.SCISSORS,
                Shape.PAPER to Shape.ROCK,
                Shape.SCISSORS to Shape.PAPER -> 6

                else -> if (response == attack) 3 else 0
            }
        }

        return score
    }

    println(part1(input))
}