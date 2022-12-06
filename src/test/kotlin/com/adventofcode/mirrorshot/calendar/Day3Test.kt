package com.adventofcode.mirrorshot.calendar

class Day3Test : DayTest<Long, Long>(
    inputFileName = "day3.txt",
    solver = Day3(),
    problem1FileExpected = 7746,
    problem2FileExpected = 2604,
    examples = listOf(
        Triple(
            """vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw""",
            157,
            70
        )
    )
)
