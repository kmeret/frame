package io.github.kmeret.frame.infrastructure.domain

fun Int.padStart(length: Int, padChar: Char): String {
    return this.toString().padStart(length, padChar)
}