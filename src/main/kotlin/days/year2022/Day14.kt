package days.year2022

import days.Day
import java.lang.Integer.max
import java.lang.Integer.min


fun main() {
    println(Day14().solve())
}


class Day14 : Day(14) {

    var grid = MutableList(700) { MutableList(700) { '.' } }
    var highestY = 0

    fun solve(): Int {
        setUpGrid()
        grid = grid.mapIndexed { i, row ->
            if (i == highestY + 2) {
                row.map { '#' }
            } else row
        } as MutableList<MutableList<Char>>
        return letSandFill()

    }

    private fun letSandFill(): Int {
        var sand = 0
        var abbys = false
        val sandStart = Point(0, 500)
        while (!abbys) {
            var rest = false
            var y = sandStart.y
            var x = sandStart.x
            while (!rest) {
                y++
                if (grid[y][x] != '.') {
                    if (grid[y][x - 1] != '.') {
                        if (grid[y][x + 1] != '.') {
                            grid[y - 1][x] = 'o'
                            if (Point(y - 1, x) == sandStart) {
                                abbys = true
                            }
                            rest = true
                        } else {
                            x++
                        }
                    } else {
                        x--
                    }
                }
            }
            sand++
        }
        return sand
    }

    private fun setUpGrid() {
        inputList.forEach {
            val ops = it.split(" -> ")
            for (i in 0 until ops.size - 1) {
                val (fx, fy) = ops[i].split(",").ints()
                val (sx, sy) = ops[i + 1].split(",").ints()
                for (y in min(fy, sy)..max(fy, sy)) {
                    highestY = max(highestY, y)
                    for (x in min(fx, sx)..max(fx, sx)) {
                        grid[y][x] = '#'
                    }
                }
            }
        }
    }

}

