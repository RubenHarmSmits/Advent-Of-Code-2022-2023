package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day19().solve())
}

class Day19 : Day(19, 2023) {
    var workflows: MutableList<Workflow> = mutableListOf()
    fun solve(): Any {
        inputList.forEach {
            val r = it.substringAfter('{').substringBefore('}').trim(' ').split(',')
            val rules: MutableList<Rule> = r.dropLast(1).map {
                val part = it.substringAfter('{').substringBefore('>').substringBefore('<')
                Rule(part, extraxtAllLongsFromString(it)[0], it.contains('>'), it.substringAfter(':'))
            }.toMutableList()

            workflows.add(Workflow(it.substringBefore('{'), rules, r.last()))
        }

        return isRatingAccepted(mutableListOf(), "in")

    }


    private fun isRatingAccepted(rules: MutableList<Rule>, name: String): Long {
        var current = workflows.find { it.name == name }
        if (current != null) {
            var total = 0L

            val reversed = mutableListOf<Rule>()
            current.rules.forEach { rule ->
                val newRules = rules.toMutableList()
                newRules.add(rule)
                newRules.addAll(reversed)
                reversed.add(rule.reverse())
                total += isRatingAccepted(newRules, rule.nameToGoTo)
            }
            return total + isRatingAccepted((reversed + rules).toMutableList(), current.endDestination)
        }
        return if (name == "A") calculateScoreFromRules(rules) else 0L
    }

    private fun calculateScoreFromRules(rules: List<Rule>): Long {
        return "saxm".map { char ->
            getScoreFromPart(rules.filter { it.part == char.toString() })
        }.reduce { acc, unit -> acc * unit }
    }

    fun getScoreFromPart(rules: List<Rule>): Long {
        var newRanges = mutableListOf<LongRange>()
        rules.map { it.reverse() }.forEach {
            if (it.greaterThan) newRanges.add(it.number + 1L..4000L)
            else newRanges.add(0L until it.number)
        }
       return  (1..4000).count { i -> newRanges.all { range -> i !in range } }.toLong()
    }
}


data class Workflow(val name: String, val rules: List<Rule>, val endDestination: String)
data class Rule(val part: String, val number: Long, val greaterThan: Boolean, val nameToGoTo: String) {
    fun reverse(): Rule {
        val num = if (greaterThan) number + 1 else number - 1
        return Rule(part, num, !greaterThan, nameToGoTo)
    }
}
