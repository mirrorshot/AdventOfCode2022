package com.adventofcode.mirrorshot.calendar

class Day2Test : DayTest(
    input = """A Y
B X
C Z""",
    inputFileName = "day2.txt",
    solver = Day2(),
    problem1Expected = 15,
    problem2Expected = 12,
    problem1FileExpected = 14827,
    problem2FileExpected = 13889
)
