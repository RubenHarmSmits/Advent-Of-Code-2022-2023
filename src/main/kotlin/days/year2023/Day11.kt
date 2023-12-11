package days.year2023

import days.Day

fun main() {
    println(Day11().solve())
}

class Day11 : Day(11, 2023) {

    var grid = matrixOfInput(inputList)

    var emptyY = mutableListOf<Int>()
    var emptyX = mutableListOf<Int>()

    var galaxies = mutableListOf<PointL>()

    fun solve(): Any {
        grid.forEachIndexed { y, line ->
            if (line.all { it == '.' }) {
                emptyX.add(y)
            }
        }

        grid.transposed().forEachIndexed { y, line ->
            if (line.all { it == '.' }) {
                emptyY.add(y)
            }
        }


        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, char ->
                if (char == '#') galaxies.add(calculateExpandedGalaxy(y, x))
            }
        }

        var tot = 0L

        galaxies.forEach { gal1 ->
            galaxies.forEach { gal2 ->
                if (gal1 != gal2) {
                    tot += manhattanDistance(gal1, gal2)
                }
            }
        }

        return tot / 2
    }

    private fun calculateExpandedGalaxy(y: Int, x: Int): PointL {
        val county = emptyX.count { it < y }
        val countx = emptyY.count { it < x }
        val n = 1000000 - 1
        return PointL((y + county * n).toLong(), (x + (countx * n)).toLong())
    }


}