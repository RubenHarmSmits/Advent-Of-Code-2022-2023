package days.year2016

import days.Day
import kotlin.math.*

fun main() {
    println(Day20().solve())
}
class Day20: Day(20, 2016) {
    var max = 4294967295;
    fun solve(): Any {
        val list = inputList
                .map {
                    it.split("-")
                            .map { it.toLong() }
                            .map { it }
                }

        return (0..max).count { i ->
            (list.all { i < it[0] || i > it[1] })
        }

    }


}