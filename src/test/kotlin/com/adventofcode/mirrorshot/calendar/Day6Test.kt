package com.adventofcode.mirrorshot.calendar

class Day6Test : DayTest<Int, Int>(
    inputFileName = "day6.txt",
    solver = Day6(),
    problem1FileExpected = 1987,
    problem2FileExpected = 3059,
    examples = listOf(
        Triple("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 7, 19),
        Triple("bvwbjplbgvbhsrlpgdmjqwftvncz", 5, 23),
        Triple("nppdvjthqldpwncqszvftbrmjlhg", 6, 23),
        Triple("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10, 29),
        Triple("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11, 26)
    )
)
