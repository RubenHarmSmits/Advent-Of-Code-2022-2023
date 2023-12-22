package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day22().solve())
}

class Day22 : Day(22, 2023) {

    var bricks = inputList.mapIndexed { i, it ->
        var (f, s) = it.split("~");
        val cfi = extraxtAllIntsFromString(f)
        val cf = Coor(cfi[0], cfi[1], cfi[2])
        val csi = extraxtAllIntsFromString(s)
        Brick(cf, csi[0] - cfi[0], csi[1] - cfi[1], csi[2] - cfi[2], mutableSetOf(), mutableSetOf(), i)
    }

    fun solve(): Any {

        bricks.sortedBy { it.corner.z }.forEach { moveDown(it) }

        bricks.forEach { brick ->
            brick.steuntOn.forEach { steuntje ->
                bricks.find { steuntje == it.name }!!.dependentOnMe.add(brick.name)
            }
        }

        return bricks.sumOf { it.getDependenOnMe(bricks.sortedBy { it.corner.z }, mutableSetOf(it.name)).size - 1 }

    }


    private fun moveDown(brick: Brick) {
        var moving = true
        while (moving) {
            if (brick.corner.z > 1) {
                // check if something is 1 step under
                var emtyUnder = true
                for (y in 0..brick.lengthy) {
                    for (x in 0..brick.lengthx) {
                        val brickUnder = bricks.filter { it != brick }.find { it.containsCoor(Coor(brick.corner.x + x, brick.corner.y + y, brick.corner.z - 1)) }
                        if (brickUnder != null) {
                            emtyUnder = false
                            moving = false
                            brick.steuntOn.add(brickUnder.name)
                        }
                    }
                }
                if (emtyUnder) brick.corner.z--
            } else moving = false
        }
    }

}

data class Coor(var x: Int, var y: Int, var z: Int)

data class Brick(val corner: Coor, val lengthx: Int, val lengthy: Int, val lengthz: Int, var steuntOn: MutableSet<Int>, var dependentOnMe: MutableSet<Int>, val name: Int) {

    fun containsCoor(coor: Coor): Boolean {
        for (x in this.corner.x..this.corner.x + lengthx) {
            for (y in this.corner.y..this.corner.y + lengthy) {
                for (z in this.corner.z..this.corner.z + lengthz) {
                    if (coor == Coor(x, y, z)) return true
                }
            }
        }
        return false
    }

    fun getDependenOnMe(bricks: List<Brick>, dependencies: MutableSet<Int>): MutableSet<Int> {
        bricks.filter { potentionDependency -> potentionDependency.corner.z > this.corner.z }
                .forEach {
                    if (dependencies.containsAll(it.steuntOn)) dependencies.add(it.name)
                }
        return dependencies
    }
}

