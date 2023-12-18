package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day8().solve())
}

class Day8 : Day(8, 2023) {
    var input = "LLRRRLRLLRLRRLRLRLRRRLLRRLRRRLRRRLRRRLRRRLRRRLRRLRLLRRRLRRLLRLRLLLRRLRRLRLRLRLRRRLRLRRRLRRLLLRRRLLRRLLRRLLRRRLLLLRLRLRRRLRLRRRLRLLLRLRRLRRRLRRRLRRRLRRRLLRRLLLLRRLLRRLLRRLRLRRRLRRRLRRRLRRLRRRLRRLRRLRRLRLRRRLRRLRRRLRRRLRRLRLRRRLRRLLRLRRLRRRLRLRRLRRRLRRLRRLRRRLLRRRR";

    fun solve(): Any {
        val nodes = inputList.map {
            var name = it.split(" =")[0];
            val left = it.substringAfter('(').split(',')[0]
            val right = it.substringAfter(", ").substringBefore(")")
            Node(name, left, right)
        }
//        var currentList = nodes.filter { it.name.endsWith('A') }.toMutableList()
//        var i = 0;
//        while (true) {
//            var newList: MutableList<Node> = mutableListOf()
//            currentList.forEach { cur ->
//                newList.add(nodes.first { it.name == if (input[i % input.length] == 'L') cur.left else cur.right })
//            }
//            i++
//            currentList = newList
//            if(newList[5].name.endsWith('Z')){
//                println(i)
//            }
//        }

        // repetitions are calculated manually by the previous code snippet
        var repetitions = listOf(13939L, 17621L, 11309L, 20777L, 19199L, 15517L)
        return leastCommonMultiple(repetitions);
    }
}

    private data class Node(val name: String, val left: String, val right: String)