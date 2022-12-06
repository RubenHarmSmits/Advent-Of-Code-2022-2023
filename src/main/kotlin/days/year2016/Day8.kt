package days.year2016

import days.Day

fun main(args: Array<String>) {
    println(Day8().solve())
}


class Day8 : Day(8, 2016) {

    val w = 50
    val h = 6

    fun solve(): Any {

        var grid = Array(h) { Array(w) { false } }

        printGrid(grid)

        inputList
            .forEach {
                if (it.contains("rect")) {
                    val x = it.substringAfter(' ').substringBefore('x').toInt()
                    val y = it.substringAfter('x').toInt()
                    for (ix in 0 until x) {
                        for (iy in 0 until y) {
                            grid[iy][ix] = true
                        }
                    }
                }
                if (it.contains("column")) {
                    val x = it.substringAfter('=').substringBefore(" by").toInt()
                    val n = it.substringAfter("by ").toInt()
                    var col: Array<Boolean> = grid.map{it[x]}.toTypedArray()
                    var newcol = rotate(col, n)
                    grid = grid.mapIndexed{i,row ->
                        row[x] = newcol[i]
                        row
                    }.toTypedArray()

                }
                if (it.contains("row")) {
                    val y = it.substringAfter('=').substringBefore(" by").toInt()
                    val n = it.substringAfter("by ").toInt()
                    grid[y] = rotate(grid[y], n)
                }
                printGrid(grid)
            }


        return grid.map{it.count{it}}.sum()
    }

    private fun rotate(array: Array<Boolean>, n: Int): Array<Boolean> {
        var _array = array
        for (yy in 0 until n) {
            _array = arrayOf(_array.last()) + _array.sliceArray(0 until _array.size - 1)
        }
        return _array
    }

    private fun printGrid(grid: Array<Array<Boolean>>) {
        println("")
        grid.forEach {
            it.forEach { i -> print(if (i) "#" else ".") }
            println("")
        }
    }

}

