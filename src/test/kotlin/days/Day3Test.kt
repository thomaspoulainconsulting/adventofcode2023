package days

import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test : AdventOfCodeTest {

    private val day = Day3()
    private val input =
        """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
        """.trimIndent().split('\n')

    @Test
    override fun solvePart1Test() {
        assertEquals("4361", day.solvePart1(input))
    }


    @Test
    override fun solvePart2Test() {
        assertEquals("467835", day.solvePart2(input))
    }
}