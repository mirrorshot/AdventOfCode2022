package com.adventofcode.mirrorshot.calendar

import kotlin.math.min

private const val HANDSHAKE_LENGTH = 4
private const val MESSAGE_LENGTH = 14

class Day6 : DaySolver<Int, Int>() {
    override fun problem1(input: ByteArray): Int =
        extractSequences(parseInput(input), HANDSHAKE_LENGTH)
            .first { p -> p.second.toSet().size >= HANDSHAKE_LENGTH }
            .also { p -> println(p) }
            .first + HANDSHAKE_LENGTH

    override fun problem2(input: ByteArray): Int =
        extractSequences(parseInput(input), MESSAGE_LENGTH)
            .first { p -> p.second.toSet().size >= MESSAGE_LENGTH }
            .also { p -> println(p) }
            .first + MESSAGE_LENGTH

    private fun extractSequences(input: String, len: Int): List<Pair<Int, String>> =
        input.mapIndexed { i, _ -> Pair(i, input.substring(i..(min((i + len - 1), (input.length - 1))))) }

}
