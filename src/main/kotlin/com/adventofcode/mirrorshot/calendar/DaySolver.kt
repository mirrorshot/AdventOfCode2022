package com.adventofcode.mirrorshot.calendar

abstract class DaySolver {

    fun parseInput(input: ByteArray) = input.decodeToString()
    abstract fun problem1(input: ByteArray): Long
    abstract fun problem2(input: ByteArray): Long
}
