package days.year2016

import days.Day

fun main() {
    println(Day9().solve())
}


class Day9 : Day(9, 2016) {

    fun solve(): Any {
        return nextMarker(inputString, 0) -1
    }

    fun nextMarker(line: String, total: Long): Long {
        if(line.isEmpty()) return total
        return if(line[0]=='('){
            val markerIndex = line.indexOf(")")
            var action = line.substringAfter("(").substringBefore(")")
            val letters = action.substringBefore("x").toInt()
            val repetitions = action.substringAfter("x").toInt()
            nextMarker(line.substring(markerIndex + letters + 1), total + (repetitions * nextMarker(line.substring(markerIndex+1, markerIndex+letters+1),0 ))        )
        } else {
            nextMarker(line.substring(1), total + 1)
        }
    }

}

