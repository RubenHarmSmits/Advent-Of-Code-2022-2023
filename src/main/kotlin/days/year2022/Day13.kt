package days.year2022

import days.Day


fun main() {
    Day13().solve()
}

enum class Score {
    CORRECT, FALSE, DRAW
}

class Day13 : Day(13) {

    fun solve() {
        val list = inputList.splitBy { it == "" }.flatten().map{parse(foo(it))}
        val a = list.sortedWith(Comparator { o1, o2 ->
            val sc = correct(o1,o2)
            if(sc==Score.CORRECT){
                -1
            } else 1
        })

        a.forEachIndexed{i,it->println("$i+1   $it")}
        // find solution by searching in output and multyplying results

    }


    private fun parse(list: List<Any>): Any {
        return list
            .mapIndexed { i, it ->
                if (it is List<*>) parse(it as List<Any>)
                else if (it == '1' && list.size > i + 1 && list[i + 1] == '0') {
                    10
                } else if (it == '0' && i > 0 && list[i - 1] == '1') {
                    '#'
                } else if(it is Char) {
                    it.code - 48
                } else it
            }.filter { it != '#' }.filter{it!=-4}.filter{it!=' '}
    }


    private fun correct(first: Any, second: Any): Score {
        var s = Score.DRAW

        val fint = first is Int
        val sint = second is Int


        if (sint && fint) {
            if ((first as Int) < (second as Int)) return Score.CORRECT
            if (first > second) return Score.FALSE
        } else if (!sint && !fint) {
            (first as List<Any>).forEachIndexed { i, fany ->
                if (i < (second as List<Any>).size) {
                    val sany = second[i]
                    val res = correct(fany, sany)
                    if (res != Score.DRAW) return res
                } else {
                    return Score.FALSE
                }
            }
            if (first.size < (second as List<Any>).size) return Score.CORRECT
        } else if (fint && !sint) {
            val res = correct(listOf(first), second as List<Any>)
            if (res != Score.DRAW) return res
        } else if (!fint && sint) {
            val res = correct(first as List<Any>, listOf(second))
            if (res != Score.DRAW) return res
        }
        return s
    }

    fun foo(s: String): List<Any> {
        val tokens = s.iterator()
        fun fooHelper(level: Int = 0): List<Any> {
            var token = '.'
            try {
                token = tokens.next()
            } catch (e: Exception) {
                return emptyList()
            }
            if (token == ']') {
                return emptyList()
            } else if (token == '[') {
                return listOf(fooHelper(level + 1)) + fooHelper(level)
            } else {
                return listOf(token) + fooHelper(level)
            }
        }

        return fooHelper()[0] as List<Any>
    }
}

