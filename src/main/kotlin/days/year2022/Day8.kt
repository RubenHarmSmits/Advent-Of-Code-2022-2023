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
            ity.forEachIndexed { ix, itx ->
                var score =0;

                val current = itx

                // left
                val ll = ity.subList(0, ix).reversed()
                val score1 = calcAmountOfTrees(ll, current)

                // right
                val lr = ity.subList(ix+1, ity.size)
                val score2= calcAmountOfTrees(lr, current)

                //top
                val lt = grid.map{it[ix]}.subList(0, iy).reversed()
                val score3= calcAmountOfTrees(lt, current)

                //bottom
                val lb = grid.map{it[ix]}.subList(iy+1, grid.size)
                val score4= calcAmountOfTrees(lb, current)

                highest = max(highest, score1 * score2 * score3 * score4)
            }
        }
        return highest;
    }
}

fun calcAmountOfTrees(view:List<Int>, current: Int): Int{
    var s = view.indexOfFirst {it>=current}
    return if(s>=0) s+1 else view.size
}







