package days.year2022

import days.Day

fun main() {
    println(Day7().solve())
}

class Day7 : Day(7) {

    private var firstF = File("/", 0, true)
    private var current = firstF
    private var listFiles = mutableListOf(firstF)

    fun solve(): Int {

        for ((i, it) in inputList.withIndex()) {
            val parsed = it.split(" ")
            when {
                parsed[1] == "cd" -> {
                    val newName = parsed[2]
                    current = if (newName == "..") {
                        current.parent!!
                    } else {
                        current.inner.firstOrNull() { it.name == newName } ?: current
                    }
                }

                parsed[1] == "ls" -> {
                    var start = 1
                    while (i + start < inputList.size && !inputList[i + start].startsWith('$')) {
                        val (first, newName) = inputList[i + start].split(" ")
                        if (!current.inner.any { it.name == newName }) {
                            val isDir = first == "dir"
                            val newFile = File(newName, if (isDir) 0 else first.toInt(), isDir, current)
                            listFiles.add(newFile)
                            current.inner.add(newFile)
                        }
                        start++
                    }
                }
            }
        }
        return listFiles
            .filter { it.isDir && it.calcSize() >= 30000000 - (70000000 - firstF.calcSize()) }
            .minOf { it.calcSize() }
    }
}

data class File(
    val name: String,
    val size: Int,
    val isDir: Boolean,
    val parent: File? = null,
    var inner: MutableList<File> = mutableListOf()
) {

    fun calcSize(): Int {
        return if (this.isDir) inner.sumOf { it.calcSize()} else size
    }
}