package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day9().solve())
}

class Day9 : Day(9, 2023) {
    var tot = 0

    var input = "";

    var list: MutableList<Int> = mutableListOf()

    fun solve(): Any {
        var a = inputList.map { it.split(" ").map{it.toLong()} }
                .sumOf {
                    val pair = findNextValue(it, true)
//                    println(pair.second)
//                    println(pair.first)
                    if(pair.second) pair.first
                    else 0L
                }
        return a;
    }

    private fun findNextValue(list: List<Long>, isValid: Boolean): Pair<Long, Boolean> {
        val neigbours = list.windowed(2, 1) { it[0] to it[1] }
        var diffs = neigbours.map { it.first - it.second }
//        println(list)
//        println(neigbours)
//        println(diffs)
//        println()
        if(diffs.isEmpty()){
            return Pair(0L, false)
        }
        else if (diffs.all { it == 0L }){
            return Pair(list.last(), true)
        }
        else{
            val pair = findNextValue(diffs, isValid)
            return Pair(list.last() + pair.first, pair.second)
        }
    }


}

// 1909895000
// 775057141
// 684821788