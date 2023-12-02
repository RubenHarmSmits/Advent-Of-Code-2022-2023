package days.year2016

import days.Day
import kotlin.math.min

fun main() {
    val start = System.currentTimeMillis()
    println(Day13().partOne())
    println("MILISECONDS: " + (System.currentTimeMillis() - start))
}

class Day13 : Day(13, 2016) {

    val length = 50

    val fn = 1358;
    var fastest = Int.MAX_VALUE
    val des = Point(39, 31)
    val cur = Point(1, 1)

    val globalMem: MutableList<Point> = mutableListOf();

    val grid = matrixOf(List(length) { List(length) { "." } }).toMutableMatrix()

    fun partOne(): Any {
        prepare()
        grid.print();

        checkQuickestPath(cur, mutableListOf())

        return globalMem.size

    }

    private fun checkQuickestPath(cur: Point, memory: MutableList<Point>) {
        if (memory.contains(cur) || memory.size>50) return
        memory.add(cur)
        if(!globalMem.contains(cur)) globalMem.add(cur)
        val options: List<Point> = getOptions(cur)
        options.forEach {
            checkQuickestPath(it, memory.toMutableList())
        }
    }

    private fun getOptions(cur: Point): List<Point> {
        val options: MutableList<Point> = mutableListOf();
        if (cur.y < grid.size - 1 && grid[cur.y + 1][cur.x] == ".") options.add(Point(cur.y + 1, cur.x))
        if (cur.x < grid[0].size - 1 && grid[cur.y][cur.x + 1] == ".") options.add(Point(cur.y, cur.x + 1))
        if (cur.y > 0 && grid[cur.y - 1][cur.x] == ".") options.add(Point(cur.y - 1, cur.x))
        if (cur.x > 0 && grid[cur.y][cur.x - 1] == ".") options.add(Point(cur.y, cur.x - 1))
        return options
    }

    private fun prepare() {
        for (y in 0 until length) {
            for (x in 0 until length) {
                grid[y][x] = isWall(y, x)
            }
        }
    }

    private fun isWall(y: Int, x: Int): String {
        val find = (x * x) + (3 * x) + (2 * x * y) + y + (y * y) + fn
        val bits = Integer.toBinaryString(find)
        return if (bits.count { it == '1' } % 2 == 0) "." else "#"
    }


}




