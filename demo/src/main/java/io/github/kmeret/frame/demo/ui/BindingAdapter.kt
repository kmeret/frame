package io.github.kmeret.frame.demo.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.kmeret.frame.demo.ui.repos.RecyclerBindingAdapter

@BindingAdapter("adapter")
fun setAdapter(recyclerView: RecyclerView, recyclerAdapter: RecyclerBindingAdapter<*, *>) {
    recyclerView.apply {
        layoutManager = LinearLayoutManager(recyclerView.context)
        adapter = recyclerAdapter
    }
}

//
//https://kotlinlang.org/docs/reference/typecasts.html
//@BindingAdapter("adapter")
//fun <Item> updateList(recyclerView: RecyclerView, list: List<Item>) {
//    val adapter = recyclerView.adapter
//    if (adapter == null || adapter !is RecyclerBindingAdapter<*, Item>) return
//
//    adapter.updateList(list)
//}

