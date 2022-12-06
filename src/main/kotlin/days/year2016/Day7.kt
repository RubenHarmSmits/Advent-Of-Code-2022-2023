package days.year2016

import days.Day

fun main(args: Array<String>) {
    println(Day7().partOne())
}

class Day7 : Day(7, 2016) {

    fun partOne(): Any {
        return inputList.filter { isAbbaLine(it) }.size
    }


    private fun isAbbaLine(line: String): Boolean {
        var insideBrackets = false;
        val babList: MutableList<String> = mutableListOf();
        var found = false;
        for ((i, c) in line.substring(0, line.length - 2).withIndex()) {
            if (c == '[') insideBrackets = true
            if (c == ']') insideBrackets = false
            val nextThree = line.substring(i, i + 3);
            if (nextThree.contains('[') || nextThree.contains(']')) continue
            if (isBAB(nextThree) && !insideBrackets) {
                babList.add(nextThree);
            }
        }

        insideBrackets = false;

        for ((i, c) in line.substring(0, line.length - 2).withIndex()) {
            val nextThree: String = line.substring(i, i + 3);
            if (c == '[') insideBrackets = true
            if (c == ']') insideBrackets = false
            if(insideBrackets){
                babList.forEach {
                    if (isCorresponding(it, nextThree)) {
                        found = true
                        println(it)
                    }
                }
            }
        }

        return found;
    }

    private fun isBAB(line: String): Boolean {
        if (line[0] != line[2]) return false;
        if (line[0] == line[1]) return false
        return true
    }

    private fun isCorresponding(first: String, second: String): Boolean {
        if (first[0] != second[1]) return false;
        if (first[1] != second[0] || first[1] != second[2]) return false
        if (first[2] != second[1]) return false
        return true
    }


}

