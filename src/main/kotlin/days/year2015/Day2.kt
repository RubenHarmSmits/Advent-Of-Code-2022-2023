package days.year2015

import days.Day
import kotlin.math.*

fun main() {
    println(Day2().solve())
}
class Day2: Day(2, 2015) {
    var tot = 0

    var input = "";

    var list: MutableList<Int> = mutableListOf()

    fun solve(): Any {
        var a = inputList.map{
            val (l,w,h) = it.split("x").ints().sorted()
            l+l+w+w + (l*w*h)
        }
                .sum()

        return a;
    }


}