import kotlin.math.min

private object Day13 : Day(13) {
    private fun buildPacket(string: String) = buildList<Any> {
        val stack = mutableListOf<MutableList<Any>>()

        string.forEach { char ->
            if (char.isDigit()) {
                stack.last() += char.toString().toInt()
            } else {
                when (char) {
                    '[' -> {
                        val last = stack.lastOrNull()
                        val list = mutableListOf<Any>()

                        stack += list

                        if (last != null) last.add(list) else add(list)
                    }

                    ']' -> stack.removeLast()
                }
            }
        }
    }

    private val pairs = input.split("\n\n").map { pairs ->
        val (firstPacket, secondPacket) = pairs.lines()

        buildPacket(firstPacket) to buildPacket(secondPacket)
    }

    override fun part1(): Int {
        var sum = 0

        pairs.forEachIndexed { index, (firstPacket, secondPacket) ->
            if (firstPacket < secondPacket) sum += index + 1
        }

        return sum
    }

    override fun part2(): Int = TODO("Not yet implemented")
}

private operator fun Any.compareTo(right: Any): Int {
    val left = this

    @Suppress("UNCHECKED_CAST") // Shouldn't be needed, but Kotlin doesn't know that the lists are lists of Any
    return when {
        left is List<*> && right is List<*> -> {
            left as List<Any>
            right as List<Any>

            for (i in 0 until min(left.size, right.size)) {
                if (left[i] < right[i]) return -1
                if (right[i] < left[i]) return 1
            }

            return left.size.compareTo(right.size) // left is shorter, in right order
        }

        left is Int && right is Int -> left.compareTo(right)
        left is Int && right is List<*> -> listOf(left).compareTo(right as List<Any>)
        left is List<*> && right is Int -> (left  as List<Any>).compareTo(listOf(right))
        else -> throw IllegalArgumentException("Invalid input")
    }
}

private fun main() = Day13.main()