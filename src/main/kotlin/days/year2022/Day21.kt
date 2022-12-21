package days.year2022

import days.Day

fun main() {
    println(Day21().solve())
}

data class Monk(
    val name: String,
    var first: String?,
    var second: String?,
    var operation: String?,
    var hasNumber: Boolean,
    var number: Long
) {

}

class Day21 : Day(21) {


    var mobkeys = mutableListOf<Monk>()


    fun solve(): Any {
        inputList
            .forEach {
                val (name, value) = it.split(": ")
                val parsed = value.split(" ")
                if (parsed.size == 1) {
                    val number = parsed[0].toLong()
                    mobkeys.add(Monk(name, null, null, null, true, number))
                } else {
                    mobkeys.add(Monk(name, parsed[0], parsed[2], parsed[1], false, 0))
                }
            }
        return calcValue("rcsj", calcNumber("zfsf"))
    }

    private fun calcValue(name: String, end: Long): Long {
        val current = findMonk(name)
        val fmonk = current.first
        val smonk = current.second

        val op = current.operation

        if (returnsNumber(fmonk!!)) {
            val n = calcNumber(fmonk)

            if (fmonk == "humn") {
                println("HERE1")
                println(end + calcNumber(smonk!!))

            }

            if (op == "+") return calcValue(smonk!!, end - n)
            if (op == "-") return calcValue(smonk!!, n - end)
            if (op == "/") return calcValue(smonk!!, n / end )
            if (op == "*") return calcValue(smonk!!, end / n)

        } else if (returnsNumber(smonk!!)) {
            val n = calcNumber(smonk)

            if (smonk == "humn") {
                println("HERE12")
            }
            if (op == "+") return calcValue(fmonk!!, end - n)
            if (op == "-") return calcValue(fmonk!!, end + n)
            if (op == "/") return calcValue(fmonk!!, end * n)
            if (op == "*") return calcValue(fmonk!!, end / n)

        }

        return 0;

    }


    private fun calcNumber(name: String): Long {
        val current = findMonk(name)
        if (current.hasNumber) return current.number
        else {
            val fmonk = current.first
            val smonk = current.second

            val op = current.operation
            if (op == "+") return calcNumber(fmonk!!) + calcNumber(smonk!!)
            if (op == "-") return calcNumber(fmonk!!) - calcNumber(smonk!!)
            if (op == "/") return calcNumber(fmonk!!) / calcNumber(smonk!!)
            if (op == "*") return calcNumber(fmonk!!) * calcNumber(smonk!!)

            return 0;
        }
    }


    private fun returnsNumber(name: String): Boolean {
        val current = findMonk(name)
        if (current.hasNumber) return true
        else {
            val fmonk = current.first
            val smonk = current.second

            if (fmonk == "humn" || smonk == "humn") return false
            return returnsNumber(fmonk!!) && returnsNumber(smonk!!)
        }
    }

    fun findMonk(name: String): Monk {
        return mobkeys.first { it.name == name }
    }
}

