package days.year2016

import days.Day
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Optional
import kotlin.math.min

fun main() {
    val start = System.currentTimeMillis()
    println(Day14().partOne())
    println("MILISECONDS: " + (System.currentTimeMillis() - start))
}

class Day14 : Day(14, 2016) {

    var i = 0
    var found = 0
    val salt = "jlmsuwbz"
//    val salt = "abc"

    val memory:MutableMap<String, String> = mutableMapOf()

    fun partOne(): Any {
        while (found < 64) {
            val os = getOptionalSequence((findStretchedHash(salt + i.toString())))
            if (os.isPresent && next1000contains(os.get(), i)) {
                println(found)
                found++
            }
            i++
        }
        return i - 1;
    }

    private fun next1000contains(get: Char, starti: Int): Boolean {
        val fiveTimesChar = get.toString().repeat(5)
        for (i in 1 + starti..1000 + starti) {
            if (findStretchedHash(salt + i.toString()).contains(fiveTimesChar)) return true
        }
        return false
    }

    fun getOptionalSequence(s: String): Optional<Char> {
        s.forEachIndexed { ii, c ->
            if (ii + 2 < s.length && (c == s[ii + 1] && c == s[ii + 2])) {
                return Optional.of(c)
            }
        }
        return Optional.empty()
    }

    fun findStretchedHash(s: String): String {
        if(memory.containsKey(s)) return memory.get(s)!!

        var ls = s
        for (i in 0..2016) {
            ls = md5(ls)
        }
        memory.put(s, ls)
        return ls
    }
}




