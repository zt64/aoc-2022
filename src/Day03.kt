fun main() {
    val input = readInput("Day03")

    fun part1(input: String): Int {
        val rucksacks = input.lines()

        val priorities = (('a'..'z') + ('A'..'Z'))
            .mapIndexed { index, char -> char to index + 1 }
            .toMap()

        val total = rucksacks.sumOf { rucksack ->
            val middle = rucksack.length / 2

            val compartment1 = rucksack.take(middle).toList()
            val compartment2 = rucksack.takeLast(middle).toList()

            priorities[compartment1.intersect(compartment2.toSet()).single()]!!
        }

        return total
    }

    println(part1(input))
}