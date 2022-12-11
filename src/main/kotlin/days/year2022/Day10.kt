package days.year2022

import days.Day

fun main() {
    Day10().solve()
}

class Day10 : Day(10) {

    var x = 1
    var cycle = 1
    var grid = matrixOf(MutableList(7) { MutableList(40) { '.' } }).toMutableMatrix()

    fun solve() {
        inputList
            .forEach {
                var s = it.split(" ")
                move()
                if (s.size == 2) {
                    move()
                    x += s[1].toInt()
                }
            }
        grid.print()

    }

    fun move() {
        grid[cycle/40][(cycle-1)%40] = if(kotlin.math.abs((cycle-1) % 40 - x) <2) '#' else '.'
        cycle++
    }

}



