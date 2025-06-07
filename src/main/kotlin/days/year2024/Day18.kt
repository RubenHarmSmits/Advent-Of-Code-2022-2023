package days.year2024

import days.Day

fun main() {
    println(Day17().solve())
}

class Day17 : Day(17, 2024) {  //5, 7, 7, 3, 4, 2, 3, 1, 3

    val program = listOf(2, 4, 1, 2, 7, 5, 4, 7, 1, 3, 5, 5, 0, 3, 3, 0)
//    val program = listOf(0, 3, 5, 4, 3, 0)


    fun solve(): Any {
        var cur = -1
        (0L..5000L).forEach { a->
            val output = getOutput(35184372088832L + a)
            if(output[0]==program[0].toLong() &&
                output[1]==program[1].toLong()
                ) println(a)
//            if(output.size>cur){
////                println(a)
//                cur = output.size
//            }
//            if(output.size==6) return a
//            println("$a $output")
//            println("${output[0]}")
//            if(output.size>1 && output[1]==3L) println(a)
            // check first 0..
//            if((0L..7L).any { (a-it) % 64L == 0L }){
//                val output = getOutput(a)
//                if(output.size>1 && output[1]==3L) println(a)
//            }
        }
        return 0
    }

//    fun solve(): Any {
//        program.subList(0,program.size-1).forEachIndexed{i,outcome ->
//            println(getTwo(i,outcome.toLong()))
//        }

//        (0..99999999L).forEach { a ->
//            val output = getOutput(a)
//            println(output)
//            if(output.size==6) {
//                println(a)
//                return 0
//            }
//        }

//        return program.subList(0,program.size-1).mapIndexed{i,outcome ->
//            val ans = getTwo(i,outcome.toLong())
//            println(ans)
//            ans.first
//        }.sum()
//        (0..99999999L).forEach { a ->
//            if ((0L..7L).any { (a - it - 16L) % 64L == 0L }) {
//                if ((0L..7L).any { (a - it - 272L) % 512L == 0L }) {
//                    if ((0L..7L).any { (a - it - 784L) % 4096L == 0L }) {
//                        if ((0L..7L).any { (a - it - 8976L) % 32768L == 0L }) {
//                            if (isValid(238352L, 262144L, a)) {
//                                if (isValid(1549072L, 2097152L, a)) {
//                                    if (isValid(1549072L, 3646224L - 1549072L, a)) {
////                                        if (isValid(14131984L, 24617744L - 14131984L, a)) {
//                                            if (getOutput(a) == program.map { it.toLong() }) println(a)
//                                    println(a)
//                                            val output = getOutput(a)
//                                            val n = 7
//                                            if (output.size > n && output[n] == 7L) println(a)
////                                        }
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                }
//            }
//        }
//        return 0
//    }

    fun getTwo(n:Int, value:Long):Pair<Long,Long>{
        val list = mutableListOf<Long>()
        (0..99999999L).forEach { a ->
            val output = getOutput(a)
            if (output.size > n && output[n] == value) list.add(a)
            if(list.size==9){
                return Pair(list[0], list[8]-list[0])
            }

        }
        throw Exception("Error")
    }

    fun isValid(first: Long, second: Long, a: Long): Boolean {
        return (0L..7L).any { (a - it - first) % second == 0L }
    }


    var b = 0L
    var c = 0L
    var a = 0L

    fun getOutput(aa: Long): List<Long> {
        var output = listOf<Long>()
        var pointer = 0
        a = aa
        b = 0L
        c = 0L
        while (pointer < program.size) {
            val opcode = program[pointer]
            val operand = program[pointer + 1].toLong()
            when (opcode) {
                0 -> a = a / (2L `**` getCombo(operand))
                1 -> b = b xor operand
                2 -> b = getCombo(operand) % 8
                3 -> if (a != 0L) pointer = (operand - 2).toInt()
                4 -> b = b xor c
                5 -> output += getCombo(operand) % 8L
                6 -> b = a / (2L `**` getCombo(operand))
                7 -> c = a / (2L `**` getCombo(operand))
            }

            pointer += 2
        }

        return output
    }

    private fun getCombo(operand: Long): Long {
        when (operand) {
            0L, 1L, 2L, 3L -> return operand
            4L -> return a
            5L -> return b
            6L -> return c
        }
        throw IllegalStateException("Unreachable")
    }

}