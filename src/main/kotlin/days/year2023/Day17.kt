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
        it.map { it.digitToInt() }
    }

    val sizey = grid.size
    val sizex = grid[0].size
    val endpoint = Point(sizey - 1, sizex - 1)

    fun solve(): Int {
        var priorityQueue = mutableListOf<Node>()
        for (y in 0..sizey) {
            for (x in 0..sizex) {
                listOf(Direction.LEFT, Direction.RIGHT, Direction.DOWN, Direction.UP).forEach { d ->
                    for (l in 0 until 10) {
                        priorityQueue.add(Node(Point(y, x), d, l, Int.MAX_VALUE, false))
                    }
                }
            }
        }
        val startNode = Node(Point(0, 0), null, 0, 0, false)
        priorityQueue.add(startNode)

        while (priorityQueue.isNotEmpty()) {
            priorityQueue = priorityQueue.sortedBy { it.weight }.toMutableList()
            val node = priorityQueue[0]
            priorityQueue.removeFirst()

            if (node.point == endpoint && node.amountStraight >=3) return node.weight

            val neighnodes = mutableListOf<Pair<Point, Direction>>()

            if (node.point.x != 0) neighnodes.add(Point(node.point.y, node.point.x - 1) to Direction.LEFT)
            if (node.point.x != sizex - 1) neighnodes.add(Point(node.point.y, node.point.x + 1) to Direction.RIGHT)
            if (node.point.y != 0) neighnodes.add(Point(node.point.y - 1, node.point.x) to Direction.UP)
            if (node.point.y != sizey - 1) neighnodes.add(Point(node.point.y + 1, node.point.x) to Direction.DOWN)

            neighnodes.forEach { (point, dir) ->
                var amountStraight = 0
                if (node.direction == dir) {
                    amountStraight = node.amountStraight + 1
                }
                val findNeighnode = priorityQueue.find { it.point == point && amountStraight == it.amountStraight && dir == it.direction }
                if (findNeighnode != null && isAllowed(dir, node) && !findNeighnode.visited){
                    findNeighnode.weight = min(findNeighnode.weight, node.weight + grid.get(findNeighnode.point))
                }
            }
            node.visited = true
        }

        return -1
    }

    private fun isAllowed(dir: Direction, node: Node): Boolean {
        if (node.amountStraight < 3) return dir == node.direction || node.direction == null
        return !directionIsOpposite(dir, node.direction)
    }
    data class Node(val point: Point, val direction: Direction?, val amountStraight: Int, var weight: Int, var visited: Boolean)

}
