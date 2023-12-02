package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day1().solve())
}
class Day1: Day(1, 2023) {
    var tot = 0

    var input = "";

    var list: MutableList<Int> = mutableListOf()

    //54303

//    fun solve(): Any {
//        var a = inputList.map{
//            var temp = it
//            val one = it.indexOf("one")
//
//            val first = getFirstDigit(temp)
//            val second = getFirstDigit(temp.reversed())
//            val b =(""+first+second).toInt()
//            println(b)
//            b
//        }.sum()
//
//        return a;
//    }

    fun solve(): Any {
        var a = inputList.map{
            var temp = it
            temp = temp.replace("one", "one1one")
            temp = temp.replace("two", "two2two")
            temp =temp.replace("three", "three3three")
            temp =temp.replace("four", "four4four")
            temp =temp.replace("five", "five5five")
            temp =temp.replace("six", "six6six")
            temp =temp.replace("seven", "seven7seven")
            temp =temp.replace("eight", "eight8eight")
            temp =temp.replace("nine", "nine9nine")
            val first = getFirstDigit(temp)
            val second = getFirstDigit(temp.reversed())
            val b =(""+first+second).toInt()
            println(b)
            b
        }.sum()

        return a;
    }

    fun getFirstDigit(input: String): Char {
        for ((i, char) in input.withIndex()) {
            if (char.isDigit()) {
                // Return the first digit found
                return char
            }
        }
        // Return null if no digit is found
        return 'A'
    }



}

