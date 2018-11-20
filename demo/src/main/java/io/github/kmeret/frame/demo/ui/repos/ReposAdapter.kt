package io.github.kmeret.frame.demo.ui.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.demo.databinding.TemplateRepoBinding
import io.github.kmeret.frame.demo.domain.entity.Repo

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    class RepoViewHolder(private val binding: TemplateRepoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repo) {
            binding.repo = repo
            binding.executePendingBindings()
        }

    }

    private var list: MutableList<Repo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding: TemplateRepoBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.template_repo, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun getList(): List<Repo> = list

    fun updateList(list: List<Repo>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

}