package days.year2022

import days.Day


fun main() {
    println(Day3().solve())
}


class Day3 : Day(3) {

    fun solve(): Any {
        return inputList.chunked(3).sumOf {
            var ans = 0
            it[0].forEach { f ->
                it[1].forEach { s ->
                    it[2].forEach { t ->
                        if (f == s && s == t) {
                            ans = f.code - if (f.isLowerCase()) 96 else 38
                        }
                    }
                }
            }
            ans
        }
        return inputString
    }

}

