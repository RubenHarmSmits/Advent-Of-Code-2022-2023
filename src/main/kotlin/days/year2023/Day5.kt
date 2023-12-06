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

    fun intersectRanges(range1: LongRange, range2: LongRange): LongRange? {
        val start = maxOf(range1.start, range2.start)
        val endInclusive = minOf(range1.endInclusive, range2.endInclusive)

        // Check if there is a valid intersection
        if (start <= endInclusive) {
            return start..endInclusive
        } else {
            return null // No intersection
        }
    }


    fun outersectRanges(range1: LongRange, range2: LongRange): MutableList<LongRange> {
        val intersectionStart = maxOf(range1.start, range2.start)
        val intersectionEnd = minOf(range1.endInclusive, range2.endInclusive)

        val outersects = mutableListOf<LongRange>()

        // Check if there is a valid intersection
        if (intersectionStart <= intersectionEnd) {
            if (range1.start < intersectionStart) {
                outersects.add(range1.start until intersectionStart)
            }
            if (intersectionEnd < range1.endInclusive) {
                outersects.add((intersectionEnd + 1)..range1.endInclusive)
            }

            if (range2.start < intersectionStart) {
                outersects.add(range2.start until intersectionStart)
            }
            if (intersectionEnd < range2.endInclusive) {
                outersects.add((intersectionEnd + 1)..range2.endInclusive)
            }
        } else {
            // No intersection, add both ranges as outersects
            outersects.add(range1)
            outersects.add(range2)
        }

        return outersects
    }

    fun outersectWithList(current: LongRange, innerRanges: List<LongRange>): List<LongRange> {
        var outersects = mutableListOf(current)
        innerRanges.forEach { innerRange ->
            var newOutersects = mutableListOf<LongRange>()
            outersects.forEach { outersect ->
                var inner = intersectRanges(outersect, innerRange)
                if (inner == null) {
                    newOutersects.add(outersect)
                } else {
                    newOutersects.addAll(outersectRanges(outersect, innerRange))
                }
            }
            outersects = newOutersects
        }
        return outersects
    }


}
