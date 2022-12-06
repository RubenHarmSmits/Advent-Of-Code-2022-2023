package days.year2016

import days.Day

fun main(args: Array<String>) {
    println(Day10().solve())
}


class Day10 : Day(10, 2016) {

    var output = mutableMapOf<Int, Int>()
    var bots = mutableListOf<Bot>()


    fun solve(): Any {
        inputList
            .forEach {
                if (it.contains("goes")) {
                    val value = it.substringAfter(" ").substringBefore(" goes").toInt()
                    val name = it.substringAfter("bot ").toInt()
                    var botO = bots.find { it.name == name }
                    if (botO != null) {
                        botO.add(value)
                    } else {
                        botO = Bot(name)
                        botO.add(value)
                        bots += botO
                    }
                } else {
                    // bot 2 gives low to bot 1 and high to bot 0
                    val name = it.substringAfter(" ").substringBefore(" gives").toInt()
//                    val low = it.substringAfter("to bot ").substringBefore(" and high").toInt()
//                    var high = it.substringAfter("high to bot ").toInt()
                    var botO = bots.find { it.name == name }
                    if (botO == null) {
                        botO = Bot(name)
                        bots += botO
                    }
                    botO.line = it.split(" ")
                }
            }
        while (true) {
            var bot = bots.find { it.values.size == 2 }
            if(bot!=null){
                val low = bot.line[6].toInt()
                val high = bot.line[11].toInt()
                val lowToBot = bot.line[5] == "bot"
                val highToBot = bot.line[10] == "bot"
                val lowest = bot.values.minOrNull()?:0
                val highest = bot.values.maxOrNull()?:0

                if(lowToBot){
                    var bot2 = bots.find { it.name == low }
                    bot2!!.add(lowest!!)
                } else {
                    if(output[low]==null) output[low] = lowest
                    else output[low] = output[low]!! + lowest
                }
                if(highToBot){
                    var bot2 = bots.find { it.name == high }
                    bot2!!.add(highest!!)
                }
                else {
                    if(output[high]==null) output[high] = highest
                    else output[high] = output[high]!! + highest
                }
                bot.empty();
            }
            else {
                break;
            }
        }
        return (output[0]?:1) * (output[1]?:1) * (output[2]?:0)
    }


}

class Bot(_name: Int, _values: MutableList<Int> = mutableListOf<Int>()) {
    val name: Int
    val values: MutableList<Int>
    var line = listOf<String>()

    init {
        name = _name
        values = _values
    }

    fun add(value: Int) {
        values += value
    }

    fun empty(){
        values.clear();
    }
}

