package io.github.kmeret.frame.infrastructure.domain

fun <T> T?.requiredNotNull(): T {
    return this ?: throw ConvertException()
}

fun Int.padStart(length: Int, padChar: Char): String {
    return this.toString().padStart(length, padChar)
}