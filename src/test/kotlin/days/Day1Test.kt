package days

import org.junit.Test
import kotlin.test.assertEquals

class Day1Test : AdventOfCodeTest {

    private val day = Day1()
    private val input =
        """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
        """.trimIndent().split('\n')


    @Test
    override fun solvePart1Test() {
        assertEquals("142", day.solvePart1(input))
    }

    @Test
    override fun solvePart2Test() {
        //assertEquals("45000", day.solvePart2(input))
    }
}