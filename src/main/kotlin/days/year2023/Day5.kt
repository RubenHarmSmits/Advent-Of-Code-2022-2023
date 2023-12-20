package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day5().solve())
}

class Day5 : Day(5, 2023) {
    var input = "222541566 218404460 670428364 432472902 2728902838 12147727 3962570697 52031641 2849288350 113747257 3648852659 73423293 4036058422 190602154 1931540843 584314999 3344622241 180428346 1301166628 310966761";
    val seeds = input.split(" ").map { it.toLong() }
    var seedsrange = mutableListOf<LongRange>();

    fun solve(): Any {
        seeds.chunked(2).forEach { seedsrange.add(it[0] until (it[0] + it[1])) }
        var overview = inputList
                .splitBy { it == "" }
                .map {
                    it.subList(1, it.size)
                            .map { extraxtAllLongsFromString(it) }
                }

        overview.forEach {
            seedsrange = findCorresponding(seedsrange, it)
        }
        return seedsrange.minOf{it.first}
    }

    private fun findCorresponding(cur: MutableList<LongRange>, mapper: List<List<Long>>): MutableList<LongRange> {
        val a = cur.map {
            findCorresponding(it, mapper)
        }.flatten()
        return a.toMutableList()
    }

    private fun findCorresponding(cur: LongRange, mapper: List<List<Long>>): MutableList<LongRange> {
        var destinationRange = mutableListOf<LongRange>()
        var innerRanges = mutableListOf<LongRange>()

        mapper.forEach {
            val (destination, source, range) = it
            var sourceRange = source until source + range
            var interRange = intersectRanges(cur, sourceRange)
            if (interRange != null) {
                innerRanges.add(interRange);
                var start = destination + interRange.first - source
                var end = destination + interRange.last - source
                var outputRange = start..end
                destinationRange.add(outputRange)
            }
        }
        val outersects = outersectWithList(cur, innerRanges);
        destinationRange.addAll(outersects)
        return destinationRange
    }



}
