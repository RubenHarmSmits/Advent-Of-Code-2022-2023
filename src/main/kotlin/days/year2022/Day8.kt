package days.year2022

import days.Day

fun main() {
    println(Day8().solve())
}

class Day8 : Day(8) {

    fun solve(): Int {
        val grid = matrixOf(inputList.map { it.map(Char::digitToInt) })
        return grid.mapMatrixIndexed { y, x, h ->
            val l = grid[y].subList(0, x).reversed()
            val r = grid[y].subList(x + 1, grid.size)
            val t = grid.map { it[x] }.subList(0, y).reversed()
            val b = grid.map { it[x] }.subList(y + 1, grid.size)
            views(l, h) * views(r, h) * views(t, h) * views(b, h)
        }.matrixMax()
    }
}

fun views(view: List<Int>, current: Int): Int {
    val s = view.indexOfFirst { it >= current }
    return if (s >= 0) s + 1 else view.size
}

