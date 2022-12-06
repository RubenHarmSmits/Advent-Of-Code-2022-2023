package util

import java.io.File

object InputReader {

    fun getInputAsString(day: Int, year:Int): String {
        return fromResources(day, year).readText().replace("\r", "")
    }

    fun getInputAsList(day: Int, year:Int): List<String> {
        return fromResources(day, year).readLines()
    }

    fun getInputAsListInt(day: Int, year:Int): List<Int> {
        return fromResources(day, year).readLines().map{it.toInt()}
    }

    private fun fromResources(day: Int, year:Int): File {
        return File(javaClass.classLoader.getResource("$year/input_day_$day.txt").toURI())
    }
}
