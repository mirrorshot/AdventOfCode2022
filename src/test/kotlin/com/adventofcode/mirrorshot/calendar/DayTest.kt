package com.adventofcode.mirrorshot.calendar

import org.junit.Ignore
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

abstract class DayTest(
    val input: String,
    inputFileName: String,
    val solver: DaySolver,
    val problem1Expected: Long,
    val problem2Expected: Long,
    val problem1FileExpected: Long,
    val problem2FileExpected: Long
) {
    private val inputFile = File(ClassLoader.getSystemResource(inputFileName).file)

    @Test
    fun problem1() {
        val byteArrayInput = input.toByteArray()
        val result = solver.problem1(byteArrayInput)
        assertEquals(problem1Expected, result)
    }

    @Ignore
    @Test
    fun problem2() {
        val byteArrayInput = input.toByteArray()
        val result = solver.problem2(byteArrayInput)
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
