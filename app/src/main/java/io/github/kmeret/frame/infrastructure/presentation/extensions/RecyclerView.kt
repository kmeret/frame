package io.github.kmeret.frame.infrastructure.presentation.extensions

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.initList(recyclerAdapter: RecyclerView.Adapter<*>, scrollable: Boolean = false) {
    layoutManager = LinearLayoutManager(context)
    adapter = recyclerAdapter
    isNestedScrollingEnabled = scrollable
}

fun RecyclerView.initGrid(recyclerAdapter: RecyclerView.Adapter<*>, rowCount: Int, scrollable: Boolean = false) {
    layoutManager = GridLayoutManager(context, rowCount)
    adapter = recyclerAdapter
    isNestedScrollingEnabled = scrollable
}