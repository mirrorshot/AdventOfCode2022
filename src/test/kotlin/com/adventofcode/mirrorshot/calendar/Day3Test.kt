package com.adventofcode.mirrorshot.calendar

class Day3Test : DayTest<Long, Long>(
    input = """vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw""",
    inputFileName = "day3.txt",
    solver = Day3(),
    problem1Expected = 157,
    problem2Expected = 70,
    problem1FileExpected = 7746,
    problem2FileExpected = 2604
)
