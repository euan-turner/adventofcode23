package day2

import java.io.File
import kotlin.math.max

val oneLimits = mapOf("red" to 12, "green" to 13, "blue" to 14)

fun validOne(sets: List<List<String>>): Boolean =
    sets.all { it[0].toInt() <= oneLimits[it[1]]!! }

fun idsOne(line: String): Int {
    val (start, info) = line.split(":")
    val id = start.split(" ")[1].toInt()
    val sets = info.split(";")
        .flatMap { it.split(",").map { c -> c.split(" ").subList(1, 3) } }
    return if (validOne(sets)) id else 0
}

fun powerTwo(sets: List<List<String>>): Int {
    val colorValues = mutableMapOf<String, Int>()
    for (set in sets) {
        val (color, value) = set[1] to set[0].toInt()
        colorValues[color] = max(colorValues[color] ?: 0, value)
    }
    return colorValues.values.reduce { acc, value -> acc * value }
}

fun powersTwo(line: String): Int {
    val sets =  line.split(":")[1].split(";")
        .flatMap { it.split(",").map { c -> c.split(" ").subList(1, 3) } }
    return powerTwo(sets)
}

fun problemOne(file: String): Int = File(file).useLines { lines ->
    lines.sumOf { idsOne(it) }
}

fun problemTwo(file: String): Int = File(file).useLines { lines ->
    lines.sumOf { powersTwo(it) }
}

fun main() {
    println(problemOne("src/main/kotlin/day2/input2a.txt"))
    println(problemTwo("src/main/kotlin/day2/input2a.txt"))
}


