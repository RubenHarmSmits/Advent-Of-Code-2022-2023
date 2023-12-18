package days.year2023

import days.Day
import days.Matrix

fun main() {
    println(Day16().solve())
}

class Day16 : Day(16, 2023) {

    var grid: Matrix<Char> = matrixOfInput(inputList);

    fun solve(): Any {

        return grid.indices.maxOf { coor ->
            listOf(
                    Point(coor, -1) to Direction.RIGHT,
                    Point(coor, grid.size) to Direction.LEFT,
                    Point(-1, coor) to Direction.DOWN,
                    Point(grid[0].size, coor) to Direction.DOWN)
                    .maxOf {
                        solveFromStart(it.second, it.first)
                    }
        }
    }


    private fun solveFromStart(direction: Direction, point: Point): Int {
        val energized = mutableSetOf<Point>()
        light(direction, point, mutableListOf(), energized)
        return energized.size;
    }


    private fun light(direction: Direction, point: Point, mem: MutableList<Pair<Point, Direction>>, energized: MutableSet<Point>) {
        point.move(direction)

        val newMem = Pair(point, direction)
        if (mem.contains(newMem)) return
        mem.add(newMem)

        if (point.y < 0 || point.x < 0 || point.y >= grid.size || point.x >= grid[0].size) return
        energized.add(point)

        when (grid.get(point)) {
            '.' -> light(direction, point.copy(), mem, energized)
            '\\' -> {
                when (direction) {
                    Direction.DOWN -> light(Direction.RIGHT, point.copy(), mem, energized)
                    Direction.UP -> light(Direction.LEFT, point.copy(), mem, energized)
                    Direction.LEFT -> light(Direction.UP, point.copy(), mem, energized)
                    Direction.RIGHT -> light(Direction.DOWN, point.copy(), mem, energized)
                }
            }

            '/' -> {
                when (direction) {
                    Direction.DOWN -> light(Direction.LEFT, point.copy(), mem, energized)
                    Direction.UP -> light(Direction.RIGHT, point.copy(), mem, energized)
                    Direction.LEFT -> light(Direction.DOWN, point.copy(), mem, energized)
                    Direction.RIGHT -> light(Direction.UP, point.copy(), mem, energized)
                }
            }

            '|' -> {
                when (direction) {
                    Direction.LEFT, Direction.RIGHT -> {
                        light(Direction.UP, point.copy(), mem, energized)
                        light(Direction.DOWN, point.copy(), mem, energized)
                    }

                    else -> light(direction, point.copy(), mem, energized)
                }
            }

            '-' -> {
                when (direction) {
                    Direction.DOWN, Direction.UP -> {
                        light(Direction.RIGHT, point.copy(), mem, energized)
                        light(Direction.LEFT, point.copy(), mem, energized)
                    }

                    else -> light(direction, point.copy(), mem, energized)
                }
            }
        }
    }


}
