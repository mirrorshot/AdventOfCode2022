package com.adventofcode.mirrorshot.calendar

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

abstract class DayTest<X, Y>(
    inputFileName: String,
    val solver: DaySolver<X, Y>,
    val problem1FileExpected: X,
    val problem2FileExpected: Y,
    val examples: List<Triple<String, X, Y>>
) {
    private val inputFile = File(ClassLoader.getSystemResource(inputFileName).file)

    @Test
    fun problem1() {
        examples.forEach { e -> assertEquals(e.second, solver.problem1(e.first.toByteArray())) }
    }

    @Test
    fun problem2() {
        examples.forEach { e -> assertEquals(e.third, solver.problem2(e.first.toByteArray())) }
    }

    @Test
    fun problem1Sol() {
        val result = solver.problem1(inputFile.readBytes())
        println(result)
        assertEquals(problem1FileExpected, result)
    }

    @Test
    fun problem2Sol() {
        val result = solver.problem2(inputFile.readBytes())
        println(result)
        assertEquals(problem2FileExpected, result)
    }

}
