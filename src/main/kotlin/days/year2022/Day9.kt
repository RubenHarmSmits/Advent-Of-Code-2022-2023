package days.year2022

import days.Day
import kotlin.math.sign

fun main() {
    println(Day9().solve())
}

class Day9 : Day(9) {

    fun solve(): Any {
        val history = mutableListOf<Point>()

        val knot = MutableList(10) { Point(0, 0) }
        history.add(knot[9].copy())

        inputList
            .forEach {
                val (dir, n) = it.split(" ")
                for (i in 0 until n.toInt()) {
                    knot[0].move(dir[0])

                    for (i in 0 until 9) {
                        if (kotlin.math.abs(knot[i].x - knot[i + 1].x) > 1 || kotlin.math.abs(knot[i].y - knot[i + 1].y) > 1) {
                            if (knot[i].x == knot[i + 1].x) knot[i + 1].y += (knot[i].y - knot[i + 1].y) / 2
                            else if (knot[i].y == knot[i + 1].y) knot[i + 1].x += (knot[i].x - knot[i + 1].x) / 2
                            else {
                                knot[i + 1].move(if (knot[i].y > knot[i + 1].y) 'U' else 'D')
//                                knot[i + 1].y += sign(knot[i].y - knot[i + 1].y)
                                knot[i + 1].move(if (knot[i].x > knot[i + 1].x) 'R' else 'L')
                            }
                        }
                    }
                    history.add(knot[9].copy())
                }
            }
        return history.distinctBy { Pair(it.x, it.y) }.size
    }
}



