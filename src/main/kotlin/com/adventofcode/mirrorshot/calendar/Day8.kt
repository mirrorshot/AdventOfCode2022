package com.adventofcode.mirrorshot.calendar

class Day8 : DaySolver<Int, Int>() {
    override fun problem1(input: ByteArray): Int {
        val area = parseInput(input).split("\n")
            .filter { s -> s.isNotBlank() }
            .map { s -> s.map { c -> c.digitToInt() } }
        return area.flatMapIndexedTo(mutableListOf()) { i, s ->
            List(s.size) { j -> Triple(i, j, isVisible(i, j, area)) }
        }
            .also { t -> println(t) }
            .count { t -> t.third }
    }

    override fun problem2(input: ByteArray): Int {
        val forest = Forest(parseInput(input).split("\n")
            .filter { s -> s.isNotBlank() }
            .mapIndexed { x, s -> s.mapIndexed { y, c -> Tree(x, y, c.digitToInt()) } })
        return forest.getTrees()
            .map { t -> View(forest, t) }
            .onEach { v -> println(v) }
            .maxOf { v -> v.score }
    }

    private fun isVisible(i: Int, j: Int, area: List<List<Int>>): Boolean =
        if (i == 0 || j == 0 || i == area.size - 1 || j == area[0].size) {
            true
        } else {
            listOf(
                Pair(Direction.UP, i),
                Pair(Direction.DOWN, area.size - 1 - i),
                Pair(Direction.LEFT, j),
                Pair(Direction.RIGHT, area[0].size - j)
            )
                .sortedBy { p -> p.second }
                .any { p -> isVisible(i, j, area, p.first, area[i][j]) }
        }

    private fun isVisible(i: Int, j: Int, area: List<List<Int>>, direction: Direction, height: Int): Boolean =
        when (direction) {
            Direction.UP -> (0 until i).all { x -> area[x][j] < height }
            Direction.DOWN -> (i + 1 until area.size).all { x -> area[x][j] < height }
            Direction.LEFT -> (0 until j).all { y -> area[i][y] < height }
            Direction.RIGHT -> (j + 1 until area[0].size).all { y -> area[i][y] < height }
        }

    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }

    class Tree(val x: Int, val y: Int, val height: Int) {
        override fun toString(): String = "[$x,$y]$height"
    }

    class Forest(private val area: List<List<Tree>>) {
        private val height = area.size - 1
        private val width = area[0].size - 1

        fun getTree(x: Int, y: Int) = area[x][y]

        fun sight(tree: Tree, direction: Direction): List<Tree> {
            val trees: List<Tree> = when (direction) {
                Direction.UP -> (tree.x - 1).downTo(0)
                    .map { x -> getTree(x, tree.y) }

                Direction.DOWN -> (tree.x + 1..height)
                    .map { x -> getTree(x, tree.y) }

                Direction.LEFT -> (tree.y - 1).downTo(0)
                    .map { y -> getTree(tree.x, y) }

                Direction.RIGHT -> (tree.y + 1..width)
                    .map { y -> getTree(tree.x, y) }
            }
            return trees.indexOfFirst { t -> t.height >= tree.height }
                .let { last -> if (last == -1) trees else trees.subList(0, last + 1) }
        }

        fun getTrees() = area.flatten()
    }

    class View(forest: Forest, private val tree: Tree) {

        private val up = forest.sight(tree, Direction.UP)
        private val down = forest.sight(tree, Direction.DOWN)
        private val left = forest.sight(tree, Direction.LEFT)
        private val right = forest.sight(tree, Direction.RIGHT)

        val score: Int = up.size * down.size * left.size * right.size

        override fun toString(): String = "$tree -  UP $up, DOWN $down, LEFT $left, RIGHT $right - view: $score"

    }

}
