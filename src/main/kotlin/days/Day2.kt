package days

class Day2 : Day(2, "Cube Conundrum") {

    override fun solvePart1(input: List<String>): String {
        val colors = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14,
        )

        return input.sumOf { line ->
            val gameId = Regex("Game (\\d+)").find(line)?.groupValues?.last()?.toInt() ?: 0
            val draws = line.replace(Regex("Game \\d+:"), "").split(";")

            var isImpossible = false

            run draws@{
                draws.map { draw ->
                    draw.split(",")
                        .forEach { cubes ->
                            Regex("(\\d+) (.+)").find(cubes)?.groupValues?.let { cube ->
                                val occurrence = cube[1].toInt()
                                val maxOccurence = colors[cube.last()] ?: 0

                                if (isGameImpossible(occurrence, maxOccurence)) {
                                    isImpossible = true
                                    return@draws
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
        return input.sumOf { line ->
            val draws = line.replace(Regex("Game \\d+:"), "").split(";")

            val colors = mutableMapOf(
                "red" to 0,
                "green" to 0,
                "blue" to 0,
            )

            draws.forEach { draw ->
                draw.split(",")
                    .forEach { cubes ->
                        Regex("(\\d+) (.+)").find(cubes)?.groupValues?.let { cube ->
                            val occurrence = cube[1].toInt()

                            if (colors[cube.last()] == 0 || occurrence > colors[cube.last()]!!)
                                colors[cube.last()] = occurrence

                        }
                    }
            }
            colors["red"]!! * colors["green"]!! * colors["blue"]!!
        }.toString()
    }
}