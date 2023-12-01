package day1

import java.io.File

fun problemOne(file: String): Int  = File(file).useLines { lines ->
        lines.map { digitsOne(it) }.sum()
    }


fun digitsOne(line: String): Int {
    val first: Char = line.find { it.isDigit() }!!
    val last: Char = line.findLast { it.isDigit() }!!
    return 10 * first.digitToInt() + last.digitToInt()
}

fun problemTwo(file: String): Int {
    val sum = File(file).useLines { lines ->
        lines.map { digitsTwo(it) }.sum()
    }
    return sum
}

val wordDigits = mapOf(
    "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5,
    "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9
)

fun digitsTwo(line: String): Int =
    10 * firstDigit(line, 0)!! + secondDigit(line, line.length - 1)!!

fun firstDigit(line: String, i: Int): Int? =
    if (i == line.length) {
        null
    } else {
        getDigit(line, i) ?: firstDigit(line, i + 1)
    }

fun secondDigit(line: String, i: Int) : Int? =
    if (i == -1) {
        null
    } else {
        getDigit(line, i) ?: secondDigit(line, i - 1)
    }

fun getDigit(s: String, i: Int): Int? {
    if (s[i].isDigit()) {
        return s[i].digitToInt()
    }
    for (word in wordDigits.keys) {
        if (s.startsWith(word, i)) {
            return wordDigits[word]
        }
    }
    return null
}

fun main(args: Array<String>) {
    println(problemOne("src/main/kotlin/day1/input1a.txt"))
    println(problemTwo("src/main/kotlin/day1/input1b.txt"))
}