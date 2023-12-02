package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day2().solve())
}

class Day2 : Day(2, 2023) {

    val a = 3 to 5


    fun solve(): Any {
        println(a.first)
        return inputList.map {
            var numbers = it.split(": ")[1];
            var games = numbers.split("; ")
            var maxBlue=0
            var maxGreen=0
            var maxRed=0
            games.forEach {
                val spl = it.replace(",", "").split(" ")
                spl.chunked(2).forEach {
                    val (num, col) = it
                    if(col=="red"){
                        maxRed = max(maxRed, num.toInt())
                    }
                    if(col=="blue"){
                        maxBlue = max(maxBlue, num.toInt())
                    }
                    if(col=="green"){
                        maxGreen = max(maxGreen, num.toInt())
                    }
                }
            }
            maxBlue* maxGreen*maxRed
        }.sum()
    }


}