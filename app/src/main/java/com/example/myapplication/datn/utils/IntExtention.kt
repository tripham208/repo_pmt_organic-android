package com.example.myapplication.datn.utils

fun Int.toStringFormat(): String {
    if (this < 1000) return this.toString()
    val list = this.toString().toMutableList()
    var index = list.size
    while (true) {
        if (index < 3) {
            break
        }
        index -= 3
        list.add(index, ',')
    }
    return list.joinToString(separator = "")
}