package com.adventofcode.mirrorshot.calendar

class Day3 : DaySolver() {
    override fun problem1(input: ByteArray): Long {
        return extractMatches(input)
            .map { e -> Pair(e.substring(0, e.length / 2), e.substring(e.length / 2)) }
            .map { e -> Pair(e, score(e)) }
            .also { m -> println(m) }
            .sumOf { m -> m.second.second }
    }

    override fun problem2(input: ByteArray): Long {
        return extractMatches(input)
            .chunked(3)
            .map { g -> Pair(g, score(g)) }
            .also { m -> println(m) }
            .sumOf { m -> m.second.second }
    }

    private fun extractMatches(input: ByteArray) = input.decodeToString().split("\n")
        .filter { e -> e.isNotBlank() }

    private fun score(rucksack: Pair<String, String>): Pair<Char, Long> =
        rucksack.first
            .filter { c -> rucksack.second.contains(c) }
            .map { r -> Pair(r, priority(r)) }
            .first()

    private fun score(rucksacks: List<String>): Pair<Char, Long> =
        rucksacks[0]
            .filter { i -> rucksacks[1].contains(i) && rucksacks[2].contains(i) }
            .map { c -> Pair(c, priority(c)) }
            .first()

    private fun priority(item: Char): Long = when (item) {
        in 'a'..'z' -> item - 'a' + 1L
        in 'A'..'Z' -> item - 'A' + 27L
        else -> 0L
    }

}
