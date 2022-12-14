package com.adventofcode.mirrorshot.calendar

import java.math.BigInteger

class Day11 : DaySolver<Long, Long>() {
    override fun problem1(input: ByteArray): Long = Monkeys(parseInput(input))
        .also { monkeys ->
            repeat(20) { r ->
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
            repeat(10000) { r ->
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
            .map { description -> Monkey.make(description) }
            .associateBy { m -> m.num }

        private val limiter: BigInteger = monkeys.values.fold(BigInteger.ONE) { p, t -> p.times(t.test) }

        fun round() {
            monkeys.forEach { (_, m) ->
                while (m.items.isNotEmpty()) m.project()
                    .also { p -> monkeys[p.first]!!.receive(p.second) }
            }
        }

        fun round2() {
            monkeys.forEach { (_, m) ->
                while (m.items.isNotEmpty()) m.project2(limiter)
                    .also { p -> monkeys[p.first]!!.receive(p.second) }
            }
        }

        override fun toString(): String = monkeys.map { e -> "${e.value}" }.joinToString("\n")
    }

    class Monkey(
        val num: Int,
        val items: MutableList<BigInteger>,
        val operation: (BigInteger) -> BigInteger,
        val test: BigInteger,
        private val monkeyTrue: Int,
        private val monkeyFalse: Int
    ) {
        var inspected = 0L
        fun receive(item: BigInteger) {
            items.add(item)
        }

        fun project(): Pair<Int, BigInteger> =
            operation(items.removeAt(0))
                .div(BigInteger.valueOf(3))
                .let { item -> Pair(nextMonkey(item), item) }
                .also { inspected++ }

        fun project2(limiter: BigInteger): Pair<Int, BigInteger> =
            operation(items.removeAt(0))
                .mod(limiter)
                .let { item ->
                    Pair(
                        if (item.mod(test) == BigInteger.ZERO) monkeyTrue else monkeyFalse,
                        item
                    )
                }
                .also { inspected++ }

        fun nextMonkey(item: BigInteger): Int = if (item isDivisibleBy test) monkeyTrue else monkeyFalse

        infix fun BigInteger.isDivisibleBy(divisor: BigInteger) = this.mod(test) == BigInteger.ZERO
        override fun toString(): String = "Monkey: $num - inspections $inspected - items: ${items.joinToString(", ")}"

        companion object {
            fun number(s: String) = s.split(" ")[1]
                .split(":")[0]
                .toInt()

            fun operation(s: String): (BigInteger) -> BigInteger = s.split("=")[1]
                .trim()
                .split(" ")
                .let { o ->
                    when {
                        o[1] == "+" -> { x: BigInteger -> x.plus(o[2].toBigIntegerOrNull() ?: x) }
                        o[1] == "-" -> { x: BigInteger -> x.minus(o[2].toBigIntegerOrNull() ?: x) }
                        o[1] == "*" -> { x: BigInteger -> x.times(o[2].toBigIntegerOrNull() ?: x) }
                        o[1] == "/" -> { x: BigInteger -> x.div(o[2].toBigIntegerOrNull() ?: x) }
                        else -> throw IllegalArgumentException("$o is not an operation")
                    }
                }

            fun test(s: String) = s.split("by")[1]
                .trim()
                .toBigInteger()

            fun items(s: String) = s.split(":")[1].split(",")
                .map { i -> i.trim().toBigInteger() }
                .toMutableList()

            fun positive(s: String) = s.split(" ")
                .last()
                .trim()
                .toInt()

            fun negative(s: String) = s.split(" ")
                .last()
                .trim()
                .toInt()

            fun make(description: String): Monkey =
                make(description.split("\n")
                    .filter { s -> s.isNotBlank() })

            fun make(descriptionLines: List<String>) =
                Monkey(
                    number(descriptionLines[0]),
                    items(descriptionLines[1]),
                    operation(descriptionLines[2]),
                    test(descriptionLines[3]),
                    positive(descriptionLines[4]),
                    negative(descriptionLines[5])
                )

        }
    }

}
