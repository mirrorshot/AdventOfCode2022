package com.adventofcode.mirrorshot.calendar

class Day2 {
    fun problem1(input: ByteArray): Long {
        return extractMatches(input)
            .map { e -> Pair(RPS.decode(e[0]), RPS.decode(e[2])) }
            .map { m -> Pair(m, score(m.first, m.second)) }
            .also { m -> println(m) }
            .sumOf { m -> m.second }
    }

    fun problem2(input: ByteArray): Long {
        return extractMatches(input)
            .map { e -> RPS.decode(e[0], e[2]) }
            .map { m -> Pair(m, score(m.first, m.second)) }
            .also { m -> println(m) }
            .sumOf { m -> m.second }
    }

    private fun extractMatches(input: ByteArray) = input.decodeToString().split("\n")
        .filter { e -> e.isNotBlank() }

    private fun score(opponent: RPS, move: RPS) =
        move.compare(opponent) + move.score

    enum class RPS(val score: Int) : Comparable<RPS> {
        R(1),
        P(2),
        S(3);

        fun compare(other: RPS): Long = when (this) {
            R -> when (other) {
                R -> 3
                P -> 0
                S -> 6
            }

            P -> when (other) {
                R -> 6
                P -> 3
                S -> 0
            }

            S -> when (other) {
                R -> 0
                P -> 6
                S -> 3
            }
        }


        companion object {
            fun decode(c: Char) = when (c) {
                'A', 'a', 'X', 'x' -> R
                'B', 'b', 'Y', 'y' -> P
                'C', 'c', 'Z', 'z' -> S
                else -> throw fail(c)
            }

            fun decode(move: Char, outcome: Char) = when (move) {
                'A', 'a' -> Pair(
                    R, when (outcome) {
                        'X', 'x' -> S
                        'Y', 'y' -> R
                        'Z', 'z' -> P
                        else -> throw fail(outcome)
                    }
                )

                'B', 'b' -> Pair(
                    P, when (outcome) {
                        'X', 'x' -> R
                        'Y', 'y' -> P
                        'Z', 'z' -> S
                        else -> throw fail(outcome)
                    }
                )

                'C', 'c' -> Pair(
                    S, when (outcome) {
                        'X', 'x' -> P
                        'Y', 'y' -> S
                        'Z', 'z' -> R
                        else -> throw fail(outcome)
                    }
                )

                else -> throw fail(move)
            }

            private fun fail(c: Char) = IllegalArgumentException("Invalid character: %c".format(c))
        }
    }
}
