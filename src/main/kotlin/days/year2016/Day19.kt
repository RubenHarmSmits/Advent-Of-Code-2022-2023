package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day19().solve())
}

class Day19 : Day(19, 2016) {
    val elfsTotal = 3017957

    var elfs: MutableList<Int> = mutableListOf();

    fun solve(): Any {
        repeat(elfsTotal) {
            elfs.add(it + 1)
        }
        var i = 0;

        while (elfs.size > 1) {
            val indexToRemove = findIndexToRemove(elfs.size, i)
            elfs.removeAt(indexToRemove)
            if(indexToRemove>i)i++
            if(i>=elfs.size) i=0;
        }
        return elfs[0];
    }

    private fun findIndexToRemove(len: Int, i:Int):Int{
        return (i + len/2)%len
    }
}

// too low 513010