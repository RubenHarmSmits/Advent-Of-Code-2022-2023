package days

fun main(args: Array<String>) {
   println(Day6().solve())
}
class Day6:Day(6) {

    private val answer = ""

    fun solve(): Any {

        for (i in 0..inputList[0].length-1){
            var result = inputList
                .map{it[i]}
                .groupingBy { it }
                .eachCount()
                .minByOrNull { it.value }?.key
            print(result);
        }
        return answer
    }

}

