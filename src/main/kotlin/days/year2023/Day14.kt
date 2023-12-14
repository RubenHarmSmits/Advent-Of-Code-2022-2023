package days.year2023

import days.Day
import days.Matrix

fun main() {
    println(Day14().solve())
}

class Day14 : Day(14, 2023) {
    fun solve(): Any {
        var matrix = rotateMatrixCLockwise(matrixOfInput(inputList), 2)
        val repeatsAfter = 112
        val repeatinCycle = 7
        repeat((1000000000 - repeatsAfter) % repeatinCycle + repeatsAfter) {
            repeat(4) {
                matrix = tiltGrid(rotateMatrixCLockwise(matrix, 1))
            }
        }
        return getScore(rotateMatrixCLockwise(matrix, 2))
    }

    private fun tiltGrid(grid: Matrix<Char>): Matrix<Char> {
        var newMatrix = mutableListOf<List<Char>>()
        grid.forEach { line ->
            val indexesHT = mutableListOf<Int>()
            val indexesO = mutableListOf<Int>()

            for ((index, char) in line.withIndex()) {
                if (char == '#') {
                    indexesHT.add(index)
                }
            }

            line.forEachIndexed { i, it ->
                if (it == 'O') {
                    var found = false
                    for (x in i - 1 downTo 0) {
                        if (x in indexesHT || x in indexesO) {
                            indexesO.add(x + 1)
                            found = true
                            break
                        }
                    }
                    if (!found) indexesO.add(0)
                }
            }

            val newLine = mutableListOf<Char>()
            for (i in line.indices) {
                if (indexesO.contains(i)) newLine.add('O')
                else if (indexesHT.contains(i)) newLine.add('#')
                else newLine.add('.')
            }
            newMatrix.add(newLine)
        }
        return matrixOf(newMatrix)

    }

    private fun getScore(matrix: Matrix<Char>): Int {
        return matrix.mapIndexed { i, line ->
            line.count { it == 'O' } * (matrix.size - i)
        }.sum()

    }
}