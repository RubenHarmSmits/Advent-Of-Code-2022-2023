package days

import util.InputReader


abstract class Day(dayNumber: Int, year:Int=2022) {

    fun List<String>.ints(radix: Int = 10) = this.map { it.toInt(radix) }

    fun <E> List<E>.splitBy(predicate: (E) -> Boolean): List<List<E>> =
        this.fold(mutableListOf(mutableListOf<E>())) { acc, element ->
            if (predicate.invoke(element)) {
                acc += mutableListOf<E>()
            } else {
                acc.last() += element
            }
            acc
        }

    open fun <T> transpose(table: List<List<T>>): List<List<T>> {
        val ret: MutableList<List<T>> = ArrayList()
        val N = table[0].size
        for (i in 0 until N) {
            val col: MutableList<T> = ArrayList()
            for (row in table) {
                col.add(row[i])
            }
            ret.add(col)
        }
        return ret
    }

    // lazy delegate ensures the property gets computed only on first access
    protected val inputList: List<String> by lazy { InputReader.getInputAsList(dayNumber, year) }
    protected val inputListInt: List<Int> by lazy { InputReader.getInputAsListInt(dayNumber, year) }

    protected val inputString: String by lazy { InputReader.getInputAsString(dayNumber, year) }

}
