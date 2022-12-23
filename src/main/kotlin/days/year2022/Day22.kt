package days.year2022

import days.Day


fun main() {
    println(Day22().solve())
}


class Day22 : Day(22) {

    fun solve(): Any {
        var tot = 0
        val sides = inputList.splitBy { it == "" }.map { it.map { it.split("").filter { it != "" } } }

        val instructionsS = "10R5L5R10L4R5L5"

        val instructions = Regex("(?<=[RL])|(?=[RL])").split(instructionsS)

        // 0T 1R 2F 3D 4L 5B

        val sidesList = listOf('T', 'R', 'F', 'D', 'L', 'B')
        val sidesMap = mapOf(Pair('T', 0), Pair('R', 1), Pair('F', 2), Pair('D', 3), Pair('L', 4), Pair('B', 5))

        var side = 0
        val startX = sides[side][1].indexOf(".")
        var location = Point(1, startX)
        var direction = ">"

        for (instruction in instructions) {
            var grid = sides[side]
            if (isNumeric(instruction)) {
                var i = 0
                while (i < instruction.toInt()) {
                    i++
                    if (direction == ">") {
                        val next = grid[location.y][location.x + 1]
                        if (next == ".") location.move('R')
                        if (next == "#") break
                        if (next == "X") {
                            val x = grid[location.y].indexOfFirst { it == "." || it == "#" }
                            if (grid[location.y][x] == "#") break
                            else location.x = x
                        }
                    }

                    if (direction == "<") {
                        val next = grid[location.y][location.x - 1]
                        if (next == ".") location.move('L')
                        if (next == "#") break
                        if (next == "" || next == " " || next == "X") {
                            val x = grid[location.y].indexOfLast { it == "." || it == "#" }
                            if (grid[location.y][x] == "#") break
                            else location.x = x
                        }
                    }

                    if (direction == "^") {
                        val next = grid[location.y - 1][location.x]
                        if (next == ".") location.move('U')
                        if (next == "#") break
                        if (next == "" || next == " " || next == "X") {
                            val y = grid.map { it[location.x] }.indexOfLast { it == "." || it == "#" }
                            if (grid[y][location.x] == "#") break
                            else location.y = y
                        }
                    }

                    if (direction == "v") {
                        val next = grid[location.y + 1][location.x]
                        if (next == ".") location.move('D')
                        if (next == "#") break
                        if (next == "X") {

                            when (sidesList[side]) {
                                'T' -> {
                                    grid = sides[sidesMap['F']!!]
                                }
                            }

                            if (grid[1][location.x] == "#") break
                            else {
                                location.y = 1

                            }
                        }
                    }
                }


            } else {
                direction = turn(direction, instruction == "R")
            }


        }


        val row = location.y
        val column = location.x - 1
        val facing = when (direction) {
            ">" -> 0
            "<" -> 2
            "^" -> 3
            "v" -> 1
            else -> 0
        }


        return 1000 * row + 4 * column + facing
//        return 1;
    }

    private fun turn(direction: String, moveRight: Boolean): String {
        var nDirection = "X"
        if (direction == ">") {
            if (moveRight) nDirection = "v"
            else nDirection = "^"
        }

        if (direction == "<") {
            if (moveRight) nDirection = "^"
            else nDirection = "v"
        }

        if (direction == "^") {
            if (moveRight) nDirection = ">"
            else nDirection = "<"
        }

        if (direction == "v") {
            if (moveRight) nDirection = "<"
            else nDirection = ">"
        }

        return nDirection
    }


    fun isNumeric(str: String) = str.all { it in '0'..'9' }


}


// 90284 high
//90280

// 88272

//88268
