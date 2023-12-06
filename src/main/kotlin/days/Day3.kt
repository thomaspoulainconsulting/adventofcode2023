package days

class Day3 : Day(3, "Gear Ratios") {

    override fun solvePart1(input: List<String>): String {

        val regex = Regex("\\d+")
        var total = 0

        input.mapIndexed { index, line ->
            regex.findAll(line)
                .takeIf { !it.none() }
                ?.let { matches ->
                    matches.forEach {
                        if (isSymbolAroundNumber(index, it.range, input)) {
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
    private fun isSymbolAroundNumber(index: Int, intRange: IntRange, input: List<String>): Boolean {
        val maxLength = input.first().length

        intRange.forEach ranges@ { range ->
            if (range == 0) return@ranges
            if (range == maxLength - 1) return@ranges

            (-1..1).forEach abs@ { abs ->
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

    override fun solvePart2(input: List<String>): String {

        // find all *
        // check if there is numbers around
        // if there is at least 2 numbers (on same line, above or below)
        // retrieve those number thanks to there range
        // multiple the found numbers
        // addition all number





        // find all numbers and all *
        // find all *
        // for each, find if there is numbers around it
        // if so, find those number and multiply them
        // add all numbers
        return ""
    }
}