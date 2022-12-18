package days.year2022

import days.Day
import java.lang.Math.abs


fun main() {

    val start = System.currentTimeMillis()
    println(Day18().solve())

    println("time(ms): ")
    print(System.currentTimeMillis() - start)
}


class Day18 : Day(18) {

    val GRIDSIZE = 22

    var grid = MutableList(GRIDSIZE) { MutableList(GRIDSIZE) { MutableList(GRIDSIZE) { false } } }

    val cubes = inputList.map { extraxtAllIntsFromString(it) }

    val noEscaping = mutableListOf<Cube>()
    val escaping = mutableListOf<Cube>()

    val dy = listOf(-1, 1, 0, 0, 0, 0)
    val dx = listOf(0, 0, -1, 1, 0, 0)
    val dz = listOf(0, 0, 0, 0, -1, 1)


    fun solve(): Int {
        return part2()
    }

    private fun part2(): Int {

        cubes.forEach {
            grid[it[1]][it[0]][it[2]] = true
        }

        var tot = part1()

        println(tot)

        val notEscaping = mutableListOf<List<Int>>()

        for (y in 0 until GRIDSIZE) {
]            for (x in 0 until GRIDSIZE) {
                for (z in 0 until GRIDSIZE) {
                    if (!grid[y][x][z] && !canEscape(grid.toList(), Cube(y, x, z))) {
                        notEscaping.add(listOf(y,x,z))
                    }
                }
            }
        }


        notEscaping.forEach{
            val (y,x,z) = it
            for (i in 0 until 6) {
                if(grid[y+dy[i]][x+dx[i]][z+dz[i]]) tot--
            }
        }

        return tot
    }


    private fun part1(): Int {
        val adj = cubes.combinations(2).filter { it[0] != it[1] && adjacent(it[0], it[1]) }.size
        return cubes.size * 6 - adj * 2
    }

    private fun adjacent(cube1: List<Int>, cube2: List<Int>): Boolean {
        var same = 0
        var oof = 0
        for (i in cube1.indices) {
            if (cube1[i] == cube2[i]) same++
            if (abs(cube1[i] - cube2[i]) == 1) oof++
        }
        return (same == 2 && oof == 1)
    }

    fun canEscape(grid: List<List<List<Boolean>>>, start: Cube): Boolean {
        val queue = ArrayDeque<Cube>()
        queue.add(start)

        val visited = mutableSetOf<Cube>()

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if(escaping.contains(current)){
                return true
            }
            if(noEscaping.contains(current)){
                return false
            }
            visited.add(current)

            for (i in 0 until 6) {
                val nextX = current.x + dx[i]
                val nextY = current.y + dy[i]
                val nextZ = current.z + dz[i]

                if (nextX < 0 || nextX >= GRIDSIZE ||
                    nextY < 0 || nextY >= GRIDSIZE ||
                    nextZ < 0 || nextZ >= GRIDSIZE ||
                    visited.contains(Cube(nextY, nextX, nextZ)) ||
                    grid[nextY][nextX][nextZ]
                ) {
                    continue
                }

                queue.add(Cube(nextY, nextX, nextZ))

                if (nextX == 0 || nextX == GRIDSIZE - 1 ||
                    nextY == 0 || nextY == GRIDSIZE - 1 ||
                    nextZ == 0 || nextZ == GRIDSIZE - 1
                ) {
                    escaping+= visited
                    return true
                }
            }
        }
        noEscaping+=visited
        return false
    }


}


// 2003 too low




