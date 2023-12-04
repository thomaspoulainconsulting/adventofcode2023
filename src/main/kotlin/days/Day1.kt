package days

class Day1 : Day(1, "Trebuchet?!") {

    override fun solvePart1(input: List<String>): String {
        return input.mapNotNull { line ->
            line.filter { it.isDigit() }
                .takeIf { it.isNotEmpty() }
                ?.let { digits ->
                    "${digits.first()}${digits.last()}".toInt()
                }
        }
        .sum()
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

        return input.map { line ->
            val first = line.findAnyOf(dictionary.map { it.key })?.let { elt ->
                val indexOfFirstRealDigit = line.indexOfFirst { it.isDigit() }
                if (indexOfFirstRealDigit != -1 && indexOfFirstRealDigit < elt.first) return@let line.first { it.isDigit() }

                dictionary.entries.first { it.key == elt.second }.value
            } ?: line.first { it.isDigit() }

            val last = line.findLastAnyOf(dictionary.map { it.key })?.let { elt ->
                val indexOfLastRealDigit = line.indexOfLast { it.isDigit() }
                if (indexOfLastRealDigit != -1 && indexOfLastRealDigit > elt.first) return@let line.last { it.isDigit() }

                dictionary.entries.first { it.key == elt.second }.value
            } ?: line.last { it.isDigit() }

            "$first$last".toInt()
        }
            .sum()
            .toString()
    }

}