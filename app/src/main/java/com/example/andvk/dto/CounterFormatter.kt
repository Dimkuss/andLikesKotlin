package com.example.andvk.dto

object CounterFormatter {
    fun formatCounter(counter: Long): String =
    when (counter) {
        in 1000..1099 -> "${counter / 1000}K"
        in 1100..9999 -> "${counter.toDouble() / 1000}K"
        in 10000..999_999 -> "${counter.toDouble() / 1000}K"
        in 1_000_000..1_099_999 -> "${counter / 1000000}M"
        in 1_100_000..999_999_999 -> "${counter.toDouble() / 1000000}M"
        else -> counter.toString()
    }
}