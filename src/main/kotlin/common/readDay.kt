package common


fun readDay(day: Int): List<String> {
    return java.io.File("data/day${day}/input.txt").readLines()
}