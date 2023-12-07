package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day7().solve())
}

class Day7 : Day(7, 2023) {

    fun solve(): Any {
        return inputList.map { getBestVersion(it) }
                .sortedWith(::customSortingFunction)
                .mapIndexed { i, it ->
                    it.split(" ")[1].toLong() * (i + 1)
                }.sum()
    }

    private fun getBestVersion(it: String): String {
        val (hand, score) = it.split(" ")

        val comb = generateCombinations(hand)
        val best = comb.sortedWith(::customSortingFunction).last()
        return "$best $score $hand";
    }

    fun generateCombinations(input: String): List<String> {
        val result = mutableListOf<String>()

        fun generateCombinationsRecursive(current: String, index: Int) {
            if (index == input.length) {
                result.add(current)
                return
            }

            if (input[index] == 'J') {
                for (char in "AKQT987654321") {
                    generateCombinationsRecursive(current + char, index + 1)
                }

            } else {
                generateCombinationsRecursive(current + input[index], index + 1)
            }
        }

        generateCombinationsRecursive("", 0)
        return result
    }

    fun getScore(hand: String): Int {
        val cardCounts = mutableMapOf<Char, Int>()

        for (card in hand) {
            cardCounts[card] = cardCounts.getOrDefault(card, 0) + 1
        }

        val sortedCounts = cardCounts.values.sortedDescending()

        return when (sortedCounts) {
            listOf(5) -> 6
            listOf(4, 1) -> 5
            listOf(3, 2) -> 4
            listOf(3, 1, 1) -> 3
            listOf(2, 2, 1) -> 2
            listOf(2, 1, 1, 1) -> 1
            else -> 0
        }
    }

    fun customSortingFunction(card1: String, card2: String): Int {
        var hand1 = card1.split(" ")[0]
        var hand2 = card2.split(" ")[0]
        val score1 = getScore(hand1)
        val score2 = getScore(hand2)
        val order = "AKQT987654321J"
        if (score1 == score2) {
            if (card1.split(" ").size > 2) hand1 = card1.split(" ")[2]
            if (card2.split(" ").size > 2) hand2 = card2.split(" ")[2]
            for (i in 0..4) {
                val order1 = order.indexOf(hand1[i])
                val order2 = order.indexOf(hand2[i])
                if (order1 != order2) return order2 - order1
            }
        }
        return score1 - score2
    }

}