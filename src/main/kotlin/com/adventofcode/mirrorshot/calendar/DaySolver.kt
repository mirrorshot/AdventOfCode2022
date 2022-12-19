package com.adventofcode.mirrorshot.calendar

abstract class DaySolver<X, Y> {

    fun parseInput(input: ByteArray) = input.decodeToString()
        .replace("\n\r", "\n")
        .replace("\r\n", "\n")

    abstract fun problem1(input: ByteArray): X

    abstract fun problem2(input: ByteArray): Y
}
