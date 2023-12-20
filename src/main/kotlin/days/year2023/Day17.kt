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


    fun solve(): Any {
        return dijkstra(grid)
    }

    fun dijkstra(matrix: Matrix<Int>): Int {
        val size = matrix.size

        // Priority queue to store vertices with their cumulative weights
        var priorityQueue = mutableListOf<Node>()
        for (y in 0..size) {
            for (x in 0..size) {
                listOf(Direction.LEFT, Direction.RIGHT, Direction.DOWN, Direction.UP).forEach { d ->
                    for (l in 0..2) {
                        priorityQueue.add(Node(Point(y, x), d, l, Int.MAX_VALUE, false))
                    }
                }
            }
        }

        priorityQueue.find { it.point == Point(0, 0) && it.direction == Direction.LEFT && it.amountStraight == 0 }!!.weight = 0

        while (priorityQueue.isNotEmpty()) {
            priorityQueue = priorityQueue.sortedBy { it.weight }.toMutableList()
            val node = priorityQueue[0]
            priorityQueue.removeFirst()

            if (node.point == endpoint) {
                return node.weight
            }
            val neighnodes = mutableListOf<Node>()

            if (node.point.x != 0) {
                val dir = Direction.LEFT
                var amountStraight = 0
                if (node.direction == dir) {
                    amountStraight = node.amountStraight + 1
                }
                val findNeighnode = priorityQueue.find { it.point == Point(node.point.y, node.point.x - 1) && amountStraight == it.amountStraight && dir == it.direction }
                if (findNeighnode != null) neighnodes.add(findNeighnode)
            }
            if (node.point.x != size - 1) {
                val dir = Direction.RIGHT
                var amountStraight = 0
                if (node.direction == dir) {
                    amountStraight = node.amountStraight + 1
                }
                val findNeighnode = priorityQueue.find { it.point == Point(node.point.y, node.point.x + 1) && amountStraight == it.amountStraight && dir == it.direction }
                if (findNeighnode != null) neighnodes.add(findNeighnode)
            }
            if (node.point.y != 0) {
                val dir = Direction.UP
                var amountStraight = 0
                if (node.direction == dir) {
                    amountStraight = node.amountStraight + 1
                }
                val findNeighnode = priorityQueue.find { it.point == Point(node.point.y - 1, node.point.x) && amountStraight == it.amountStraight && dir == it.direction }
                if (findNeighnode != null) neighnodes.add(findNeighnode)
            }
            if (node.point.y != size - 1) {
                val dir = Direction.DOWN
                var amountStraight = 0
                if (node.direction == dir) {
                    amountStraight = node.amountStraight + 1
                }
                val findNeighnode = priorityQueue.find { it.point == Point(node.point.y + 1, node.point.x) && amountStraight == it.amountStraight && dir == it.direction }
                if (findNeighnode != null) neighnodes.add(findNeighnode)
            }

            neighnodes.forEach { neighNode ->
                if (!neighNode.visited) {
                    neighNode.weight = min(neighNode.weight, node.weight + grid.get(neighNode.point))
                }
            }
            node.visited = true
        }


        return -1
    }

    data class Node(val point: Point, val direction: Direction, val amountStraight: Int, var weight: Int, var visited: Boolean)

}

// 966 too low