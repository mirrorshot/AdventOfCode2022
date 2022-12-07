package com.adventofcode.mirrorshot.calendar

import java.util.*

class Day7 : DaySolver<Long, Long>() {
    override fun problem1(input: ByteArray): Long = extractFileTree(input)
        .flatContent()
        .map { n -> Pair(n.name, n.size()) }
        .filter { p -> p.second <= 100000L }
        .also { p -> println(p) }
        .sumOf { p -> p.second }

    override fun problem2(input: ByteArray): Long {
        val root = extractFileTree(input)
        println("directories: ${root.flatContent().map { n -> "[${n.name}:${n.size()}]" }.joinToString("\n")}")
        println("used space: ${root.size()}")
        val free = 70000000L - root.size()
        println("free: $free")
        val missing = 30000000L - free
        println("missing: $missing")
        return root.flatContent()
            .map { n -> Pair(n.name, n.size()) }
            .filter { p -> p.second > missing }
            .minBy { p -> p.second }
            .also { p -> println(p) }
            .second
    }

    private fun extractFileTree(input: ByteArray): Node.NodeDir =
        parseInput(input).split("\n")
            .filter { l -> l.isNotBlank() }
            .map { l -> decode(l.split(" ")) }
            .let { tree -> compileNodes(tree) }

    private fun compileNodes(triples: List<Triple<Type, String, String?>>): Node.NodeDir {
        val nodes = Stack<Node.NodeDir>()
        var current = Node.NodeDir("/", listOf())
        triples.drop(1).filter { t -> !(t.first == Type.COMMAND && t.second == "ls") }.forEach { t ->
            current = when (t.first) {
                Type.COMMAND -> when (t.third) {
                    ".." -> {
                        println("closing directory at size: ${current.size()}")
                        nodes.pop().add(current)
                    }

                    else -> {
                        println("open directory ${t.third}")
                        nodes.push(current)
                        Node.NodeDir(t.third.toString(), listOf())
                    }
                }

                Type.FILE -> {
                    println("file ${t.second} size: ${t.third}")
                    current.add(Node.NodeFile(t.second, t.third!!.toLong()))
                }

                Type.DIRECTORY -> current
            }
        }

        while (current.name != "/") current = nodes.pop().add(current)

        return current
    }

    private fun decode(line: List<String>): Triple<Type, String, String?> =
        when (line[0]) {
            "${'$'}" -> Triple(Type.COMMAND, line[1], line.getOrNull(2))
            "dir" -> Triple(Type.DIRECTORY, line[1], null)
            else -> Triple(Type.FILE, line[1], line[0])
        }

    enum class Type {
        COMMAND,
        FILE,
        DIRECTORY
    }

    abstract class Node(val name: String) {
        abstract fun size(): Long
        class NodeFile(name: String, val weight: Long) : Node(name) {
            override fun size(): Long = weight
        }

        class NodeDir(name: String, val content: List<Node>) : Node(name) {
            override fun size(): Long =
                content.sumOf { n -> n.size() }

            fun add(node: Node): NodeDir = NodeDir(name, content.plus(node))

            fun flatContent(): List<NodeDir> = content
                .filterIsInstance<NodeDir>()
                .flatMap { n -> listOf(n).plus(n.flatContent()) }
        }
    }

}
