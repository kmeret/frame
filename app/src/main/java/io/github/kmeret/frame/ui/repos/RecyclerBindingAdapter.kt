package io.github.kmeret.frame.ui.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

class RecyclerBindingAdapter <Binding : ViewDataBinding, Item : Any>(
        private val templateResId: Int
) : RecyclerView.Adapter<RecyclerBindingAdapter.ItemViewHolder<Binding, Item>>() {

    class ItemViewHolder <Binding : ViewDataBinding, Item> (private val binding: Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }

    private var list: MutableList<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<Binding, Item> {
        val binding: Binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), templateResId, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder<Binding, Item>, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun getList(): List<Item> = list

    fun updateList(list: List<Item>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

}