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
                        if (isSymbolAround(index, it.range, input, ::isSymbol)) {
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
        predicate: (Char) -> Boolean
    ): Boolean {
        val maxLength = input.first().length

        intRange.forEach ranges@{ range ->
            if (range == 0) return@ranges
            if (range == maxLength - 1) return@ranges

            (-1..1).forEach abs@{ abs ->
                if (index + abs < 0) return@abs
                if (index + abs == maxLength) return@abs

                (-1..1).forEach { ord ->
                    if (predicate(input[index + abs][range + ord])) {
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
        val regexStar = Regex("\\*")
        val regexNumber = Regex("\\d+")
        var total = 0

        // find all *
        // check if there is numbers around
        // if there is at least 2 numbers (on same line, above or below)
        // retrieve those number thanks to there range
        // multiple the found numbers
        // addition all number

        input.forEachIndexed { index, line ->
            regexStar.findAll(line)
                .takeIf { !it.none() }
                ?.let { matches ->
                    matches.forEach {
                        if (isSymbolAround(index, it.range, input, predicate = { it.isDigit() })) {
                            val adjacentRange = IntRange(it.range.first -1, it.range.last +1)
                            val numberFound = mutableListOf<Int>()

                            // check number above
                            if (index > 0) {
                                regexNumber.findAll(input[index - 1])
                                    .takeIf { !it.none() }
                                    ?.let { matchesNumber ->
                                        matchesNumber.forEach { matchNumber ->
                                            if (matchNumber.range.intersect(adjacentRange).isNotEmpty()) {
                                                numberFound.add(matchNumber.value.toInt())
                                            }
                                        }
                                    }
                            }

                            // current line
                            regexNumber.findAll(input[index])
                                .takeIf { !it.none() }
                                ?.let { matchesNumber ->
                                    matchesNumber.forEach { matchNumber ->
                                        if (matchNumber.range.intersect(adjacentRange).isNotEmpty()) {
                                            numberFound.add(matchNumber.value.toInt())
                                        }
                                    }
                                }

                            // below line
                            if (index < input.size - 1) {
                                regexNumber.findAll(input[index + 1])
                                    .takeIf { !it.none() }
                                    ?.let { matchesNumber ->
                                        matchesNumber.forEach { matchNumber ->
                                            if (matchNumber.range.intersect(adjacentRange).isNotEmpty()) {
                                                numberFound.add(matchNumber.value.toInt())
                                            }
                                        }
                                    }
                            }


                            if (numberFound.size == 2) {
                                total += numberFound.first() * numberFound.last()
                            }
                        }
                    }
                }
        }
        return total.toString()
    }
}