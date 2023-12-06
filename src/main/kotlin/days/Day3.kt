package days

class Day3 : Day(3, "Gear Ratios") {

    override fun solvePart1(input: List<String>): String {
        val regex = Regex("\\d+")
        var total = 0

        input.forEachIndexed { index, line ->
            regex.findAll(line)
                .takeIf { !it.none() }
                ?.let { matches ->
                    matches.forEach {
                        if (isSymbolAround(index, it.range, input)) {
                            total += it.value.toInt()
                        }
                    }
                }
        }
        return total.toString()
    }

    /**
     * We check for symbols on above, current and below lines
     */
    private fun isSymbolAround(
        index: Int,
        intRange: IntRange,
        input: List<String>,
    ): Boolean {
        val maxLength = input.first().length

        intRange.forEach ranges@{ range ->
            if (range == 0) return@ranges
            if (range == maxLength - 1) return@ranges

            (-1..1).forEach abs@{ abs ->
                if (index + abs < 0) return@abs
                if (index + abs == maxLength) return@abs

                (-1..1).forEach { ord ->
                    if (isSymbol(input[index + abs][range + ord])) {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun isSymbol(char: Char): Boolean {
        return !char.isDigit() && char != '.'
    }

    /**
     * find all *
     * check if there is numbers around
     * if there is at least 2 numbers (on same line, above or below)
     * retrieve those number thanks to there range
     * multiply the found numbers
     * addition them
     */
    override fun solvePart2(input: List<String>): String {
        val regexStar = Regex("\\*")
        var total = 0

        input.forEachIndexed { index, line ->
            regexStar.findAll(line).let { matches ->
                matches.forEach {
                    if (isSymbolAround(index, it.range, input)) {
                        val adjacentRange = IntRange(it.range.first - 1, it.range.last + 1)
                        val numbers = mutableListOf<Int>()

                        // check number above
                        if (index > 0) {
                            isAdjacentNumber(input[index - 1], adjacentRange, numbers::add)
                        }

                        // current line
                        isAdjacentNumber(input[index], adjacentRange, numbers::add)

                        // below line
                        if (index < input.size - 1) {
                            isAdjacentNumber(input[index + 1], adjacentRange, numbers::add)
                        }

                        if (numbers.size == 2) {
                            total += numbers.first() * numbers.last()
                        }
                    }
                }
            }
        }
        return total.toString()
    }

    private fun isAdjacentNumber(line: String, adjacentRange: IntRange, onMatch: (Int) -> Unit) {
        val regexNumber = Regex("\\d+")

        regexNumber.findAll(line).let { matches ->
            matches.forEach { matchNumber ->
                if (matchNumber.range.intersect(adjacentRange).isNotEmpty()) {
                    onMatch(matchNumber.value.toInt())
                }
            }
        }
    }
}