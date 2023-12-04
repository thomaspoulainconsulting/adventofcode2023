package days

abstract class Day(private val day: Int, private val name: String) {

    private val input: List<String> = Day::class.java.getResource("/day$day.txt")!!.readText().split("\n")

    abstract fun solvePart1(input: List<String>): String
    abstract fun solvePart2(input: List<String>): String

    fun solve() {
        println("\nDay $day - $name")
        val resultPartOne = solvePart1(input)
        println("Part One: $resultPartOne")

        val resultParTwo = solvePart2(input)
        println("Part Two: $resultParTwo")
    }

}