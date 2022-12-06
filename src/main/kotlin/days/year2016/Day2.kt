package days.year2016

import days.Day
import java.math.BigInteger
import java.security.MessageDigest

fun main(args: Array<String>) {
    println(Day2().solve())
}

class Day2 : Day(1, 2016) {

    val input: String = "wtnhxymk"
    var index: Int = 0
    var found: Int = 0
    var password = "????????"


    fun solve() {
        while (found < 8) {
            val poging = input + index.toString();
            val hasj = md5(poging)
            if (hasj.substring(0, 5) == "00000") {
                println("$index - $hasj")
                var position =99
                try{
                    position = hasj[5].toString().toInt()
                } catch (e:Exception){
                    index += 1

                    continue
                }
                val letter = hasj[6]

                if (position < 8 && password[position] == '?') {
                    found += 1

                    password = password.replaceRange(position, position + 1, letter.toString())
                    println(password)
                }
            }
            index += 1
        }
        println(password)
    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }


}

