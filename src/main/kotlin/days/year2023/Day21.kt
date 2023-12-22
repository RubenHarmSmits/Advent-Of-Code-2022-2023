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
//        val startPoint = Point(5, 5)
        var steps = mutableSetOf(startPoint to Point(0,0))


//
//
//        repeat(65){
//            var nstepss = mutableSetOf<Pair<Point,Point>>()
//            steps.forEach {step ->
//                nstepss.addAll(grid.getAdjacentCoordinates2(step).filter{grid[it.first.y][it.first.x]=='.'})
//            }
//            steps = nstepss
//        }
//
//        println(3333)
//
//        repeat(6){
//            println(3333)
//
//            repeat(131){
//                var nsteps = mutableSetOf<Pair<Point,Point>>()
//                steps.forEach {step ->
//                    nsteps.addAll(grid.getAdjacentCoordinates2(step).filter{grid[it.first.y][it.first.x]=='.'})
//                }
//                steps = nsteps
//            }
//            inc.add(steps.size)
//
//        }



        println(inc)
        val secondOrder = inc.zipWithNext { a, b -> b - a }
        println(secondOrder)
        val thirdOrder = secondOrder.zipWithNext { a, b -> b - a }
        println(thirdOrder)

        val fourthdOrder = thirdOrder.zipWithNext { a, b -> b - a }
        println(fourthdOrder)

//        for(x in 1..50){
        val x = 202300 - 5L
//            println(1196 + ((x+1) * 718) + (162  * sum(x)))
//        }


        return calculate(x);
    }


    fun sum(x: Long): Long{
        return x * (x + 1L) / 2L
    }

    fun calculate(x: Long): Long{
//        return 1256 + ((x+1) * 732) + (162  * sum(x))
        return 313365L + ((x+1L) * 154627L) + (30906L  * sum(x))
//        return 1196 + ((x+1) * 718) + (162  * sum(x))
    }




}


// too high 632514208796405

