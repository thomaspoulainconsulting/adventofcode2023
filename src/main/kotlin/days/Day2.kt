package days

class Day2 : Day(2, "Cube Conundrum") {

    private val redNumber = 12
    private val greenNumber = 13
    private val blueNumber = 14

    override fun solvePart1(input: List<String>): String {
        return input.sumOf { line ->
            val gameId = Regex("Game (\\d+)").find(line)?.groupValues?.last()?.toInt() ?: 0
            val games = line.replace(Regex("Game \\d+:"), "").split(";")

            var isGamesImpossible = false

            games.forEach game@ { game ->
                game.split(",").forEach { tirage ->
                    Regex("(\\d+) (.+)").find(tirage)?.groupValues?.let {
                        val occurence = it[1].toInt()

                        val isImpossible = when (it.last()) {
                            "red" -> isGameImpossible(occurence, redNumber)
                            "blue" -> isGameImpossible(occurence, blueNumber)
                            "green" -> isGameImpossible(occurence, greenNumber)
                            else -> false
                        }
                        if (isImpossible) {
                            isGamesImpossible = true
                            return@game
                        }
                    }
                }
            }

            if (isGamesImpossible) 0
            else gameId
        }.toString()
    }

    private fun isGameImpossible(occurence: Int, maxOccurence: Int) : Boolean {
        return occurence > maxOccurence
    }

    override fun solvePart2(input: List<String>): String {
        TODO("Not yet implemented")
    }
}