package days.year2022

import days.Day
import java.lang.Math.abs
import kotlin.collections.*


fun main() {
    val start = System.currentTimeMillis()
    println(Day15().solve())

    println("time(ms): ")
    print(System.currentTimeMillis() - start)
}

class Day15 : Day(15) {

    fun solve(): Any {
        var MAX = 4000000
        var grid = MutableList(MAX + 1) { mutableListOf<Range>() }
        inputList
            .forEachIndexed { i, it ->
                val (sx, sy, bx, by) = extraxtAllIntsFromString(it)
                val sp = Point(sy, sx)
                val mh = manhattanDistance(sp, Point(by, bx))

                val miny = (sy - mh).coerceAtLeast(0)
                val maxy = (sy + mh).coerceAtMost(MAX)

                for (y in (miny..maxy)) {
                    val dy = abs(y - sy)
                    val minx = (sx - (mh - dy)).coerceAtMost(MAX).coerceAtLeast(0)
                    val maxx = (sx + (mh - dy)).coerceAtMost(MAX).coerceAtLeast(0)
                    grid[y].add(Range(minx, maxx))
                }
            }

        for (y in 0..MAX) {
            for (pair1 in grid[y]) {
                if (grid[y].none { pair2 -> (pair1.end == MAX || (pair1 != pair2 && pair1.end in pair2.begin - 1 until pair2.end)) }) {
                    return (pair1.end + 1).toLong() * MAX.toLong() + y
                }
            }
        }
        return 0
    }
}


