package com.adventofcode.mirrorshot.calendar

import java.lang.Integer.min

class Day4 : DaySolver<Long, Long>() {
    override fun problem1(input: ByteArray): Long = countOverlappingRooms(input)
        .also { e -> println(e) }
        .count { e -> e.third == min(e.second.first.count(), e.second.second.count()) }
        .toLong()

    override fun problem2(input: ByteArray): Long = countOverlappingRooms(input)
         .also { e -> println(e) }
         .count { e -> e.third > 0 }
         .toLong()

    private fun countOverlappingRooms(input: ByteArray) = parseInput(input).split('\n')
        .filter { e -> e.isNotBlank() }
        .map { c -> Pair(c, c.split(',')) }
        .map { p -> Pair(p.first, Pair(asRange(p.second[0]), asRange(p.second[1]))) }
        .map { p -> Triple(p.first, p.second, countCommon(p.second.first, p.second.second)) }

    private fun countCommon(firstRange: IntRange, secondRange: IntRange) = firstRange
        .count { r -> secondRange.contains(r) }

    private fun asRange(s: String): IntRange = s.split('-')
        .let { ft -> (ft[0].toInt())..(ft[1].toInt()) }

}
