package com.adventofcode.mirrorshot.calendar

import java.math.BigInteger

class Day11 : DaySolver<Long, Long>() {
    override fun problem1(input: ByteArray): Long = Monkeys(parseInput(input))
        .also { monkeys ->
            (1..20)
                .onEach { r ->
//                    println("$monkeys")
                    println("---------------------round $r")
                    monkeys.round()
                }
        }
        .also { m -> println("$m") }
        .monkeys.toList()
        .sortedByDescending { m -> m.second.inspected }
        .take(2)
        .fold(1L) { p, m -> p * m.second.inspected }


    override fun problem2(input: ByteArray): Long = Monkeys(parseInput(input))
        .also { monkeys ->
            (1..10000)
                .onEach { r ->
//                    println("$monkeys")
                    println("---------------------round $r")
                    monkeys.round2()
                }
        }
        .also { m -> println("$m") }
        .monkeys.toList()
        .sortedByDescending { m -> m.second.inspected }
        .take(2)
        .fold(1) { p, m -> p * m.second.inspected }

    class Monkeys(input: String) {
        val monkeys = input.split("\n\n")
            .filter { s -> s.isNotBlank() }
            .map { description -> asMonkey(description) }
            .associateBy { m -> m.num }

        fun round() {
            monkeys.forEach { (_, m) ->
                while (m.items.isNotEmpty()) m.project()
                    .also { p -> monkeys[p.first]!!.receive(p.second) }
            }
        }

        fun round2() {
            monkeys.forEach { (_, m) ->
                while (m.items.isNotEmpty()) m.project2()
                    .also { p -> monkeys[p.first]!!.receive(p.second) }
            }
        }

        private fun asMonkey(description: String): Monkey =
            description.split("\n")
                .let { l ->
                    Monkey(
                        l[0].split(" ")[1].split(":")[0].toInt(),
                        l[1].split(":")[1].split(",").map { i -> i.trim().toBigInteger() }.toMutableList(),
                        defineOperation(l[2]),
                        l[3].split("by")[1].trim().toBigInteger(),
                        l[4].split(" ").last().toInt(),
                        l[5].split(" ").last().toInt()
                    )
                }

        private fun defineOperation(s: String): (BigInteger) -> BigInteger = s.split("=")[1].trim().split(" ")
            .let { o ->
                when {
                    o[1] == "+" && o[2] == "old" -> { x: BigInteger -> x.plus(x) }
                    o[1] == "+" -> { x: BigInteger -> x.plus(o[2].toBigInteger()) }
                    o[1] == "-" && o[2] == "old" -> { x: BigInteger -> x.minus(x) }
                    o[1] == "-" -> { x: BigInteger -> x.minus(o[2].toBigInteger()) }
                    o[1] == "*" && o[2] == "old" -> { x: BigInteger -> x.times(x) }
                    o[1] == "*" -> { x: BigInteger -> x.times(o[2].toBigInteger()) }
                    o[1] == "/" && o[2] == "old" -> { x: BigInteger -> x.div(x) }
                    o[1] == "/" -> { x: BigInteger -> x.div(o[2].toBigInteger()) }
                    else -> throw IllegalArgumentException("$o is not an operation")
                }
            }

        override fun toString(): String = monkeys.map { e -> "${e.value}" }.joinToString("\n")
    }

    class Monkey(
        val num: Int,
        val items: MutableList<BigInteger>,
        val operation: (BigInteger) -> BigInteger,
        private val test: BigInteger,
        private val monkeyTrue: Int,
        private val monkeyFalse: Int
    ) {
        var inspected = 0L
        fun receive(item: BigInteger) {
            items.add(item)
        }

        fun project(): Pair<Int, BigInteger> =
            items.removeAt(0)
                .let { item -> operation(item).div(BigInteger.valueOf(3)) }
                .let { item ->
                    Pair(
                        if (item.mod(test) == BigInteger.ZERO) monkeyTrue else monkeyFalse,
                        item
                    )
                }
                .also { inspected++ }

        fun project2(): Pair<Int, BigInteger> =
            operation(items.removeAt(0))
                .let { item ->
                    Pair(
                        if (item.mod(test) == BigInteger.ZERO) monkeyTrue else monkeyFalse,
                        item
                    )
                }
                .also { inspected++ }

        override fun toString(): String = "Monkey: $num - inspections $inspected - items: ${items.joinToString(", ")}"
    }

}
