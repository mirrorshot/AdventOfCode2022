package com.adventofcode.mirrorshot.calendar

class Day12 : DaySolver<Long, Long>() {
    override fun problem1(input: ByteArray): Long {
        parseInput(input)
        TODO()
    }

    override fun problem2(input: ByteArray): Long {
        TODO("Not yet implemented")
    }

    class Grid(input: String) {
        init {
            val s = input.filter { x -> x.isLetter() }.indexOfFirst { x -> x == 'S' }
            val e = input.filter { x -> x.isLetter() }.indexOfFirst { x -> x == 'E' }
        }

        private val area = input.split("\n")
            .map { x ->
                x.map { v ->
                    when (v) {
                        'S' -> 'a'
                        'E' -> 'z'
                        else -> v
                    } - 'a'
                }
            }


    }
}
