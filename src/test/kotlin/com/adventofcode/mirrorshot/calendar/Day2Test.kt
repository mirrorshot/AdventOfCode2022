package com.adventofcode.mirrorshot.calendar

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day2Test {
    private val input = """A Y
B X
C Z"""
    private val inputFile = File(ClassLoader.getSystemResource("day2.txt").file)
    private val solver = Day2()

    @Test
    fun problem1() {
        val byteArrayInput = input.toByteArray()
        val result = solver.problem1(byteArrayInput)
        assertEquals(15, result)
    }

    @Test
    fun problem2() {
        val input = """A Y
B X
C Z"""
        val byteArrayInput = input.toByteArray()
        val result = solver.problem2(byteArrayInput)
        assertEquals(12, result)
    }

    @Test
    fun problem1Sol() {
        val result = solver.problem1(inputFile.readBytes())
        println(result)
        assertEquals(14827, result)
    }

    @Test
    fun problem2Sol() {
        val result = solver.problem2(inputFile.readBytes())
        println(result)
        assertEquals(13889, result)
    }
}
