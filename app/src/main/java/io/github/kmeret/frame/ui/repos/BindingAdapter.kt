package io.github.kmeret.frame.ui.repos

import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("adapter")
fun setRecyclerAdapter(recyclerView: RecyclerView, recyclerAdapter: RecyclerBindingAdapter<*, *>) {
    recyclerView.apply {
        layoutManager = LinearLayoutManager(recyclerView.context)
        adapter = recyclerAdapter
        addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL ))
    }
}

@BindingAdapter("list")
fun updateRecyclerList(recyclerView: RecyclerView, list: List<Any>?) {
    if (list == null) return

    val adapter = recyclerView.adapter
    if (adapter == null || adapter !is RecyclerBindingAdapter<*, *>) return

    @Suppress("UNCHECKED_CAST")
    (adapter as RecyclerBindingAdapter<*, Any>).updateList(list)
    adapter.updateList(list)
}

@BindingAdapter("chipList")
fun updateChipList(chipGroup: ChipGroup, list: List<String>) {
    list.forEach {
        chipGroup.addView(Chip(chipGroup.context).apply {
            text = it
        })
    }
}
