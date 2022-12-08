package days.year2022

import days.Day
import java.lang.Integer.max


fun main(args: Array<String>) {
    println(Day8().solve())
}

class Day8: Day(8) {

    fun solve(): Any {
        var highest = 0
        val grid = inputList.map { it.split("").filter { it != "" }.ints()}

        grid.forEachIndexed { iy, ity ->
            ity.forEachIndexed { ix, it ->
                // left
                val l = ity.subList(0, ix).reversed()
                // right
                val r = ity.subList(ix+1, ity.size)
                //top
                val t = grid.map{it[ix]}.subList(0, iy).reversed()
                //bottom
                val b = grid.map{it[ix]}.subList(iy+1, grid.size)

                highest = max(highest, views(l, it) * views(r, it) * views(t, it) * views(b, it))
            }
        }
        return highest;
    }
}

fun views(view:List<Int>, current: Int): Int{
    var s = view.indexOfFirst {it>=current}
    return if(s>=0) s+1 else view.size
}