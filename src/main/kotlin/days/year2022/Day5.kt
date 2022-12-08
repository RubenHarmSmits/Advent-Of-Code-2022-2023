package days.year2022

import days.Day


fun main() {
    println(Day5().solve())
}


class Day5 : Day(5) {

    fun solve(): Any {
        val a = inputList.splitBy { it == "" }
        val stacks = makeStacks(a[0])
        a[1].forEach {
            val b = it.split(" ")
            val move = b[1].toInt()
            val from = b[3].toInt()
            val to = b[5].toInt()
            val c = stacks[from - 1]
            val t = stacks[to - 1]
            val newL = mutableListOf<String>()
            repeat (move) {
                newL.add((c as MutableList).removeLast())
            }
            (t as ArrayList).addAll(newL.reversed())
        }

        return stacks?.map{it.last()} ?: "";
    }

    private fun makeStacks(initial: List<String>): List<List<String>> {
        var initialR = initial.map { it.split(" ") }
        return transpose(initialR).map { it.reversed().filter { it != "[X]" } }
    }

}


