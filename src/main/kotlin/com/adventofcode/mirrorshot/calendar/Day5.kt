package com.adventofcode.mirrorshot.calendar

import java.util.*
import java.util.regex.Matcher

private val pattern = "move (\\d+) from (\\d+) to (\\d+)".toPattern()

class Day5 : DaySolver<String, String>() {
    override fun problem1(input: ByteArray): String {
        return mapCrateStacks(input)
            .let { i -> Pair(i.first, applyMoves(i.second, i.third)) }
            .also { e -> println("---\nstacks:\n${e.first[0]}\n---\nmoves:\n${e.first[1]}\n---\nmoved: ${e.second.pretty()}") }
            .second
            .map { s -> s.pop() }
            .joinToString("")
    }

    override fun problem2(input: ByteArray): String {
        return mapCrateStacks(input)
            .let { i -> Pair(i.first, applyMoves2(i.second, i.third)) }
            .also { e -> println("---\nstacks:\n${e.first[0]}\n---\nmoves:\n${e.first[1]}\n---\nmoved: ${e.second.pretty()}") }
            .second
            .map { s -> s.pop() }
            .joinToString("")
    }

    private fun mapCrateStacks(input: ByteArray): Triple<List<String>, List<Stack<Char>>, List<Matcher>> =
        parseInput(input).split("\n\n")
            .let { i -> Triple(i, mapInitial(i[0]), extractMoves(i[1])) }
            .also { t -> println("---\nstacks:\n${t.first[0]}\n----\n${t.second.pretty()}") }

    private fun mapInitial(initial: String): List<Stack<Char>> {
        val lines = initial.split("\n")
            .filter { m -> m.isNotBlank() }
            .reversed()

        val mapped = lines.drop(1)
            .fold(lines.first()
                .split(" ")
                .filter { e -> e.isNotBlank() }
                .map { _ -> Stack<Char>() }) { s, l -> inStack(s, l, lines.first()) }

        println(mapped.pretty())

        return mapped
    }

    private fun applyMoves(initial: List<Stack<Char>>, moves: List<Matcher>): List<Stack<Char>> = moves
        .map { m -> explodeMove(m) }
        .fold(initial) { i, m -> applyMove(i, m.first, m.second, m.third) }

    private fun applyMoves2(initial: List<Stack<Char>>, moves: List<Matcher>): List<Stack<Char>> = moves
        .map { m -> explodeMove(m) }
        .fold(initial) { i, m -> applyMove2(i, m.first, m.second, m.third) }

    private fun extractMoves(moves: String) = moves.split("\n")
        .filter { m -> m.isNotBlank() }
        .map { s -> pattern.matcher(s) }
        .filter { p -> p.matches() }

    private fun applyMove(initial: List<Stack<Char>>, howMany: Int, moveFrom: Int, moveTo: Int): List<Stack<Char>> {
        println("move $howMany from $moveFrom to $moveTo")
        (1..howMany)
            .forEach { _ -> initial[moveTo - 1].push(initial[moveFrom - 1].pop()) }
        println(initial.pretty())
        return initial
    }

    private fun applyMove2(initial: List<Stack<Char>>, howMany: Int, moveFrom: Int, moveTo: Int): List<Stack<Char>> {
        println("move $howMany from $moveFrom to $moveTo")
        val tmp = Stack<Char>()
        (1..howMany)
            .forEach { _ -> tmp.push(initial[moveFrom - 1].pop()) }
        println("tmp: $tmp")
        while (tmp.isNotEmpty()) {
            initial[moveTo - 1].push(tmp.pop())
        }
        println(initial.pretty())
        return initial
    }

    private fun inStack(stacks: List<Stack<Char>>, line: String, guide: String): List<Stack<Char>> =
        line.mapIndexed { i, c -> Pair(i, c) }
            .filter { p -> p.second.isLetter() }
            .fold(stacks) { s, p -> toStack(s, p.second, identifyStack(guide, p.first)) }

    private fun toStack(
        s: List<Stack<Char>>,
        c: Char,
        stackIndex: Int
    ): List<Stack<Char>> {
        s[stackIndex].push(c)
        return s
    }

    private fun identifyStack(guide: String, index: Int) = guide[index].digitToInt() - 1

    private fun explodeMove(move: Matcher): Triple<Int, Int, Int> = move
        .let { m -> Triple(m.group(1).toInt(), m.group(2).toInt(), m.group(3).toInt()) }


    private fun List<Stack<Char>>.pretty() = this.mapIndexed { i, s -> "${i + 1}: $s" }
}
