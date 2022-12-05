package com.adventofcode.mirrorshot.calendar

class Day4Test : DayTest<Long, Long>(
    input = """2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8""",
    inputFileName = "day4.txt",
    solver = Day4(),
    problem1Expected = 2,
    problem2Expected = 4,
    problem1FileExpected = 518,
    problem2FileExpected = 909
)
