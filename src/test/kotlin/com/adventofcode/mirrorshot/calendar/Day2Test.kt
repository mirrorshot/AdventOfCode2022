package com.adventofcode.mirrorshot.calendar

class Day2Test : DayTest<Long, Long>(
    inputFileName = "day2.txt",
    solver = Day2(),
    problem1FileExpected = 14827,
    problem2FileExpected = 13889,
    examples = listOf(
        Triple(
            """A Y
B X
C Z""",
            15,
            12
        )
    )
)
