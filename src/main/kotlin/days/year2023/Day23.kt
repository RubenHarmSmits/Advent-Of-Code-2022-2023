package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day23().solve())
}

class Day23 : Day(23, 2023) {
    var startingPoint = Point(0, 1)
    var endPoint = Point(140, 139)

    var highest = 0;

    var grid = matrixOfInput(inputList)
    var nodes = mutableListOf<Node>()

    fun solve(): Any {
        for (y in grid.indices) {
            for (x in grid[0].indices) {
                if (grid[y][x] == '.') {
                    if (grid.getAdjacentCoordinates(Point(y, x)).filter { grid.get(it) == '.' }.size > 2) {
                        nodes.add(Node(Point(y, x)))
                    }
                }
            }
        }

        nodes.add(Node(startingPoint))
        nodes.add(Node(endPoint))

        nodes.forEach { node ->
            grid.getAdjacentCoordinates(node.point).filter { grid.get(it) != '#' }.forEach { direction ->
                node.options.add(getNextNode(direction, mutableListOf(node.point)))
            }
        }

        traverse(findNode(startingPoint), mutableListOf(), 0)

        return highest;
    }

    private fun traverse(node: Node, history: MutableList<Node>, total: Int) {
        if (node.point == endPoint) {
            highest = max(highest, total)
            return
        }

        history.add(node)
        node.options.filter { !history.contains(it.first) }.forEach { optionNodePair ->
            traverse(optionNodePair.first, history.toMutableList(), total + optionNodePair.second)
        }

    }

    private fun findNode(point: Point): Node {
        return nodes.find { point == it.point }!!
    }

    private fun getNextNode(point: Point, history: MutableList<Point>): Pair<Node, Int> {
        history.add(point)
        val possibilities = grid.getAdjacentCoordinates(point).filter { grid.get(it) != '#' }.filter { !history.contains(it) }
        return if (nodes.map { it.point }.contains(point)) {
            nodes.find { it.point == point }!! to history.size - 1
        } else getNextNode(possibilities[0], history)
    }

    data class Node(val point: Point) {
        var options: MutableList<Pair<Node, Int>> = mutableListOf()
    }

}
