package day2

import common.readDay

private const val part2 = true

// 0, 1, 2
// rock, paper, scissors
// score = index + 1
// opponent array
private val winMap = intArrayOf(1, 2, 0)
private val loseMap = intArrayOf(2, 0, 1)

fun main() {
    val texts = readDay(2)

    var score = 0
    texts.forEach {
        val (_opponent, _me) = it.split(" ")

        val opponent = when (_opponent) {
            "A" -> 0
            "B" -> 1
            "C" -> 2
            else -> error("Unknown")
        }

        val me = figureOutMySelf(opponent, _me)

        if (opponent == me) {
            score += 3 + me + 1
            return@forEach
        }

        score += (me + 1) + if (winMap[opponent] == me) 6 else 0

    }

    println(score)
}

private fun figureOutMySelf(opponent: Int, _me: String): Int {
    return if (part2) {
        when (_me) {
            "X" -> loseMap[opponent]
            "Y" -> opponent
            "Z" -> winMap[opponent]
            else -> error("Unknown")
        }
    } else {
        when (_me) {
            "X" -> 0
            "Y" -> 1
            "Z" -> 2
            else -> error("Unknown")
        }
    }
}