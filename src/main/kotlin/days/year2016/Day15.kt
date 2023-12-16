package days.year2016

import days.Day
import kotlin.math.*

fun main() {
    println(Day15().solve())
}

class Day15 : Day(15, 2016) {
    var tot = 0

    var list: MutableList<Int> = mutableListOf()

    fun solve(): Any {
        var discs: List<Disc> = inputList.map {
            val spl = it.split(" ")
            val positions = spl[3]
            val position = spl[11].dropLast(1);
            val disc = Disc(positions.toInt(), position.toInt())
            disc;
        }
        println(3649478-(563213 - 122318))
        var found = false
        var i = 122318
        while (!found) {
            val cop = discs.map { it.copy() }
            cop.forEach { it.move(i) }
            var dept = 0;
            found = cop.all {
                dept++;
                it.move(dept);
                it.position == 0
            }
            i += 563213 - 122318;

        }

        return i ;
    }


}

data class Disc(val positions: Int, var position: Int) {
    fun move(n: Int) {
        repeat(n) {
            if (position == positions - 1) position = 0;
            else position++
        }
    }
}


