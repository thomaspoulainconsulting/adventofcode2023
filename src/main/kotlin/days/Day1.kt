package days

class Day1 : Day(1, "Trebuchet?!") {

    override fun solvePart1(input: List<String>): String {
        return input
            .sumOf(::computeDigits)
            .toString()
    }

    override fun solvePart2(input: List<String>): String {
        val dictionary = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9",
        )
        val regex = Regex(dictionary.keys.joinToString("|") { it })

        return input.sumOf { line ->
            val answer = regex
                .replace(line) { match ->
                    dictionary[match.value].orEmpty()
                }
                .let(::computeDigits)

            val first = line.findAnyOf(dictionary.map { it.key })?.let { elt ->
                val indexOfFirstRealDigit = line.indexOfFirst { it.isDigit() }
                if (indexOfFirstRealDigit != -1 && indexOfFirstRealDigit < elt.first) line.first { it.isDigit() }
                else dictionary.entries.first { it.key == elt.second }.value
            } ?: line.first { it.isDigit() }

            val last = line.findLastAnyOf(dictionary.map { it.key })?.let { elt ->
                val indexOfLastRealDigit = line.indexOfLast { it.isDigit() }
                if (indexOfLastRealDigit != -1 && indexOfLastRealDigit > elt.first) line.last { it.isDigit() }
                else dictionary.entries.first { it.key == elt.second }.value
            } ?: line.last { it.isDigit() }

            "$first$last".toInt()
        }.toString()
    }

    private fun computeDigits(line: String): Int =
        line.filter { it.isDigit() }
            .let { digits ->
                "${digits.first()}${digits.last()}".toInt()
            }

}