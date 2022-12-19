package com.adventofcode.mirrorshot.calendar

class Day12Test : DayTest<Long, Long>(
    inputFileName = "day12.txt",
    solver = Day12(),
    problem1FileExpected = 0L,
    problem2FileExpected = 0L,
    examples = listOf(
        Triple("""
Sabqponm
abcryxxl
accszExk
acctuvwj
abdefghi""", 31L, 0L)
    )
)
