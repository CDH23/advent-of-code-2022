package day7

import common.readDay
import day6.Directory
import day6.File
import day6.FileSystem
import java.util.*


private const val limit = 100000

private const val test = false

fun main() {
    val lines = readDay(7, test)
    val rootDirectory = Directory()
    val terminalState = Stack<FileSystem>()

    fun cdCommand(argument: String) {
        when (argument) {
            ".." -> terminalState.pop()
            "/" -> with(terminalState) {
                clear()
                push(rootDirectory)
            }

            else -> terminalState.push((terminalState.peek() as Directory).directory(argument))
        }
    }

    var lsInput = false


    fun readLine(line: String) {
        when {
            line.startsWith("$ cd ") -> {
                cdCommand(line.replaceFirst("\$ cd ", ""))
                lsInput = false
            }

            line.trim() == "\$ ls" -> lsInput = true

            lsInput -> {
                val (size, name) = line.split(" ", limit = 2)
                val currentDir = terminalState.peek() as Directory

                if (size == "dir") currentDir.directory(name)
                else currentDir.file(name, size.toLong())
            }

            else -> error(line)
        }
    }

    lines.forEach(::readLine)

    val allDirs = mutableListOf<Directory>()

    fun part1() {

        fun Directory.scan(): Long {
            size += content.values.filterIsInstance<File>().sumOf { it.size }
            content.values.filterIsInstance<Directory>().apply {
                allDirs.addAll(this)
                forEach {
                    this@scan.size += it.scan()
                }
            }

            return size
        }

        rootDirectory.scan()
        println(allDirs.toList().filter { it.size <= limit }.sumOf { it.size })

    }

    part1()


    fun part2() {
        val totalSpace = 70000000L
        val requiredSpace = 30000000L

        val usedSpace =
            rootDirectory.content.values.sumOf { if (it is Directory) it.size else if (it is File) it.size else 0 }

        val emptySpace = totalSpace - usedSpace
        println(allDirs.sortedBy { it.size }.first { emptySpace + it.size >= requiredSpace }.size)
    }

    part2()
}