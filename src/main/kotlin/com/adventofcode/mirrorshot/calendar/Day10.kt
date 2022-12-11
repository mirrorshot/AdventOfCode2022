package com.adventofcode.mirrorshot.calendar

import kotlin.math.absoluteValue

class Day10 : DaySolver<Int, String>() {
    override fun problem1(input: ByteArray): Int = parseCommands(input)
        .foldIndexed(Pair(1, listOf<Pair<Int, Int>>())) { i, p, v -> resolve(i, p, v) }
        .second
        .sumOf { p -> p.second }

    override fun problem2(input: ByteArray): String = parseCommands(input)
        .foldIndexed(Pair(1, "")) { i, p, v -> Pair(
            p.first + (v.third ?: 0),
            p.second + applyColor(i % 40, p.first).also { n -> println("$i - $p -> $n") }
        ) }
        .second
        .also { s -> println(s) }
        .chunkedSequence(40)
        .joinToString("\n")

    private fun parseCommands(input: ByteArray): List<Triple<String, String, Int?>> = parseInput(input).split("\n")
        .filter { s -> s.isNotBlank() }
        .flatMap { s ->
            s.split(" ")
                .let { p -> Pair(p[0], p.getOrNull(1)) }
                .let { p ->
                    when (p.first) {
                        "noop" -> listOf(Triple(s, p.first, null))
                        "addx" -> listOf(Triple(s, "fill", null), Triple(s, p.first, p.second!!.toInt()))
                        else -> throw IllegalArgumentException("$p is not a valid command")
                    }
                }
        }

    private fun resolve(i: Int, p: Pair<Int, List<Pair<Int, Int>>>, v: Triple<String, String, Int?>) = when (v.second) {
        "addx" -> p.first + v.third!!
        else -> p.first
    }.let { x ->
        Pair(
            x,
            when {
                ((i + 1) - 20) % 40 == 0 -> p.second.plus(Pair(p.first, (i + 1) * p.first))
                else -> p.second
            }
        )
    }
        .also { x -> println("$i - $v: ${if (v.second == "addx") "${p.first} ${if (v.third!! >= 0) "+" else "-"} ${v.third!!.absoluteValue} ->" else ""} $x ") }

    private fun applyColor(cycle: Int, registry: Int): String = when (cycle) {
        in (registry - 1)..(registry + 1) -> "#"
        else -> "."
    }

}
