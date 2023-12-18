package days.year2023

import days.Day
import java.util.*
import kotlin.math.*

fun main() {
    println(Day18().solve())
}

class Day18 : Day(18, 2023) {
    var tot = 0L

    var sy = 0
    var ly = 0
    var sx = 0
    var lx = 0

    var points: MutableSet<Point> = mutableSetOf()
    var currentPoint = Point(0, 0)
    val mem = mutableMapOf<String, Long>()
    val memPointToL = mutableMapOf<Point, Int>()

    val map2 = mapOf('0' to 'R', '1' to 'D', '2' to 'L', '3' to 'U')
    val map = mapOf('R' to Point(0,1), 'L' to Point(0,-1), 'U' to Point(-1,0), 'D' to Point(1,0))

    fun solve(): Any {
        points.add(currentPoint.copy())


        var pos = 0.0
        var ans = 1.0
        inputList.forEach {
//            var (dir, l, col) = it.split(" ")

            var (xxx, xx, col) = it.split(" ")
            val l = col.substring(2, 7).toInt(16)
            val dir = map2[col.get(col.length-2)]!!.toString()

            if(dir == "R"){
                memPointToL[Point(currentPoint.y, currentPoint.x)] = l.toInt()+ 1
            }
            if(dir == "L"){
                memPointToL[Point(currentPoint.y, currentPoint.x-l.toInt())] = l.toInt() +1
            }

//            for (s in 0 until l.toInt()) {
//                currentPoint = currentPoint.moveInDirection(dir[0])
//                points.add(currentPoint.copy())
//            }

            val n = l.toDouble()
            val x = map[dir[0]]!!.x.toDouble()
            val y = map[dir[0]]!!.y.toDouble()

            pos += x*n
            ans += y*n * pos + n/2
        }


        println(ans.toLong())

        sy = points.map { it.y }.min()
        ly = points.map { it.y }.max()
        sx = points.map { it.x }.min()
        lx = points.map { it.x }.max()


        for (y in sy ..ly) {
            val allX = points.filter{it.y==y}
            val allXString = allX.toString()
            val ytot = mem.getOrPut(allXString){
                getYTot(y)
            }
            tot+=ytot
            println(y)
        }
        return ans

    }

    private fun getYTot(y: Int): Long {
        var ytot = 0L
        var inside = false
        var x = sx - 1
        while (x < lx + 1) {
            if (points.contains(Point(y, x))) {
                val hashtagsize = extraxtHashtags(y, x)
                if (hashtagsize == 1) {
                    x++
                    inside = !inside
                    ytot++
                } else {
                    val lu = points.contains(Point(y - 1, x))
                    val ru = points.contains(Point(y - 1, x + hashtagsize - 1))
                    if (lu != ru) {
                        inside = !inside
                    }
                    x += hashtagsize
                    ytot += hashtagsize

                }
            } else {
                val pointsize = extraxtDots(y, x)
                if (pointsize.isPresent) {
                    x += pointsize.get()
                    if (inside) {
                        ytot += pointsize.get()
                    }
                } else {
                    x += lx
                }
            }

        }
        return ytot;
    }

    private fun extraxtHashtags(y: Int, x: Int, findHashtags: Boolean): Int {
        var found = false
        var count = 0
        while (!found) {
            var next = Point(y, x + count)
            if (points.contains(next) != findHashtags) {
                found = true
                continue
            }
            count++
        }
        return count
    }

    private fun extraxtHashtags(y: Int, x: Int): Int {
        return memPointToL.getOrDefault(Point(y,x),1)
    }

    private fun extraxtDots(y: Int, x: Int): Optional<Int> {
        var allX = points.filter{it.y==y}.map { it.x }.sorted().filter { it > x }
        if (allX.isEmpty()) return Optional.empty()
        return Optional.of(allX[0] - x)
    }

//    fun checkNeighbours(p: Point) {
//        val neig: List<Point> = listOf(Point(p.y + 1, p.x), Point(p.y - 1, p.x), Point(p.y, p.x + 1), Point(p.y, p.x - 1))
//        neig.forEach {
//            if (!mem.contains(it) && !points.contains(it)) {
//                mem.add(it)
//                checkNeighbours(it)
//            }
//        }
//    }


}