package io.github.kmeret.base.android.extensions

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.attachAdapter(
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>,
        reverse: Boolean = false
) {
    setHasFixedSize(false)
    layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, reverse)
    this.adapter = adapter
}

fun RecyclerView.attachGridAdapter(
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>,
        columns: Int = 2
) {
    setHasFixedSize(false)
    layoutManager = GridLayoutManager(context, columns)
    this.adapter = adapter
}