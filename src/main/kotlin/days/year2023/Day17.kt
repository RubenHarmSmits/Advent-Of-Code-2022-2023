package days.year2023

import days.Day
import days.Matrix
import java.util.*
import kotlin.math.*

fun main() {
    println(Day17().solve())
}

class Day17 : Day(17, 2023) {
    val grid = matrixOfInput(inputList).toMutableMatrix().map {
        it.map {
            it.digitToInt()
        }
    }

    var list: MutableList<Int> = mutableListOf()

    val endpoint = Point(grid.size - 1, grid[0].size - 1)

    var mimimunHeat = 5000

    fun solve(): Any {
//        findLowestWeightedPath(grid)
        traverse(Point(0, -1), Direction.RIGHT, mutableListOf(), 0)
        return mimimunHeat
    }

    fun traverse(point: Point, direction: Direction, memory: MutableList<Pair<Direction, Point>>, totalHeat: Int) {
        point.move(direction)
        if (point.y < 0 || point.x < 0 || point.y >= grid.size || point.x >= grid[0].size) return
        if (memory.map { it.second }.contains(point)) return
        memory.add(direction to point.copy())
        if (fourInARow(memory.map { it.first })) return
        val newHeat = totalHeat + grid.get(point)
        if (newHeat >= mimimunHeat) return
        if (point == endpoint) {
            mimimunHeat = min(newHeat, mimimunHeat)
            println(mimimunHeat)
            return
        }
        val map = mapOf(Direction.RIGHT to Direction.LEFT, Direction.LEFT to Direction.RIGHT, Direction.UP to Direction.DOWN, Direction.DOWN to Direction.UP)

        val options = mutableListOf(Direction.DOWN, Direction.RIGHT, Direction.UP, Direction.LEFT).filter { it != map[direction] }

        options.forEach { dir ->
            traverse(point.copy(), dir, memory.map { Pair(it.first, it.second.copy()) }.toMutableList(), newHeat)
        }


    }

    private fun fourInARow(memory: List<Direction>): Boolean {
        return if (memory.size >= 4) {
            memory.takeLast(4).all { it == memory.last() }
        } else {
            false
        }
    }

    fun dijkstra(matrix: Matrix<Int>): Int {
        val rows = matrix.size
        val cols = matrix[0].size

        // Priority queue to store vertices with their cumulative weights
        val priorityQueue = PriorityQueue<Node>(compareBy { it.weight })
        priorityQueue.add(Node(Point(0, 0), 0))

        // Visited array to keep track of visited vertices
        val visited = Array(rows) { BooleanArray(cols) }

        // Dijkstra's algorithm
        while (priorityQueue.isNotEmpty()) {
            val node = priorityQueue.poll()

            if (node.point==endpoint) return node.weight

            grid.getAdjacentCoordinates(node.point).forEach {

            }

            // Explore neighbors (down and right)
            for ((rowOffset, colOffset) in listOf(1 to 0, 0 to 1)) {
                val newRow = node.point.y + rowOffset
                val newCol = node.point.x + colOffset

                // Check if the new position is within the matrix bounds
                if (newRow in 0 until rows && newCol in 0 until cols && !visited[newRow][newCol]) {
                    val newWeight = node.weight + matrix[newRow][newCol]
                    priorityQueue.add(Node(Point(newRow, newCol), newWeight))
                    visited[newRow][newCol] = true
                }
            }
        }

        // If no path is found, return a sentinel value or throw an exception
        return -1
    }

//    data class Node(val point: Point, val direction: Direction, val weight: Int, val amountStraight: Int)
//    data class NodeV(val point: Point, val direction: Direction, val amountStraight: Int)
    data class Node(val point: Point, val weight: Int)
//    data class NodeV(val point: Point, val direction: Direction, val amountStraight: Int)


}