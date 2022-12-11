package com.adventofcode.mirrorshot.calendar

class Day9Test : DayTest<Int, Int>(
    inputFileName = "day9.txt",
    solver = Day9(),
    problem1FileExpected = 6269,
    problem2FileExpected = 2557,
    examples = listOf(
        Triple("""
R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2""", 13, 1),
        Triple("""
R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20
""", 88, 36)
    )
)
