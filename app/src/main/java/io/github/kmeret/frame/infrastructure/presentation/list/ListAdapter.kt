package io.github.kmeret.frame.infrastructure.presentation.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.kmeret.frame.infrastructure.presentation.extensions.inflateView

class ListAdapter<T : ListItem>(
    private val templateResId: Int,
    private val onBindItem: ((item: T, rootView: View) -> Unit)
) : RecyclerView.Adapter<ListViewHolder>() {

    private var list: MutableList<T> = mutableListOf()

    init {
        this.setHasStableIds(true)
    }

    override fun getItemCount() = list.size

    override fun getItemId(position: Int) = list[position].id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(parent.inflateView(templateResId))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        onBindItem.invoke(list[position], holder.rootView)

    fun updateList(list: List<T>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}
