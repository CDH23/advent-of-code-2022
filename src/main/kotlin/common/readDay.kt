package common

import java.io.File


fun readDay(day: Int, test: Boolean = false): List<String> {
    return if (test) {
        File("data/day${day}/test.txt").readLines()
    } else {
        File("data/day${day}/input.txt").readLines()
    }
}