package com.adventofcode.mirrorshot

class Coordinates(val x: Int, val y: Int) {
    fun isAdjacent(other: Coordinates): Boolean {
        return (x in ((other.x - 1)..(other.x + 1)))
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
