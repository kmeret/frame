package io.github.kmeret.frame.presentation.repos

import android.os.Bundle
import io.github.kmeret.frame.R
import io.github.kmeret.frame.domain.model.Repo
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMFragment
import io.github.kmeret.frame.infrastructure.presentation.extensions.initList
import io.github.kmeret.frame.infrastructure.presentation.extensions.isVisible
import io.github.kmeret.frame.infrastructure.presentation.list.ListAdapter
import kotlinx.android.synthetic.main.fragment_repos.*
import kotlinx.android.synthetic.main.template_repo.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ReposFragment : VMFragment<ReposViewModel>() {
    override val layoutResId = R.layout.fragment_repos

    override val viewModel by sharedViewModel<ReposViewModel>()

    private val reposAdapter = ListAdapter<Repo>(R.layout.template_repo) { repo, repoView ->
        repoView.run {
            repo_name.text = repo.name
            repo_description.text = repo.description
            repo_language.text = repo.language
        }
    }

    override fun initLayout(savedInstanceState: Bundle?) {
        repos_refresh.setOnRefreshListener { viewModel.requestRepoList() }
        repos_list.initList(reposAdapter)
    }

    override fun subscribeLiveData() {
        viewModel.isLoading.subscribe { repos_refresh.isRefreshing = it }
        viewModel.repoList.subscribe {
            repos_list.isVisible = it.isNotEmpty()
            repos_empty.isVisible = it.isEmpty()
            reposAdapter.updateList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestRepoList()
    }
}