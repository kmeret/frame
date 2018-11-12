package io.github.kmeret.frame.android.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.kmeret.frame.android.extensions.inflate

class RecyclerAdapter<T : RecyclerAdapter.Identifiable>(
        private val templateResId: Int,
        private val onBindItem: ((item: T, rootView: View) -> Unit)
) : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    class ItemViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView)

    interface Identifiable {
        val id: Long
    }

    private var list: MutableList<T> = ArrayList()

    init {
        this.setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ItemViewHolder(inflate(parent))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
            onBindItem.invoke(list[position], holder.rootView)

    override fun getItemCount() = list.size

    override fun getItemId(position: Int) = list[position].id

    fun getList(): List<T> = list

    fun updateList(list: List<T>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    private fun inflate(parent: ViewGroup) = parent.inflate(templateResId)

}
