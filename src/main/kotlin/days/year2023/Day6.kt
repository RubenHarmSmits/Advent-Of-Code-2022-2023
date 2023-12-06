package days.year2023

import days.Day

var games = listOf(Game(49, 298), Game(78, 1185), Game(79, 1066), Game(80, 1181))
var game = Game(49787980L, 298118510661181L)
fun main() {
    println("PART1: " + Day6().solve())
    println("PART2: " + Day6().numberOfWaysToBeat(game))
}

class Day6 : Day(6, 2023) {
    fun solve(): Any {
        return games
                .map { numberOfWaysToBeat(it) }
                .fold(1L) { acc, it ->
                    acc * it
                }
    }

    fun numberOfWaysToBeat(game: Game): Long {
        return (0..game.time)
                .count { it * (game.time - it) > game.record }
                .toLong()
    }
}
data class Game(val time: Long, val record: Long)