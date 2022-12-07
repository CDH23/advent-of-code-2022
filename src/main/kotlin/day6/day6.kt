package day6

import common.readDay


private const val test = false

fun main() {
    val line = readDay(6, test)[0]

    fun common(size: Int) {
        val signals = line.windowed(size).map { it.toList() }
        val noSameIndex = signals.indexOfFirst { it.distinct().size == it.size }
        println(noSameIndex + size)
    }

    common(4)
    common(14)

}