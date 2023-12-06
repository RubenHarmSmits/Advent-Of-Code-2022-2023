package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    val singleRange = 0..100
    val rangeList = listOf(30..60, 70..90)

    val outersects = Day5().outersectWithList(singleRange, rangeList)

    outersects.forEach { println(it) }
//    println(Day5().solve())
}
class Day5: Day(5, 2023) {
//    var input = "222541566 218404460 670428364 432472902 2728902838 12147727 3962570697 52031641 2849288350 113747257 3648852659 73423293 4036058422 190602154 1931540843 584314999 3344622241 180428346 1301166628 310966761";
    var input = "79 14 55 13";
    val seeds = input.split(" ").map{it.toLong()}
    var seedsrange = mutableListOf<LongRange>();


    fun solve(): Any {
        seeds.chunked(2).forEach { seedsrange.add(it[0] until (it[0]+it[1])) }
        var overview = inputList
                .splitBy { it == "" }
                .map{it.subList(1, it.size)
                .map{extraxtAllLongsFromString(it)}}

        overview.forEach {
            println(seedsrange)
            seedsrange = findCorresponding(seedsrange, it)
        }
        return seedsrange.flatten().min()
    }

    private fun findCorresponding(cur: MutableList<LongRange>, mapper: List<List<Long>>): MutableList<LongRange> {
        val a = cur.map {
            findCorresponding(it, mapper)
        }.flatten()
        return a.toMutableList()
    }
    private fun findCorresponding(cur: LongRange, mapper: List<List<Long>>): MutableList<LongRange> {
        var destinationRange = mutableListOf<LongRange>()

        mapper.forEach {
            val (destination, source, range) = it
            var interRange = intersectRanges(cur, source..source+range)
            if(interRange!=null){
                var outputRange = destination..(destination +(interRange.last - interRange.first))
                destinationRange.add(outputRange)
            }
        }
        return destinationRange
    }


//    seed-to-soil map:

//    79 - 92
//    55 - 67
//
//    52 50 48
//    50 98 2
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


    fun outersectRanges(range1: IntRange, range2: IntRange): List<IntRange> {
        val intersectionStart = maxOf(range1.start, range2.start)
        val intersectionEnd = minOf(range1.endInclusive, range2.endInclusive)

        val outersects = mutableListOf<IntRange>()

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

    fun outersectWithList(singleRange: IntRange, rangeList: List<IntRange>): List<IntRange> {
        val outersects = mutableListOf<IntRange>()

        for (range in rangeList) {
            val intersectionStart = maxOf(singleRange.start, range.start)
            val intersectionEnd = minOf(singleRange.endInclusive, range.endInclusive)

            // Check if there is a valid intersection
            if (intersectionStart <= intersectionEnd) {
                if (singleRange.start < range.start) {
                    outersects.add(singleRange.start until range.start)
                }
                if (range.endInclusive < singleRange.endInclusive) {
                    outersects.add((range.endInclusive + 1)..singleRange.endInclusive)
                }
            } else {
                // No intersection, add the whole singleRange as an outersect
                outersects.add(singleRange)
            }
        }

        // If there's no intersection with any range, add the whole singleRange
        if (outersects.isEmpty()) {
            outersects.add(singleRange)
        }

        return outersects
    }

}
