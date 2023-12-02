package days.year2023

import days.Day

fun main() {
    println(Day17().solve())
}

class Day17 : Day(17, 2016) {
    var position = Point(0, 0)
    val end = Point(3, 3)

    var passcodeI = "awrkjxxr"

    var longest: Int = 0;

    var list: MutableList<Int> = mutableListOf()

    val map: Map<Direction, String> = mapOf(Direction.RIGHT to "R", Direction.LEFT to "L", Direction.UP to "U", Direction.DOWN to "D")

    fun solve(): Any {
        move(passcodeI, position, null)
        return longest;
    }

    private fun move(passcode: String, position: Point, direction: Direction?) {
        var newpasscode = passcode;
        if (direction != null) {
            position.move(direction)
            newpasscode = passcode + map[direction]
        }

        if (position == end) {
            var answer = newpasscode.removePrefix(passcodeI)
            longest = Math.max(answer.length, longest)
            return
        }

        var options = md5(newpasscode).substring(0, 4).map { isOpen(it) }
        var (up, down, left, right) = options
        up = up && uIsPossible(position)
        down = down && dIsPossible(position)
        right = right && rIsPossible(position)
        left = left && lIsPossible(position)
        var actualOptions = mutableListOf<Direction>()
        if (up) actualOptions.add(Direction.UP)
        if (down) actualOptions.add(Direction.DOWN)
        if (right) actualOptions.add(Direction.RIGHT)
        if (left) actualOptions.add(Direction.LEFT)

        actualOptions.shuffled().forEach {
            move(newpasscode, position.copy(), it)
        }
    }

    fun isOpen(char: Char): Boolean {
        return !(char == 'a' || char.isDigit())
    }

    fun uIsPossible(pos: Point): Boolean {
        return pos.y > 0;
    }

    fun dIsPossible(pos: Point): Boolean {
        return pos.y < 3;
    }

    fun rIsPossible(pos: Point): Boolean {
        return pos.x < 3;
    }

    fun lIsPossible(pos: Point): Boolean {
        return pos.x > 0;
    }


}