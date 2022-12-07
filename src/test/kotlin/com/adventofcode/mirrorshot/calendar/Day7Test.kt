package com.adventofcode.mirrorshot.calendar

class Day7Test : DayTest<Long, Long>(
    inputFileName = "day7.txt",
    solver = Day7(),
    problem1FileExpected = 1477771,
    problem2FileExpected = 3579501,
    examples = listOf(
        Triple("""${'$'} cd /
${'$'} ls
dir a
14848514 b.txt
8504156 c.dat
dir d
${'$'} cd a
${'$'} ls
dir e
29116 f
2557 g
62596 h.lst
${'$'} cd e
${'$'} ls
584 i
${'$'} cd ..
${'$'} cd ..
${'$'} cd d
${'$'} ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k""", 95437, 24933642)
    )
)
