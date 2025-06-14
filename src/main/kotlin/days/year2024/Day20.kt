package days.year2024

import days.Day

fun main() {
    println(Day19().solve())
}

class Day19 : Day(19, 2024) {  // 569656553019831 too low

    val towels =
        "ugw, wbgg, uwu, ww, buuuu, bggu, rrgub, uuwr, rrgr, wwg, bwgw, rw, uwwg, ggrb, rwg, bbug, ggrw, bbb, wruwr, brg, wgrg, grwb, gbg, bggwwrb, rr, bwrw, wbwwb, grw, gbrgww, rrrug, ubuggu, bgb, wbbr, guuwgub, ugwbb, urbw, rbgwg, ugg, bbwu, bggrbuu, gurb, wggrgwb, ugubwg, ru, rwruub, bgw, buug, wgbw, gubwru, wu, uubw, gurw, bbrb, wru, rur, uw, gwbwgr, wwb, wrw, grgugurb, gwuu, rruwwug, www, gur, rgu, ruwgub, rwug, bb, grrw, gbbbwr, brwgg, wrbwgwb, buww, gbug, bgug, rrbgg, bwrrg, rbu, wwuuub, bwwgrr, rgw, grrgru, wuuggg, rwwb, bu, rrr, bgr, brrggrrw, buur, wr, uuuu, rgbrgur, rwwru, bgwuwg, wbbgr, wurubgrg, rbugbu, wwu, ubwwr, wbwbwwgw, wbr, gwbb, rrw, g, gwrg, ugb, ub, ggb, uwrur, gurr, guwwubr, urgg, uwr, bgggru, ugrbg, wbbggb, urur, rubgguu, guwb, rbbwrwbw, bgrb, wrg, wbggb, rbbbgw, uuwuu, urrwg, wg, ruu, brgrwgg, bgbw, wgu, gbw, ruuuugw, wuw, ubu, ggguwb, rwbubbr, rrg, bbrbr, wggbuu, rgg, burwurw, gubrgg, rrb, bbw, urb, urbwrbwb, bugu, rbwug, rgb, ubgbw, wurrrb, bwbbg, rbw, bbubrw, gug, bbg, rruw, uuuwu, bbbr, gbrwrrr, wwgb, wbrrbwg, uwwu, ugwbw, bbwrg, rrbwrb, bgbrggub, gbbbbb, bgbbrgb, rgrg, bubwu, bur, rub, guu, uuw, bru, rbbbbb, urugw, wruw, uwrwur, uug, wgrgwgu, gbgwgu, ggu, grwugu, bubr, urwwg, bbwgurg, urwgr, gwu, ubgwbur, brr, rwu, wub, ggw, bgrurbg, wrwggg, wbg, uwurww, gugug, rubuur, ggwb, wbrw, wubr, rgguu, gruw, bgwug, uwb, uuu, uwgr, wwgu, urw, rubwubu, gugrubw, bwbrg, rgub, gwbrwwg, uu, bgrwwg, gbrggwbr, ugrw, bwg, ugbub, uwbuu, wgbug, wgrbggbw, bgrbr, gwgr, rbub, gwb, gwwu, wwrurwu, uggrww, gub, rrbubgur, uwrr, rbbb, wbgrg, gbwgur, bruru, r, ugrbbr, guwggg, bwugg, wburru, rrrrwu, ugbbw, urwub, gwbu, ubr, uugbwg, ubww, rwuur, ubw, wggbg, bug, grgu, wwgg, wgbuw, ubburgbb, gwugw, ruur, bruu, wbgu, gbgr, rrbr, gggb, wur, guw, grrbrgw, brw, rrrrrwwu, ug, rbgb, guggg, bg, wgg, bugwgbrw, wgbbgru, gwwgbu, gbwurr, wrb, wguuwrg, wwr, uwwwbu, bgu, ugu, ugrwug, wbu, gr, wurgb, rwb, rg, ggbrrbrb, bub, uburg, urbbbb, brrwu, wguwbr, rgbbwuub, urug, buu, bwgb, gru, ggrrwg, gurbr, ruw, bwr, gbr, brb, buuu, brwg, bbrw, gw, wwggr, gg, uru, wbb, rbb, uugb, rgrbu, bwugrb, gb, bgg, wug, ruru, grr, wrr, wubbrww, brguww, uwg, rbr, ubb, bgruu, uwwwrb, grrguuu, wwbbb, wrwgw, rugbu, wubbg, urr, ruwgbbub, grrgr, rrwu, wbwg, gwuwrbr, gburw, brurrguw, ubg, rurbu, bgurugrb, gwbguwb, grrwgubw, burw, rgrr, bwgu, wwurb, wb, urg, rww, gwr, grg, rgr, bbuu, rbg, bubww, uub, ruwbgb, grgbg, ggrwg, uwuuu, bbbbwrb, wuu, ggbg, ubuwru, wugubg, bbr, ggg, bubwg, wgr, b, bgrgw, ruwb, ubuuu, bwu, uggb, ggr, ur, gbu, urwwugb, wrugu, uuurb, rurwr, uguggb, buwg, ugwuw, rgwb, bgrbu, gbww, gbb, uuwu, gwgu, uuguu, bwrwggb, bwb, wrbw, rgrb, ubbwwur, grb, gwrb, bbu, uww, buw, rbubrw, rb, bbbrw, gwg, guugg, gwggbrgw, uuuwb, rug, uguguw, gbbu, u, bww, br, gurwggb, wbrwgr, rurgw, rggb, wgw, wwwbbr, ugbgrr, rwr, brgug, wbwgbr, ugr, rrwg, bbur, ubgwwrr, rurwbbu, wwrg, wugrrb, rguwbrr, guuu, rrurubr".split(
            ", "
        )
    val patterns = inputList

    data class Cache(var designLeft: String, val possibilities: Long)

    fun solve(): Any {
        return patterns.sumOf {
            designIsPossible(it)
        }
    }

    private fun designIsPossible(design: String, cache: MutableList<Cache> = mutableListOf()): Long {
        if (design == "") return 1L
        cache.firstOrNull { it.designLeft == design }?.let {
            return it.possibilities
        }
        val left = towels
            .filter { design.startsWith(it) }
            .sumOf { towel: String ->
                designIsPossible(design.removePrefix(towel), cache)
            }
        cache.add(Cache(design, left))
        return left
    }
}