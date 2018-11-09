package io.github.kmeret.frame.android.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.kmeret.frame.android.extensions.inflate

class RecyclerAdapter<T : Identifiable>(
        private val templateResId: Int,
        private val onBindItem: ((item: T, rootView: View) -> Unit)
) : RecyclerView.Adapter<ItemViewHolder>() {

    private var list: List<T> = emptyList()

    init {
        this.setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ItemViewHolder(inflate(parent))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
            onBindItem.invoke(list[position], holder.rootView)

    override fun getItemCount() = list.size

    override fun getItemId(position: Int) = list[position].id

    fun getList() = list

    fun updateList(list: List<T>) {
        this.list = list
        notifyDataSetChanged()
    }

    private fun inflate(parent: ViewGroup) = parent.inflate(templateResId)
//            LayoutInflater.from(parent.context).inflate(templateResId, parent, false)

}