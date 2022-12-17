package days.year2022

import days.Day
import java.lang.Integer.max


fun main() {

    val start = System.currentTimeMillis()
    println(Day17().solve())

    println("time(ms): ")
    print(System.currentTimeMillis() - start)
}


class Day17 : Day(17) {

    val GRIDHEIGHT = 10000
    var grid = MutableList(GRIDHEIGHT) { MutableList(7) { '.' } }
    var rock0 = listOf(Point(0,0),Point(0, 1), Point(0, 2), Point(0, 3))
    var rock1 = listOf(Point(-1,0),Point(-1, 1), Point(-1, 2), Point(0, 1), Point(-2, 1))
    var rock2 = listOf(Point(0,0),Point(0, 1), Point(0, 2), Point(-1, 2), Point(-2, 2))
    var rock3 = listOf(Point(0,0),Point(-1, 0), Point(-2, 0), Point(-3, 0))
    var rock4 = listOf(Point(0,0),Point(-1, 0), Point(-1, 1), Point(0, 1))
    val rocks = listOf(rock0, rock1, rock2, rock3, rock4)

    var iterator = inputString.iterator()

    var count = 0




    fun solve(): Any {

        repeat(2022){
            grid[grid.lastIndex] = MutableList(7) { '#' }
                val highestY = grid.indexOfFirst { it.contains('#') }
                for (p in rocks[it%5]) {
                    grid[highestY + p.y -4][2 + p.x] = '@'
                }
                var moving = true
                while (moving) {
                    if(!iterator.hasNext()) iterator = inputString.iterator()

                    moveAllInDirection(iterator.next())
                    moving = moveAllInDirection('D')
                }

                // turn @ into #
                for ((y, row) in grid.withIndex()) {
                    for ((x, p) in row.withIndex()) {
                        if (p == '@') {
                            grid[y][x] = '#'
                        }
                    }
                }

            }



        return GRIDHEIGHT - grid.indexOfFirst { it.contains('#') } -1

    }


    private fun moveAllInDirection(direction: Char):Boolean {
        // move all # 1 down
        var moving = true
        var ats = mutableListOf<Point>()
        for ((y, row) in grid.withIndex()) {
            for ((x, p) in row.withIndex()) {
                if (p == '@') {
                    ats.add(Point(y, x))

                    if(direction=='D'){
                        if (grid[y + 1][x] == '#') {
                            moving = false
                        }
                    }

                    if(direction=='>'){
                        if(x==row.size-1||grid[y][x+1] =='#'){
                            moving = false
                        }
                    }

                    if(direction=='<'){
                        if(x==0||grid[y][x-1] =='#') moving = false
                    }
                }
            }
        }
        if (moving) {
            for ((y, row) in grid.withIndex()) {
                for ((x, p) in row.withIndex()) {
                    if (p != '#') {
                        grid[y][x] = '.'
                    }
                }
            }
            for(a in ats){
                if(direction=='D') {
                    grid[a.y+1][a.x] = '@'
                }
                if(direction=='>') {
                    grid[a.y][a.x+1] = '@'
                }
                if (direction == '<') {
                    grid[a.y][a.x-1] = '@'
                }
            }
        }

        return moving

    }

}




