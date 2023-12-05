package days

import java.io.File

abstract class Day(private val day: Int, private val name: String) {

    private val input: List<String> = File("src/main/resources/day$day.txt").readLines()

    abstract fun solvePart1(input: List<String>): String
    abstract fun solvePart2(input: List<String>): String

    fun solve() {
        println("\nDay $day - $name")
        println("Part One: ${solvePart1(input)}")
        println("Part Two: ${solvePart2(input)}")
    }

}