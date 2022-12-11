package days.year2022

import days.Day

fun main() {
    Day11().solve()
}

data class Monkey(
    val div: Long,
    val items: MutableList<Long>,
    val operation: String,
    val trow: (num: Long) -> Unit,
    var count: Long = 0
)


var monkeys: MutableList<Monkey> = mutableListOf()

class Day11 : Day(11) {

    fun solve() {
        inputList.splitBy { it == "" }
            .forEach {
                val items = it[1].substringAfter(": ").split(", ").ints().map { it.toLong() }
                val operation = it[2].substringAfter("= ")
                val division = it[3].substringAfter("by ").toLong()
                val t = it[4].substringAfter("monkey ").toLong()
                val f = it[5].substringAfter("monkey ").toLong()

                fun f(num: Long) {
                    monkeys[(if (num % division == 0L) t else f).toInt()].items.add(num)
                }

                val m = Monkey(division, items.toMutableList(), operation, ::f)
                monkeys.add(m)
            }

        val sum = monkeys.map { it.div }.fold(1L) { accumulator, element -> accumulator * element }

        repeat(10000) {

            monkeys.forEach { monkey ->
                monkey.items.forEach { num ->
                    val (_, s, t) = monkey.operation.split(" ")
                    val twice = t == "old"
                    var new = if (s == "*") {
                        num * if (twice) num else t.toLong()
                    } else {
                        num + if (twice) num else t.toLong()
                    }
                    new %= sum
                    monkey.trow(new)
                    monkey.count++
                }
                monkey.items.clear();
            }
        }

        val sorted = monkeys.map { it.count }.sorted().takeLast(2)
        println(sorted[0] * sorted[1])

    }


}



