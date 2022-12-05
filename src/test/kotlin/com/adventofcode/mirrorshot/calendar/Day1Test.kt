package com.adventofcode.mirrorshot.calendar

class Day1Test : DayTest<Long, Long>(
    input = """1000
2000
3000

4000

5000
6000

7000
8000
9000

10000""",
    inputFileName = "day1.txt",
    solver = Day1(),
    problem1Expected = 24000,
    problem2Expected = 45000,
    problem1FileExpected = 67450,
    problem2FileExpected = 199357
)
