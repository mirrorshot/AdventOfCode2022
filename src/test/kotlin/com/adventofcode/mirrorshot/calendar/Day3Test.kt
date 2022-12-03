package com.adventofcode.mirrorshot.calendar

import org.junit.Ignore
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day3Test {
    private val input = """vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw"""
    private val inputFile = File(ClassLoader.getSystemResource("day3.txt").file)
    private val solver = Day3()

    @Test
    fun problem1() {
        val byteArrayInput = input.toByteArray()
        val result = solver.problem1(byteArrayInput)
        assertEquals(157, result)
    }

    @Ignore
    @Test
    fun problem2() {
        val byteArrayInput = input.toByteArray()
        val result = solver.problem2(byteArrayInput)
        assertEquals(70, result)
    }

    @Test
    fun problem1Sol() {
        val result = solver.problem1(inputFile.readBytes())
        println(result)
        assertEquals(7746, result)
    }

    @Ignore
    @Test
    fun problem2Sol() {
        val result = solver.problem2(inputFile.readBytes())
        println(result)
        assertEquals(2604, result)
    }
}
