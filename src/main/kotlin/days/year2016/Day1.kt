package days.year2016

import days.Day

fun main(args: Array<String>) {
    println(Day1().partOne())
}
class Day1: Day(1, 2016) {

    fun partOne(): Any {
        return inputList.forEach {
            val sector = it.filter { it.isDigit() }
            val name = it.substringBefore(sector).replace("-", "")
            val checkSum = it.substringAfter('[').dropLast(1)
            println(getName(name, sector.toInt()) +  sector)
            isRealRoom(name, checkSum)
        }
    }

    private fun getName(name: String, sector: Int): String {
        return name.toCharArray()
            .map {
                (((it.code - 97) + sector) % 26  + 97).toChar()
        }.toString()
    }

    fun isRealRoom(letters: String, checkSum:String): Boolean {
        var charsMap = mutableMapOf<Char, Int>()
        letters.forEach{
            charsMap[it] = charsMap.getOrDefault(it, 0) + 1
        }
        var answer = charsMap
            .toSortedMap(compareBy<Char>{ charsMap[it]!!.unaryMinus()}.thenBy{it})
            .map{it.key}
            .take(5)
            .joinToString()
            .replace(",", "")
            .replace(" ","")
        return answer == checkSum;
    }

}

