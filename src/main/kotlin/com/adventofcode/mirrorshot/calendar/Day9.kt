package com.adventofcode.mirrorshot.calendar


class Day9 : DaySolver<Int, Int>() {
    override fun problem1(input: ByteArray): Int =
        parseInput(input).split("\n")
            .filter { s -> s.isNotBlank() }
            .map { s -> Move(s) }
//            .onEach { m -> println(m) }
            .fold(Grid(2)) { g, m -> g.apply(m) }
            .history
            .map { k -> k.last() }
            .distinct()
            .also { k -> println(k) }
            .count()

    override fun problem2(input: ByteArray): Int =
        parseInput(input).split("\n")
            .filter { s -> s.isNotBlank() }
            .map { s -> Move(s) }
//            .onEach { m -> println(m) }
            .fold(Grid(10)) { g, m -> g.apply(m) }
            .history
            .map { k -> k.last() }
            .distinct()
            .also { k -> println(k) }
            .count()

    class Move(s: String) {
        val direction = Direction.decode(s[0])
        val distance = s.split(" ")[1].toInt()
        override fun toString() = "Move $direction $distance times"
    }

    class Grid {
        private val knots: List<Coordinates>

        val history: List<List<Coordinates>>

        constructor(size: Int) {
            knots = (0 until size).map { _ -> Coordinates(0, 0) }
            history = listOf(knots)
        }

        constructor(knots: List<Coordinates>, history: List<List<Coordinates>>) {
            this.knots = knots
            this.history = history
        }

        fun apply(move: Move): Grid {
            var g = this
//            println("------------------\n$move")
            for (i in 0 until move.distance) {
                g = g.move(move.direction)
            }
//            println("\n$g\n")
            return g
        }

        fun move(direction: Direction): Grid = knots.subList(1, knots.size)
            .fold(listOf(knots[0].move(direction))) { l, k -> l.plus(follow(l.last(), k)) }
//            .onEach { k -> println(k) }
            .let { k -> Grid(k, history.plusElement(k)) }

        private fun follow(head: Coordinates, tail: Coordinates): Coordinates {
            fun findY() =
                if (head.y > tail.y) {
                    tail.y + 1
                } else if (head.y < tail.y) {
                    tail.y - 1
                } else {
                    tail.y
                }

            return if (head.isAdjacent(tail)) {
                tail
            } else if (head.x > tail.x) {
                Coordinates(tail.x + 1, findY())
            } else if (head.x < tail.x) {
                Coordinates(tail.x - 1, findY())
            } else {
                Coordinates(tail.x, findY())
            }
        }

        override fun toString(): String {
            val points = history.flatten().distinct()
            val maxX = points.maxOf { c -> c.x }
            val maxY = points.maxOf { c -> c.y }
            return (maxX downTo 0).joinToString("\n") { x ->
                (0..maxY).joinToString("") { y ->
                    Coordinates(x, y).let { p ->
                        if (knots.contains(p))
                            knots.indexOf(p).let { c -> if (c == 0) "H" else c.toString() }
                        else if (p.x == 0 && p.y == 0)
                            "O"
                        else if (points.contains(p))
                            "#"
                        else
                            "+"
                    }
                }

            }
        }

    }

    class Coordinates(val x: Int, val y: Int) {
        fun isAdjacent(other: Coordinates): Boolean {
            return (x in (other.x - 1)..(other.x + 1))
                    && (y in (other.y - 1)..(other.y + 1))
        }

        fun move(direction: Direction): Coordinates = when (direction) {
            Direction.UP -> Coordinates(x + 1, y)
            Direction.DOWN -> Coordinates(x - 1, y)
            Direction.RIGHT -> Coordinates(x, y + 1)
            Direction.LEFT -> Coordinates(x, y - 1)
        }

        override fun toString() = "[$x,$y]"
        override fun equals(other: Any?): Boolean = when (other) {
            is Coordinates -> (x == other.x) && (y == other.y)
            else -> false
        }

        override fun hashCode(): Int = x.hashCode() + y.hashCode()
    }

    enum class Direction {
        UP, DOWN, LEFT, RIGHT;

        companion object {
            fun decode(s: Char) = when (s) {
                'U' -> UP
                'D' -> DOWN
                'L' -> LEFT
                'R' -> RIGHT
                else -> throw IllegalArgumentException("$s is not a direction")
            }
        }
    }
}
