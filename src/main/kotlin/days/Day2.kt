package days

class Day2 : Day(2, "Cube Conundrum") {

    private val colors = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14,
    )

    override fun solvePart1(input: List<String>): String {
        return input.sumOf { line ->
            val gameId = Regex("Game (\\d+)").find(line)?.groupValues?.last()?.toInt() ?: 0
            val games = line.replace(Regex("Game \\d+:"), "").split(";")

            var isImpossible = false

            run games@{
                games.map { game ->
                    game.split(",")
                        .forEach { draw ->
                            Regex("(\\d+) (.+)").find(draw)?.groupValues?.let {
                                val occurrence = it[1].toInt()
                                val maxOccurence = colors[it.last()] ?: 0

                                if (isGameImpossible(occurrence, maxOccurence)) {
                                    isImpossible = true
                                    return@games
                                }
                            }
                        }
                }
            }

            if (isImpossible) 0
            else gameId
        }.toString()
    }

    private fun isGameImpossible(occurence: Int, maxOccurrence: Int): Boolean =
        occurence > maxOccurrence

    override fun solvePart2(input: List<String>): String {
        TODO("Not yet implemented")
    }
}