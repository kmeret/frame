package io.github.kmeret.frame.demo.ui.repos

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapter")
fun setAdapter(recyclerView: RecyclerView, recyclerAdapter: RecyclerBindingAdapter<*, *>) {
    recyclerView.apply {
        layoutManager = LinearLayoutManager(recyclerView.context)
        adapter = recyclerAdapter
    }
}

@BindingAdapter("list")
fun updateList(recyclerView: RecyclerView, list: List<Any>?) {
    if (list == null) return

    val adapter = recyclerView.adapter
    if (adapter == null || adapter !is RecyclerBindingAdapter<*, *> ) return

    @Suppress("UNCHECKED_CAST") //TODO how to check equals adapter item type and list type
    (adapter as RecyclerBindingAdapter<*, Any>).updateList(list)
    adapter.updateList(list)
}
