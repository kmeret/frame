package io.github.kmeret.demo.storage.base

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import io.github.kmeret.base.android.list.Identifiable

abstract class Identifiable {
    @PrimaryKey
    @ColumnInfo(index = true)
    var id: Long = 0L
}