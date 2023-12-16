package days.year2016

import days.Day

fun main() {
    println(Day16().solve())
}
class Day16: Day(16, 2016) {
    var size = 35651584

    var input = "00101000101111010"

    var list: MutableList<Int> = mutableListOf()

    fun solve(): Any {
        while(input.length<size){
            var b = String(input.reversed().map { if (it=='0') '1'  else '0' }.toCharArray())
            input = input + '0' + b;
            println(input)
        }

        input = input.substring(0,size)

        while (input.length %2 == 0){
                input=check(input);
        }
        return input;
    }

    fun check(str: String):String{
        var chunk = str.chunked(2).map { if(it[0]==it[1]) '1' else '0' }
        return String(chunk.toCharArray())
    }

}