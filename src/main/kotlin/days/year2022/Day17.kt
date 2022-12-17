package days.year2022

import days.Day
import java.lang.Integer.max


fun main() {

    val start = System.currentTimeMillis()
    println(Day17().solve2())
    println(Day17().solve1())

    println("time(ms): ")
    print(System.currentTimeMillis() - start)
}


class Day17 : Day(17) {

    val GRIDHEIGHT = 50000
    var grid = MutableList(GRIDHEIGHT) { MutableList(7) { '.' } }
    var rock0 = listOf(Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3))
    var rock1 = listOf(Point(-1, 0), Point(-1, 1), Point(-1, 2), Point(0, 1), Point(-2, 1))
    var rock2 = listOf(Point(0, 0), Point(0, 1), Point(0, 2), Point(-1, 2), Point(-2, 2))
    var rock3 = listOf(Point(0, 0), Point(-1, 0), Point(-2, 0), Point(-3, 0))
    var rock4 = listOf(Point(0, 0), Point(-1, 0), Point(-1, 1), Point(0, 1))
    val rocks = listOf(rock0, rock1, rock2, rock3, rock4)

    var iterator = inputString.iterator()

    var count = 0

    var previous = 0;


    fun solve1(): Any {

        repeat(20000) {

            grid[grid.lastIndex] = MutableList(7) { '#' }
            val highestY = grid.indexOfFirst { it.contains('#') }
            for (p in rocks[it % 5]) {
                grid[highestY + p.y - 4][2 + p.x] = '@'
            }
            var moving = true
            while (moving) {
                if (!iterator.hasNext()){
                    iterator = inputString.iterator()
                }

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


            if ((it+1) % 5 == 0) {
                val diff = GRIDHEIGHT - grid.indexOfFirst { it.contains('#') } - 1 - previous
                previous = GRIDHEIGHT - grid.indexOfFirst { it.contains('#') } - 1
                print(diff)

                // 1000000000000

                // eerste 20 stenen 26 bij

                //elke 35 stenen komt er 53 bij

                // (28571428570 * 53) + 26 + 47

                // er blijft 30 over = 47 hoogte
                print(',')
            }

        }



        return GRIDHEIGHT - grid.indexOfFirst { it.contains('#') } - 1

    }


    private fun moveAllInDirection(direction: Char): Boolean {
        // move all # 1 down
        var moving = true
        var ats = mutableListOf<Point>()
        for ((y, row) in grid.withIndex()) {
            for ((x, p) in row.withIndex()) {
                if (p == '@') {
                    ats.add(Point(y, x))

                    if (direction == 'D') {
                        if (grid[y + 1][x] == '#') {
                            moving = false
                        }
                    }

                    if (direction == '>') {
                        if (x == row.size - 1 || grid[y][x + 1] == '#') {
                            moving = false
                        }
                    }

                    if (direction == '<') {
                        if (x == 0 || grid[y][x - 1] == '#') moving = false
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
            for (a in ats) {
                if (direction == 'D') {
                    grid[a.y + 1][a.x] = '@'
                }
                if (direction == '>') {
                    grid[a.y][a.x + 1] = '@'
                }
                if (direction == '<') {
                    grid[a.y][a.x - 1] = '@'
                }
            }
        }

        return moving

    }

    fun solve2(): Any {
        val TOTALSTONEDSTOPPED = 1000000000000L


        val repeatingString = "6,7,11,10,7,7,5,9,9,8,4,11,8,8,11,6,8,8,5,7,8,6,11,8,8,7,10,6,6,11,5,6,6,9,7,5,7,12,6,6,6,7,8,8,6,7,11,6,7,11,5,6,11,8,7,7,11,6,5,10,9,8,12,7,7,6,7,8,5,8,8,6,6,11,8,7,10,13,6,7,6,5,6,10,6,8,9,11,6,8,6,6,9,10,8,7,7,8,6,9,7,9,6,7,8,6,10,11,11,8,9,6,10,7,8,10,6,10,6,9,10,6,7,7,9,7,5,8,7,9,9,11,5,9,7,8,13,12,7,9,11,3,10,3,8,7,7,9,6,9,8,9,11,7,6,7,6,8,5,6,10,5,8,9,7,10,5,8,7,11,7,10,9,7,10,7,7,8,6,12,9,10,9,7,11,6,7,7,8,9,7,8,5,9,9,11,13,9,9,6,6,6,11,11,11,6,9,9,10,6,4,8,9,9,6,5,8,2,6,9,8,6,7,9,5,10,4,6,8,4,7,7,10,10,8,8,9,6,7,5,8,7,6,8,7,4,8,11,10,7,6,5,9,8,8,9,10,6,5,6,12,9,6,6,6,7,8,8,7,8,9,6,6,5,9,9,7,11,11,6,6,8,9,10,7,9,9,6,8,9,8,7,5,9,7,7,9,6,11,11,8,6,11,9,10,6,6,10,8,8,7,6,5,7,7,5,8,11,8,8,7,9,5,8,10,7,9,9,13,11,9,8,6,9,4,7,9,11,9,7,8,9,7,9,6,6,4,11"

        val repeatingStringInt = extraxtAllIntsFromString(repeatingString).map{it.toLong()}
        val totalHeightInRepeatingString = repeatingStringInt.sum()
        val repeatingLength = repeatingStringInt.size.toLong()

        val beforeString = "9,6,7,9,6,8,7,7,5,4,9,8,5,7,9,10,7,10,8,7,10,9,8,10"
        val beforeStringInt = extraxtAllIntsFromString(beforeString).map{it.toLong()}
        val totalHeightInBeforeString = beforeStringInt.sum()
        val beforeLength = beforeStringInt.size.toLong()

        val amountOfTimesRepeating = (TOTALSTONEDSTOPPED - (beforeLength * 5L)) / (repeatingLength * 5L)

        val sizeAfter = (TOTALSTONEDSTOPPED - (amountOfTimesRepeating * repeatingLength * 5L) - (beforeLength * 5L))/5L

        val afterStringInt = repeatingStringInt.subList(0, sizeAfter.toInt())
        val totalHeightInAfterString = afterStringInt.sum()



        val answer = amountOfTimesRepeating * totalHeightInRepeatingString + totalHeightInBeforeString + totalHeightInAfterString

        return answer
    }

}




