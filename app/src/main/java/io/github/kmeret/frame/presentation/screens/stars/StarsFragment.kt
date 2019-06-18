package io.github.kmeret.frame.presentation.screens.stars

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import io.github.kmeret.frame.R
import io.github.kmeret.frame.domain.model.Repo
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMFragment
import io.github.kmeret.frame.infrastructure.presentation.extensions.initList
import io.github.kmeret.frame.infrastructure.presentation.extensions.isVisible
import io.github.kmeret.frame.infrastructure.presentation.list.ListAdapter
import kotlinx.android.synthetic.main.fragment_stars.*
import kotlinx.android.synthetic.main.template_repo_starred.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class StarsFragment : VMFragment<StarsViewModel>() {
    override val layoutResId = R.layout.fragment_stars

    override val viewModel: StarsViewModel by viewModel()

    private val repoListAdapter = ListAdapter<Repo>(R.layout.template_repo_starred) { repo, repoRootView ->
        repoRootView.also {
            it.repo_starred_title.text = repo.fullName
            it.repo_description.text = repo.description
            it.repo_description.isVisible = repo.description.isNotEmpty()
            it.repo_language.text = repo.language
            it.repo_starred_stars.text = repo.starsCount.toString()
            it.repo_starred_forks.text = repo.forksCount.toString()
            it.repo_updated_at.text = repo.updatedAt
        }
    }

    override fun initLayout(savedInstanceState: Bundle?) {
        stars_refresh.setOnRefreshListener { viewModel.requestStarredList() }
        stars_list.run {
            initList(repoListAdapter)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }

    override fun subscribeLiveData() {
        viewModel.isLoading.subscribe { stars_refresh.isRefreshing = it }
        viewModel.starredList.subscribe {
            stars_list.isVisible = it.isNotEmpty()
            stars_empty.isVisible = it.isEmpty()
            repoListAdapter.updateList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestStarredList()
    }

}