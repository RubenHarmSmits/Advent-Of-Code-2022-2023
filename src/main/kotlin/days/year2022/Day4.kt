package days.year2022

import days.Day


fun main(args: Array<String>) {
    println(Day4().solve())
}


class Day4 : Day(4) {

    private fun cont(it: String): Boolean {
        val (a, b, c, d) = it.split(",", "-").ints()
        return (a..b).intersect(c..d).any();
    }

    fun solve(): Any {
        return inputList.count{cont(it)}
    }

}

