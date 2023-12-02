package days.year2023

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
        var a = inputList.map{it}
                .forEachIndexed{i,it->
                    var list = it.split(" ");
                    if(list.contains("reverse")){

                    }
                    if(list.contains("rotate") && list.contains("based")){

                    }
                    if(list.contains("rotate") && list.contains("left")){

                    }
                    if(list.contains("rotate") && list.contains("right")){

                    }
                    if(list.contains("move")){

                    }
                    if(list.contains("swap") && list.contains("position")){
                        // swap position 4 with position 0
                        val temp = input[list[2].toInt()]
                        input[list[2].toInt()] = list[5][0]
                        input[list[5].toInt()] = temp
                    }
                    if(list.contains("swap") && list.contains("letter")){

                    }

                }
        return input.toString();
    }


}