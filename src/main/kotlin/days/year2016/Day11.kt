package days.year2016

import days.Day

fun main() {
    val start = System.currentTimeMillis()
    println(Day11().partOne())
    println(System.currentTimeMillis() - start)
}

class Day11 : Day(11, 2016) {

    var highest = 60

    val mem: MutableMap<String, Int> = mutableMapOf();


    fun partOne(): Any {
        var items1 = mutableListOf(
                Item("LG", 3, listOf(), ""),
                Item("HG", 2, listOf(), ""),
                Item("HM", 1, listOf("LG"), "HG"),
                Item("LM", 1, listOf("HG"), "LG")
        )
        var items2 = mutableListOf(
                Item("PrG", 1, listOf(), ""),
                Item("PrM", 1, listOf("CoG", "CuG", "RG", "PlG", "EG", "DG"), "PrG"),

                Item("CoG", 2, listOf(), ""),
                Item("CuG", 2, listOf(), ""),
                Item("RG", 2, listOf(), ""),
                Item("PlG", 2, listOf(), ""),

                Item("CoM", 3, listOf("PrG", "CuG", "RG", "PlG", "EG", "DG"), "CoG"),
                Item("CuM", 3, listOf("CoG", "PrG", "RG", "PlG", "EG", "DG"), "CuG"),
                Item("RM", 3, listOf("CoG", "CuG", "PrG", "PlG", "EG", "DG"), "RG"),
                Item("PlM", 3, listOf("CoG", "CuG", "RG", "PrG", "EG", "DG"), "PlG"),

                Item("EG", 1, listOf(), ""),
                Item("DG", 1, listOf(), ""),
                Item("EM", 1, listOf("CoG", "CuG", "RG", "PlG", "PrG", "DG"), "EG"),
                Item("DM", 1, listOf("CoG", "CuG", "RG", "PlG", "PrG", "EG"), "DG"),
        )

        var E = 1

        tryAllMoves(items2, null, E, 0)

        return highest
    }

    fun tryAllMoves(itemsO: MutableList<Item>, move: Move?, E: Int, moveNumber: Int) {
        var tempE = E
        var items = itemsO.map { it.copy() }.toMutableList()
        if (move != null) {
            if (move.up) {
                tempE++
                items.filter { move.items.contains(it) }.forEach { it.floor++ }
            } else {
                tempE--
                items.filter { move.items.contains(it) }.forEach { it.floor-- }
            }
        }

        if (radiation(items) || checkWin(items, moveNumber) || moveNumber >= highest - 1) return

        val hash = items.map { it.hasj() }.toString() +"$tempE"
        val fastest: Int? = mem[hash]

        if (fastest == null || moveNumber < fastest) mem[hash]=moveNumber
        else return

        getPossibleMoves(items, tempE)
                .shuffled()
                .forEach {
            tryAllMoves(items, it, tempE, moveNumber + 1)
        }
    }


    private fun checkWin(items: MutableList<Item>, moveNumber: Int): Boolean {
        if (items.all { it.floor == 4 }) {
            highest = moveNumber
            println(highest)
            return true
        }
        return false
    }


    private fun radiation(items: List<Item>): Boolean {
        items.groupBy { it.floor }.map { it.value }.forEach { row ->
            if (row.any {
                        isAnyElementCommon(it.allergic, row.map { it.name }) && !row.map { it.name }.contains(it.savedBy)
                    }) return true
        }
        return false;
    }

    private fun getPossibleMoves(items: List<Item>, E: Int): List<Move> {
        val itemsOnFloor = items.filter { E == it.floor }
        var possibleMoves: MutableList<Move> = mutableListOf();
        itemsOnFloor.forEach { possibleMoves.add(Move(listOf(it), true)) }


        for (i in itemsOnFloor.indices) {
            for (y in i until itemsOnFloor.size) {
                val first = itemsOnFloor[i]
                val second = itemsOnFloor[y]
                if (first != second) possibleMoves.add(Move(listOf(first, second), true))
            }
        }

        possibleMoves.map { it.copy() }.forEach { possibleMoves.add(Move((it.items), false)) }

        if (E == 1) possibleMoves = possibleMoves.filter { it.up }.toMutableList()
        if (E == 4) possibleMoves = possibleMoves.filter { !it.up }.toMutableList()

        return possibleMoves;
    }
    fun isAnyElementCommon(
            aList: List<String>,
            bList: List<String>
    ): Boolean {
        val aSet = aList.toSet()
        return bList.any { it in aSet }
    }

}


data class Item(var name: String, var floor: Int, val allergic: List<String>, val savedBy: String) {
    fun hasj(): String {
        return "$name$floor-"
    }

}


data class Move(var items: List<Item>, var up: Boolean){
    override fun toString():String{
        return items.map { it.hasj() }.toString() + up
    }
}



