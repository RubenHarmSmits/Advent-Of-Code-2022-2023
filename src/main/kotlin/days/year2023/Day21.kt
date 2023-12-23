package days.year2023

import days.Day
import days.Matrix
import kotlin.math.*

fun main() {
    println(Day21().solve())
}

class Day21 : Day(21, 2023) {

    val grid = matrixOfInput(inputList).toMutableMatrix()

    fun solve(): Any {
        var inc = mutableListOf<Int>()
        val startPoint = Point(65, 65)
        var steps = mutableSetOf(startPoint to Point(0,0))

        repeat(65) {
            steps = move1step(steps)
        }

        repeat(6) {
            repeat(131) {
                steps = move1step(steps)
            }
            inc.add(steps.size)

        }

        println(inc)
        val secondOrder = inc.zipWithNext { a, b -> b - a }
        println(secondOrder)
        val thirdOrder = secondOrder.zipWithNext { a, b -> b - a }
        println(thirdOrder)
        val fourthdOrder = thirdOrder.zipWithNext { a, b -> b - a }
        println(fourthdOrder)

        val x = 202300 - 5L


        return calculate(x);
    }

    private fun move1step(steps: MutableSet<Pair<Point, Point>>): MutableSet<Pair<Point, Point>> {
        var nstepss = mutableSetOf<Pair<Point, Point>>()
        steps.forEach { step ->
            nstepss.addAll(grid.getAdjacentCoordinates2(step).filter { grid[it.first.y][it.first.x] == '.' })
        }
        return nstepss
    }




    fun calculate(x: Long): Long{
        return 313365L + ((x+1L) * 154627L) + (30906L  * summation(x))
    }
}

