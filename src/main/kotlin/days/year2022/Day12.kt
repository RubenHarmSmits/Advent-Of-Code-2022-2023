package days.year2022

import days.Day
import java.lang.Integer.min

fun main() {
    Day12().solve()
}

class Day12 : Day(12) {

    var start: Point = Point(0, 0)
    var end: Point = Point(0, 0)

    var grid = inputList.mapIndexed { rowI, row ->
        row.toMutableList().mapIndexed { i, n ->
            when (n) {
                'S' -> {
                    start = Point(rowI, i)
                    'a'
                }

                'E' -> {
                    end = Point(rowI, i)
                    'z'
                }

                else -> n
            }
        }.map { it.code - 97 }
    }.toMutableMatrix()

    fun solve() {
        var lowest = Int.MAX_VALUE;
        for (y in 0 until grid.size) {
            for (x in 0 until grid[0].size) {
                if (grid[y][x] == 0) {
                    val pathSize = grid.bfs(Point(y, x), end).size -1
                    lowest = min(lowest, pathSize)
                }
            }
        }
        print(lowest)
    }


}



