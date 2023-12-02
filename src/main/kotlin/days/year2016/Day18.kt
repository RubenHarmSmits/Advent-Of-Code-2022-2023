package days.year2023

import days.Day

fun main() {
    println(Day18().solve())
}
class Day18: Day(18, 2016) {
    var tot = 0

    var input = "^^.^..^.....^..^..^^...^^.^....^^^.^.^^....^.^^^...^^^^.^^^^.^..^^^^.^^.^.^.^.^.^^...^^..^^^..^.^^^^";

    var list: MutableList<Int> = mutableListOf()

    fun solve(): Any {

        repeat(400000){
            println(input)
            var newInput = ""
            tot+= input.count { it == '.' }
            for(i in input.indices){
                val leftIsSafe = i == 0 || input[i-1] == '.'
                val middleIsSafe = input[i] == '.'
                val rightIsSafe = i == input.length-1 || input[i+1] == '.'
                newInput += if(isTrap(leftIsSafe, middleIsSafe, rightIsSafe)) "^" else "."
            }
            input = newInput
        }
        return tot;
    }

    private fun isTrap(leftIsSafe: Boolean, middleIsSafe: Boolean, rightIsSafe: Boolean): Boolean {
        if(!leftIsSafe && !middleIsSafe && rightIsSafe) return true
        if(leftIsSafe && !middleIsSafe && !rightIsSafe) return true
        if(!leftIsSafe && middleIsSafe && rightIsSafe) return true
        if(leftIsSafe && middleIsSafe && !rightIsSafe) return true
        return false;
    }


}