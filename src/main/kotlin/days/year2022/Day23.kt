package days.year2022

import days.Day
import java.util.*


fun main() {
    println(Day23().solve())
}


class Day23 : Day(23) {

    fun solve(): Any {
        var grid = inputList
            .map { it.toList().map { it == '#' } }

        var order = mutableListOf(
            mutableListOf(Point(-1, -1), Point(-1, 0), Point(-1, 1)),
            mutableListOf(Point(1, -1), Point(1, 0), Point(1, 1)),
            mutableListOf(Point(-1, -1), Point( 0, -1 ), Point(1, -1)),
            mutableListOf(Point(1, 1), Point(0, 1), Point(-1, 1))
        )

        repeat(Int.MAX_VALUE){
            var proposedMoves = mutableListOf<Pair<Point, Point>>()
            for ((y, row) in grid.withIndex()) {
                for ((x, isElf) in row.withIndex()) {
                    if (isElf) {
                        val surrounding = grid.getSurroundingCoordinates(y, x).filter{grid[it.y][it.x]}
                        if (surrounding.isNotEmpty()) {
                            var found =false
                            for(check in order){
                                val pToCheck1 = Point(check[0].y+y,check[0].x+x)
                                val pToCheck2 = Point(check[1].y+y,check[1].x+x)
                                val pToCheck3 = Point(check[2].y+y,check[2].x+x)
                                if(surrounding.intersect(mutableListOf(pToCheck1, pToCheck2, pToCheck3).toSet()).isEmpty()){
                                    proposedMoves.add(Pair(Point(y, x),pToCheck2))
                                    found=true
                                    break
                                }
                            }
                            if(!found )proposedMoves.add(Pair(Point(y,x),Point(y,x)))
                        } else {
                            proposedMoves.add(Pair(Point(y,x),Point(y,x)))
                        }
                    }
                }
            }

            val destinations = proposedMoves.map{it.second}
            var realMoves = mutableListOf<Point>()

            for (proposedMove in proposedMoves){
                val count = destinations.count { it==proposedMove.second }
                if(count==1) realMoves.add(proposedMove.second)
                else realMoves.add(proposedMove.first)
            }
            grid = makeGrid(realMoves, grid, it)
            Collections.rotate(order, -1)
        }

        return calculateScore(grid)


    }

    private fun calculateScore(grid: List<List<Boolean>>): Int {
        val firstx = grid.map{it.indexOf(true)}.filter{it>=0}.min()
        val lastx = grid.map{it.lastIndexOf(true)}.max()
        val firsty = grid.indexOfFirst { it.contains(true) }
        val lasty = grid.indexOfLast { it.contains(true) }
        var ngrid = grid.toMutableMatrix().filterIndexed{i,it-> i in firsty .. lasty }.map{it.filterIndexed{i,it-> i in firstx .. lastx }}
        return ngrid.flatten().filter { !it }.size
    }

    private fun makeGrid(realMoves: List<Point>, grid: List<List<Boolean>>, turn:Int): List<List<Boolean>> {
        var emptyGrid = grid.map{it.map{false}.toMutableList()}.toMutableList()
        realMoves.forEach{emptyGrid[it.y][it.x]=true}
        if(grid==emptyGrid){
            println(turn)
        }
        return  emptyGrid
    }


}

