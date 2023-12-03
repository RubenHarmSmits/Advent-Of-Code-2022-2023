package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day3().solve())
}

// Warning: Ugly code, please don't read
class Day3 : Day(3, 2023) {
    var tot = 0
    fun solve(): Any {
        val matrix = matrixOfInput(inputList)
        var mem = mutableMapOf<Point, MutableList<Int>>()

        inputList.forEachIndexed { y, line ->
            var list = line.split(Regex("\\D+")).filter { isNumeric(it) }
            var prev = 0
            list.forEachIndexed { i, it ->
                val x = if (prev > 2) line.substring(prev + 3).indexOf(it) + prev + 3 else line.indexOf(it)
                prev = x
                var adj = mutableListOf<Point>()
                for (l in it.indices) {
                    adj.addAll(matrix.getSurroundingCoordinates(y, x + l))
                }
                adj = adj.toSet().toMutableList()
                adj.forEach { point ->
                    if (!(isNumeric(matrix.get(point).toString()) || matrix.get(point) == '.')) {
                        if (mem.containsKey(point)) mem[point]?.add(it.toInt())
                        else mem[point] = mutableListOf(it.toInt())
                    }
                }

                if (!(adj.all { isNumeric(matrix.get(it).toString()) || matrix.get(it) == '.' })) {
                    tot += it.toInt()
                }
            }
        }

        return mem.values.map { it.toSet().toMutableList() }.filter { it.size == 2 }.sumOf { it[0] * it[1] }
    }
}
