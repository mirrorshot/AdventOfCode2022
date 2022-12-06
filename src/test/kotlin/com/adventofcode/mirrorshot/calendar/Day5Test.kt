package com.adventofcode.mirrorshot.calendar

class Day5Test : DayTest<String, String>(
    input = """    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2""",
    inputFileName = "day5.txt",
    solver = Day5(),
    problem1Expected = "CMZ",
    problem2Expected = "MCD",
    problem1FileExpected = "VQZNJMWTR",
    problem2FileExpected = "NLCDCLVMQ"
)
