package days.year2022

import days.Day

fun main() {
    println(Day20().solve())
}

class Day20: Day(20) {

    val TIMES = 811589153L

    fun solve(): Any {
        var pairList = inputListInt
            .mapIndexed{i, it -> Pair(i, it.toLong()*TIMES)}.toMutableList()

        val size = pairList.size

        repeat(10){
            for(i in 0 until size){
                val currentPair = pairList.first { it.first == i }
                val indexOfCurrent = pairList.indexOf(currentPair)
                val newIndex = (indexOfCurrent + currentPair.second ) % (pairList.size-1)
                pairList.removeAt(indexOfCurrent)
                if(newIndex>0) pairList.add(newIndex.toInt(), currentPair)
                else pairList.add((size + newIndex -1).toInt(), currentPair)
            }
        }
        val indexOfnul = pairList.indexOf(pairList.first { it.second == 0L })
        return listOf(1000,2000,3000).sumOf{pairList[(it+indexOfnul)%size].second}
    }

}

