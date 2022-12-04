package com.adventofcode.mirrorshot.calendar

class Day1: DaySolver() {
    override fun problem1(input: ByteArray): Long {
        return sortedCalories(input)
            .first()
            .also { e -> println(e) }
            .second
    }

    override fun problem2(input: ByteArray): Long {
        return sortedCalories(input)
            .take(3)
            .also { e -> println(e) }
            .sumOf { e -> e.second }
    }

    private fun sortedCalories(input: ByteArray) = parseInput(input).split("\n\n")
        .map { e -> Pair(e, e.split("\n")
            .filter { s -> s.isNotBlank() }
            .sumOf { c -> c.toLong() }) }
        .sortedByDescending { e -> e.second }
}
