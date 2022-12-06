package day4

import common.readDay


private const val test = false

fun main() {
    val texts = readDay(4, test)
        .map {
            val (first, second) = it.split(",")

            val (f1, s1) = first.split("-")
            val (f2, s2) = second.split("-")

            (f1.toInt()..s1.toInt()) to (f2.toInt()..s2.toInt())
        }



    println(
        texts
            .map {
                val (first, second) = it

                first.all(second::contains) || second.all(first::contains)
            }
            .count { it }
    )

    // part 2
    println(
        texts
            .map {
                val (first, second) = it

                first.any(second::contains) || second.any(first::contains)
            }
            .count { it }
    )
}