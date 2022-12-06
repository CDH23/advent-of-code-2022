package day5

import common.readDay
import java.util.*

private const val test = false

private data class Operation(val from: Int, val to: Int, val amount: Int)

fun main() {
    val texts = readDay(5, test)

    val beforeEmptyIndex = texts.indexOfFirst { it.isEmpty() }

    val stackLayout = texts.take(beforeEmptyIndex)
    val instructions = texts.drop(beforeEmptyIndex + 1)


    val stacks = Array(stackLayout.last().last().digitToInt()) { Stack<Char>() }


    stackLayout
        .dropLast(1)
        .reversed()
        .forEach { line ->
            val fmt = line.chunked(4)

            fmt.forEachIndexed { index, s ->
                if (s.isBlank()) return@forEachIndexed

                stacks[index].push(s[1])
            }
        }


    val operations = instructions.map {
        val s = it.split(" ")
        Operation(s[3].toInt() - 1, s[5].toInt() - 1, s[1].toInt())
    }

    fun part1() {
        operations.forEach { (from, to, amount) ->
            val f = stacks[from]
            val t = stacks[to]
            repeat(amount) {
                t.push(f.pop())
            }
        }

        println(String(stacks.map { it.peek() }.toCharArray()))
    }

    fun part2() {
        operations.forEach { (from, to, amount) ->
            val f = stacks[from]
            val t = stacks[to]

            Array(amount) { f.pop() }
                .reversed()
                .forEach { t.push(it) }

        }

        println(String(stacks.map { it.peek() }.toCharArray()))
    }

    part2()
}