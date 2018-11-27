package io.github.kmeret.frame.demo.storage.base

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

abstract class Identifiable {
    @PrimaryKey
    @ColumnInfo(index = true)
    open var id: Long = 0L
}