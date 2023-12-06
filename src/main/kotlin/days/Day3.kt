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
        var isSymbolAround = false


        intRange.forEach { range ->
            if (range == 0) return@forEach
            if (range == maxLength - 1) return@forEach

            (-1..1).forEach abs@ { abs ->
                if (index + abs < 0) return@abs
                if (index + abs == maxLength) return@abs

                (-1..1).forEach { ord ->
                    if (isSymbol(input[index + abs][range + ord])) {
                        isSymbolAround = true
                    }
                }
            }
        }
        return isSymbolAround
    }

    private fun isSymbol(char: Char): Boolean {
        return !char.isDigit() && char != '.'
    }

    override fun solvePart2(input: List<String>): String {
        TODO("Not yet implemented")
    }
}