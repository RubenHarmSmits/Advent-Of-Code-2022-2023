package days.year2022

import days.Day

fun main() {
    println(Day25().solve())
}

class Day25 : Day(25) {

    val map = mapOf(Pair('2', 2), Pair('1', 1), Pair('0', 0), Pair('-', -1), Pair('=', -2))
    val factors = generateSequence(1L) { it * 5 }.take(20).toList()

    fun solve(): Any {
        val total = inputList.sumOf { parse(it) }
        val starti = factors.indexOf(factors.closestValue(total))
        return reverse(total, starti, "")
    }

    private fun reverse(total: Long, i: Int, ans: String): String {
        if (i < 0) return ans
        val factor = factors[i]
        return if (factor * 1.5 < total) reverse(total - 2 * factor, i - 1, ans + "2")
        else if (factor * 0.5 < total) reverse(total - factor, i - 1, ans + "1")
        else if (-factor * 0.5 < total) reverse(total, i - 1, ans + "0")
        else if (-factor * 1.5 < total) reverse(total + factor, i - 1, ans + "-")
        else reverse(total + 2 * factor, i - 1, ans + "=")
    }

    private fun parse(number: String): Long {
        return number.reversed().mapIndexed { i, num ->
            factors[i] * map[num]!!
        }.sum()
    }

}


// total = 36897937135836

