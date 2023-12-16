package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day15().solve())
}

class Day15 : Day(15, 2023) {
    var tot = 0


    var list: MutableList<MutableList<Pair<String, Int>>> = MutableList(256) { mutableListOf<Pair<String, Int>>() }

    fun solve(): Any {
        inputString.split(",")
                .forEach {
                    val label = it.substringBefore("=").substringBefore("-")
                    val delimiter = it[label.length]
                    val h = getHash(label)
                    if (delimiter == '=') {
                        val len = it.substringAfter("=").toInt()
                        val pair = label to len
                        val foundI = list[h].indexOfFirst { it.first == label }
                        if (foundI >= 0) list[h][foundI] = pair
                        else list[h].add(pair)
                    } else {
                        list[h].removeIf { it.first == label }
                    }
                }
        return list.mapIndexed { i, box ->
            box.mapIndexed { slot, pair ->
                (i + 1) * (slot + 1) * pair.second
            }.sum()
        }.sum()

    };
}

private fun getHash(input: String): Int {
    var current = 0
    input.forEach {
        var a = it.code
        current += a
        current *= 17
        current %= 256
    }
    return current
}



//
//Determine the ASCII code for the current character of the string.
//Increase the current value by the ASCII code you just determined.
//Set the current value to itself multiplied by 17.
//Set the current value to the remainder of dividing itself by 256.