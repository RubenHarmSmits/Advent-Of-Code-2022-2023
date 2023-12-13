package days.year2023

import days.Day
import days.Matrix
import java.lang.Exception
import kotlin.math.min

fun main() {
    println(Day13().solve())
}
class Day13: Day(13, 2023) {

    fun solve(): Any {
        return inputList.splitBy { it == "" }
                .map { matrixOfInput(it) }.sumOf {
                    val score = getScoreForMap(it, 0)
                    getScoreForSmudgeMap(it, score)
                }
    }

    private fun getScoreForSmudgeMap(map: Matrix<Char>, prevScore: Int): Int {
        for(y in map.indices){
            for (x in map[y].indices) {
                val copy = map.toMutableMatrix().map { it.toMutableList() }
                if (map[y][x] == '#') copy[y][x] = '.'
                else copy[y][x] = '#'
                val score = getScoreForMap(copy, prevScore)
                if(score>0) return score
            }
        }
        throw Exception("No correct found")
    }

    private fun getScoreForMap(map: Matrix<Char>, prevScore: Int): Int {
        // vertical
        val end = map[0].size
        for(x in 1 until end){
            val rowsToCheck = min(x, end-x);
            var mirror =true
            for(n in 0 until rowsToCheck){
                val isSame = (getCol(map,x-n-1) == getCol(map, x+n))
                if(!isSame) mirror =false
            }
            if(mirror && (x!= prevScore)) return x
        }

        // horizontal
        val endy = map.size
        for(y in 1 until endy){
            val rowsToCheck = min(y, endy-y);
            var mirror =true
            for(n in 0 until rowsToCheck){
                val isSame = map[y-n-1] == map[y+n]
                if(!isSame) mirror =false
            }
            val score = y * 100
            if(mirror && score!=prevScore) return score
        }

        return 0
    }


}