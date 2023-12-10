package days.year2023

import days.Day

fun main() {
    println(Day10().solve())
}

class Day10 : Day(10, 2023) {

    val grid = matrixOfInput(inputList)

    var tot = 0
    var count = 0;
    var direction = Direction.UP

    var start = Point(18, 104)
    var current = start

    var loop = mutableListOf<Point>()
    fun solve(): Any {
        var found = false
        while (!found) {
            loop.add(current)
            tot++
            val currentChar = grid.get(current)
            if (currentChar == 'F') {
                if (direction == Direction.UP) move(Direction.RIGHT)
                else move(Direction.DOWN)
            } else if (currentChar == '-') {
                if (direction == Direction.RIGHT) move(Direction.RIGHT)
                else move(Direction.LEFT)
            } else if (currentChar == '|') {
                if (direction == Direction.DOWN) move(Direction.DOWN)
                else move(Direction.UP)
            } else if (currentChar == 'J') {
                if (direction == Direction.DOWN) move(Direction.LEFT)
                else move(Direction.UP)
            } else if (currentChar == 'L') {
                if (direction == Direction.DOWN) move(Direction.RIGHT)
                else move(Direction.UP)
            } else if (currentChar == '7') {
                if (direction == Direction.UP) move(Direction.LEFT)
                else move(Direction.DOWN)
            }

            if (current == start) found = true
        }

        return grid.mapIndexed { y, line ->
            var inside = false
            var lastCorner: Char? = null
            var amount = 0
            for (x in line.indices) {
                if (loop.contains(Point(y, x))) {
                    val char = grid[y][x]
                    if ("|".contains(char)) {
                        inside = !inside
                    }
                    if ("FL7J".contains(char)) {
                        if (lastCorner == null) lastCorner = char
                        else {
                            if (lastCorner == 'F' && char == 'J') {
                                inside = !inside
                            }
                            if (lastCorner == 'L' && char == '7') {
                                inside = !inside
                            }
                            lastCorner = null;
                        }
                    }
                } else {
                    if (inside) amount++
                }
            }
            amount
        }.sum()
    }

    private fun move(dir: Direction) {
        direction = dir
        current = current.moveInDirection(dir)
    }

}