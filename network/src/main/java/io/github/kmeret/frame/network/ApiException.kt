package io.github.kmeret.frame.network

data class ApiException(val error: String = "") : Exception(error)