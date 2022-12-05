package com.adventofcode.mirrorshot.calendar

import org.junit.Ignore
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

abstract class DayTest<X, Y>(
    val input: String,
    inputFileName: String,
    val solver: DaySolver<X, Y>,
    val problem1Expected: X,
    val problem2Expected: Y,
    val problem1FileExpected: X,
    val problem2FileExpected: Y
) {
    private val inputFile = File(ClassLoader.getSystemResource(inputFileName).file)

    @Test
    fun problem1() {
        val result = solver.problem1(input.toByteArray())
        assertEquals(problem1Expected, result)
    }

    @Ignore
    @Test
    fun problem2() {
        val result = solver.problem2(input.toByteArray())
        assertEquals(problem2Expected, result)
    }

    @Test
    fun problem1Sol() {
        val result = solver.problem1(inputFile.readBytes())
        println(result)
        assertEquals(problem1FileExpected, result)
    }

    @Ignore
    @Test
    fun problem2Sol() {
        val result = solver.problem2(inputFile.readBytes())
        println(result)
        assertEquals(problem2FileExpected, result)
    }

}
