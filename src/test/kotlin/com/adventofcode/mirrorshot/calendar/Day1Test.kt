package com.adventofcode.mirrorshot.calendar

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day1Test {
    private val input = """1000
2000
3000

4000

5000
6000

7000
8000
9000

10000"""
    private val inputFile = File(ClassLoader.getSystemResource("day1.txt").file)
    private val solver = Day1()

    @Test
    fun problem1() {
        val byteArrayInput = input.toByteArray()
        val result = solver.problem1(byteArrayInput)
        assertEquals(24000, result)
    }

    @Test
    fun problem2() {
        val byteArrayInput = input.toByteArray()
        val result = solver.problem2(byteArrayInput)
        assertEquals(45000, result)
    }

    @Test
    fun problem1Sol() {
        val result = solver.problem1(inputFile.readBytes())
        println(result)
        assertEquals(67450, result)
    }

    @Test
    fun problem2Sol() {
        val result = solver.problem2(inputFile.readBytes())
        println(result)
        assertEquals(199357, result)
    }
}
