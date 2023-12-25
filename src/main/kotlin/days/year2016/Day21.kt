package days.year2016

import days.Day
import kotlin.math.*

fun main() {
    println(Day21().solve())
}

class Day21 : Day(21, 2016) {
    var tot = 0

    var input = "abcde".toCharArray();

    var list: MutableList<Int> = mutableListOf()

    fun solve(): Any {
        var a = inputList.map { it }
                .forEachIndexed { i, it ->
                    var list = it.split(" ");
                    if (list.contains("reverse")) {
                        // reverse positions 0 through 4
                        val (x, xx, firstIndex, xxx, secondIndex) = list
                        var str = input.joinToString("")
                        val begin = str.substring(0, firstIndex.toInt()) + str.substring(firstIndex.toInt(), secondIndex.toInt()+1).reversed() + str.substring(secondIndex.toInt()+1)
                        input = begin.toCharArray()

                    }
                    if (list.contains("rotate") && list.contains("based")) {

                    }
                    if (list.contains("rotate") && list.contains("left")) {
                        // rotate left 1 step
                        val steps = list[2]


                    }
                    if (list.contains("rotate") && list.contains("right")) {

                    }
                    if (list.contains("move")) {

                    }
                    if (list.contains("swap") && list.contains("position")) {
                        // swap position 4 with position 0
                        val temp = input[list[2].toInt()]
                        input[list[2].toInt()] = input[list[5].toInt()]
                        input[list[5].toInt()] = temp
                    }
                    if (list.contains("swap") && list.contains("letter")) {
                        // swap letter d with letter b
                        // "abcde"
                        val indexoffirst = input.indexOf(list[2][0])
                        val indexofsecond = input.indexOf(list[5][0])
                        val temp = input[indexoffirst]
                        input[indexoffirst] = input[indexofsecond]
                        input[indexofsecond] = temp
                    }
                    println(input)

                }
        return input.toString();
    }


}