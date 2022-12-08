package days.year2022

import days.Day

fun main() {
    println(Day8().solve())
}

class Day8 : Day(8) {

    fun solve(): Int? {
        val grid = inputList.map { it.map(Char::digitToInt) }
        return grid.mapIndexed { iy, ity ->
            ity.mapIndexed { ix, it ->
                val l = ity.subList(0, ix).reversed()
                val r = ity.subList(ix + 1, ity.size)
                val t = grid.map { it[ix] }.subList(0, iy).reversed()
                val b = grid.map { it[ix] }.subList(iy + 1, ity.size)

                views(l, it) * views(r, it) * views(t, it) * views(b, it)
            }.maxOrNull() ?: 0
        }.maxOrNull()
    }
}

fun views(view: List<Int>, current: Int): Int {
    val s = view.indexOfFirst { it >= current }
    return if (s >= 0) s + 1 else view.size
}

