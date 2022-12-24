package days.year2022

import days.Day


fun main() {
    println(Day24().solve())
}


class Day24 : Day(24) {

    var end = Point(26, 120)
    val start = Point(0, 1)
    val max = 500
    var grid = inputList.map { it.toMutableList() }
    var blizzards = mutableListOf<Pair<Day.Point, Char>>()
    val SIZEY = grid.size
    val SIZEX = grid[0].size
    val memory = mutableMapOf<String, String>()
    var fastest = max

    fun solve(): Any {
        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if (c != '.' && c != '#') blizzards.add(Pair(Point(y, x), c))
            }
        }

        grid = grid.map { it.map { if (it in "^v><") '.' else it }.toMutableList() }

        findEnd(blizzards.toMutableList(), start.copy(), 0, end)
        val fastest1 = fastest
        var nBlizzards = blizzards
        repeat(fastest) {
            nBlizzards = makeBlizzardsMove(nBlizzards)
        }
        fastest = max
        memory.clear()
        findEnd(nBlizzards.toMutableList(), end.copy(), 0, start)
        val fastest2 = fastest

        nBlizzards = blizzards
        repeat(fastest1 + fastest) {
            nBlizzards = makeBlizzardsMove(nBlizzards)
        }
        fastest = max
        memory.clear()
        findEnd(nBlizzards.toMutableList(), start.copy(), 0, end)

        return fastest1 + fastest2 + fastest
    }

    private fun findEnd(
        blizzards: MutableList<Pair<Day.Point, Char>>,
        position: Point,
        minute: Int,
        goal: Point
    ) {
        if (manhattanDistance(position, goal) + minute >= fastest) return
        if (position.y == goal.y && position.x == goal.x) {
            if (minute < fastest) {
                fastest = minute
            }
            return
        }
        val state = createState(position, minute)
        if (memory[state] == null) memory[state] = "";
        else return

        val nBlizzards = makeBlizzardsMove(blizzards)
        var moves = mutableListOf<Point>()

        if (nBlizzards.none { (positionB, dir) -> positionB.y == position.y + 1 && positionB.x == position.x } && position.y < SIZEY - 1 && grid[position.y + 1][position.x] == '.') moves.add(
            Point(position.y + 1, position.x)
        )
        if (nBlizzards.none { (positionB, dir) -> positionB.y == position.y - 1 && positionB.x == position.x } && position.y > 0 && grid[position.y - 1][position.x] == '.') moves.add(
            Point(position.y - 1, position.x)
        )
        if (nBlizzards.none { (positionB, dir) -> positionB.y == position.y && positionB.x == position.x + 1 } && grid[position.y][position.x + 1] == '.') moves.add(
            Point(position.y, position.x + 1)
        )
        if (nBlizzards.none { (positionB, dir) -> positionB.y == position.y && positionB.x == position.x - 1 } && grid[position.y][position.x - 1] == '.') moves.add(
            Point(position.y, position.x - 1)
        )

        if (nBlizzards.none { (positionB, dir) -> positionB.y == position.y && positionB.x == position.x } && grid[position.y][position.x] == '.') moves.add(
            Point(position.y, position.x)
        )

        for (move in moves) {
            findEnd(nBlizzards.toMutableList(), move.copy(), minute + 1, goal)
        }
    }

    private fun makeBlizzardsMove(blizzards: MutableList<Pair<Point, Char>>): MutableList<Pair<Point, Char>> {
        var nBlizzards = mutableListOf<Pair<Point, Char>>()
        for (blizz in blizzards.toMutableList()) {
            var bliz = blizz.copy()
            var (poss, dir) = bliz
            val pos = poss.copy()
            if (dir == '>' && pos.x == SIZEX - 2) pos.x = 1
            else if (dir == '<' && pos.x == 1) pos.x = SIZEX - 2
            else if (dir == 'v' && pos.y == SIZEY - 2) pos.y = 1
            else if (dir == '^' && pos.y == 1) pos.y = SIZEY - 2
            else pos.move(dir)
            nBlizzards.add(Pair(Point(pos.y, pos.x), dir))
        }
        return nBlizzards
    }

    private fun createState(position: Day.Point, minute: Int): String {
        val y = minute % 25
        val x = minute % 120
        return "y$y x$x p$position m$minute"
    }
}
