package com.adventofcode.mirrorshot.calendar

class Day8Test : DayTest<Int, Int>(
    inputFileName = "day8.txt",
    solver = Day8(),
    problem1FileExpected = 1543,
    problem2FileExpected = 595080,
    examples = listOf(
        Triple("""
30373
25512
65332
33549
35390""", 21, 8)
    )
)
