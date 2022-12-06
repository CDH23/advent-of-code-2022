package day3

import common.readDay

private val a2z = ('a'..'z') + ('A'..'Z')
private const val test = false

fun main() {
    val texts = readDay(3, test)
        .map { line ->
            val first = line.take(line.length / 2)
            val second = line.takeLast(line.length / 2)

            first.toCharArray().intersect(second.toSet()).first()
        }





    println(texts.sumOf { a2z.indexOf(it) + 1 })

    // part 2
    val chars = readDay(3, test)
        .map { it.toSet() }
        .chunked(3)
        .map {
            val (first, second, third) = it
            first.intersect(second).intersect(third).first()
        }

    println(chars.sumOf { a2z.indexOf(it) + 1 })
}