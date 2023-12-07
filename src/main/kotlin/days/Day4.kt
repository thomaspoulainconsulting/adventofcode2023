package days

import kotlin.math.pow

class Day4 : Day(4, "Scratchcards") {
    override fun solvePart1(input: List<String>): String {
        val regex = Regex("(?<=:)\\d*(.*)\\|\\d*(.*)")
        var points = 0

        input.forEach { line ->
            regex.findAll(line)
                .takeIf { !it.none() }
                ?.let { matches ->
                    matches.forEach { match ->
                        val winningNumbers = match.groupValues[1].trim().split(" ").filter { it.isNotEmpty() }
                        val scratchNumbers = match.groupValues[2].trim().split(" ").filter { it.isNotEmpty() }

                        scratchNumbers.intersect(winningNumbers).let {
                            points += calculatePoints(it.size)
                        }
                    }
                }
        }
        return points.toString()
    }

    private fun calculatePoints(iteration: Int): Int {
        return 2f.pow((iteration - 1)).toInt()
    }

    override fun solvePart2(input: List<String>): String {
        val regex = Regex("(?<=:)\\d*(.*)\\|\\d*(.*)")
        // data structure pour sauvegarder le nombre de scratchcard gagné
        val wonCards = input.indices.map { it to 1 }.toMutableList()

        input.mapIndexed { index, line ->
            regex.findAll(line)
                .takeIf { !it.none() }
                ?.let { matches ->
                    matches.forEach { match ->
                        val winningNumbers = match.groupValues[1].trim().split(" ").filter { it.isNotEmpty() }
                        val scratchNumbers = match.groupValues[2].trim().split(" ").filter { it.isNotEmpty() }

                        scratchNumbers.intersect(winningNumbers.toSet()).forEachIndexed { wonIndex, _ ->
                            (1..wonCards[index].second).map {
                                wonCards[index + wonIndex + 1] =
                                    index + wonIndex + 1 to wonCards[index + wonIndex + 1].second + 1
                            }
                        }
                    }
                }
        }
        return wonCards.sumOf { it.second }.toString()
    }
}