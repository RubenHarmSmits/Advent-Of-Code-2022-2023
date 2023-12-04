package days.year2023

import days.Day
import java.lang.Math.pow
import kotlin.math.*

fun main() {
    println(Day4().solve())
}
class Day4: Day(4, 2023) {

    fun solve(): Any {
        val cards = inputList.mapIndexed{i,it->
            var (winning, mine) = it.substringAfter(':').split("|").map{extraxtAllIntsFromString(it)};
            val inter = winning.intersect(mine).size
            Card(i, inter, 1)
        }
        cards.forEachIndexed { index, card ->
            repeat(card.amount){
                for(intersects in 0 until card.intersects){
                    cards[index+intersects+1].amount++
                }
            }
        }
        return cards.sumOf { it.amount };
    }
}

data class Card(val name:Int, val intersects: Int, var amount:Int)