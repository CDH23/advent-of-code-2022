package day1

import common.readDay
import java.util.*

fun main() {
    val texts = readDay(1)
    val stack = Stack<Int>()

    stack.push(0)

    texts.forEach {
        if (it.trim().isEmpty()) {
            stack.push(0)
        } else {
            stack.push(stack.pop() + it.toInt())
        }
    }

    // part one
    println(stack.max())

    // part two
    println(stack.toList().sortedDescending().take(3).sum())
}