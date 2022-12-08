package days.year2022

import days.Day


fun main() {
    println(Day2().solve())
}


class Day2 : Day(2) {

    fun solve(): Any {
        return inputList
            .map {
                var score = 0;
                val opp = it.split(" ")[0]
                val me = map(opp, it.split(" ")[1])
                if (me == "X") {
                    score += 1
                    if (opp == "A") score += 3
                    if (opp == "C") score += 6
                };
                if (me == "Y") {
                    score += 2
                    if (opp == "A") score += 6
                    if (opp == "B") score += 3
                };
                if (me == "Z") {
                    score += 3
                    if (opp == "B") score += 6
                    if (opp == "C") score += 3
                };
                score
            }
            .sum()
        return inputString
    }

    private fun map(opp: Any, out: Any): String {
        if (opp == "A") {
            if (out == "X") return "Z"
            if (out == "Y") return "X"
            if (out == "Z") return "Y"
        }
        if (opp == "B") {
            if (out == "X") return "X"
            if (out == "Y") return "Y"
            if (out == "Z") return "Z"
        }
        if (opp == "C") {
            if (out == "X") return "Y"
            if (out == "Y") return "Z"
            if (out == "Z") return "X"
        }
        return ""
    }

}

