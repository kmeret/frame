package io.github.kmeret.frame.presentation.profile

import android.os.Bundle
import io.github.kmeret.frame.R
import io.github.kmeret.frame.domain.model.Repo
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMFragment
import io.github.kmeret.frame.infrastructure.data.network.loadByUrl
import io.github.kmeret.frame.infrastructure.presentation.extensions.attachGridAdapter
import io.github.kmeret.frame.infrastructure.presentation.extensions.isGone
import io.github.kmeret.frame.infrastructure.presentation.extensions.isVisible
import io.github.kmeret.frame.infrastructure.presentation.list.ListAdapter
import io.github.kmeret.frame.presentation.repos.ReposViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.template_repo_profile.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : VMFragment<ProfileViewModel>() {
    override val layoutResId = R.layout.fragment_profile

    override val viewModel: ProfileViewModel by viewModel()

    private val reposViewModel: ReposViewModel by sharedViewModel()

    private val reposAdapter = ListAdapter<Repo>(R.layout.template_repo_profile) { repo, repoView ->
        repoView.run {
            repo_profile_name.text = repo.name
            repo_profile_description.text = repo.description
            repo_profile_language.text = repo.language
        }
    }

    override fun initLayout(savedInstanceState: Bundle?) {
        profile_refresh.setOnRefreshListener {
            viewModel.requestProfile()
            reposViewModel.requestRepoList()
        }
        profile_repo_list.run {
            attachGridAdapter(reposAdapter)
            isNestedScrollingEnabled = false
        }
    }

    override fun subscribeLiveData() {
        viewModel.isLoading.subscribe { profile_refresh.isRefreshing = it }
        viewModel.networkProfile.subscribe {
            profile_avatar.loadByUrl(it.avatarUrl)
            profile_bio.text = it.bio
            profile_company.text = it.company
            profile_location.text = it.location
            profile_login.text = it.login
            profile_name.text = it.name
        }
        reposViewModel.repoList.subscribe {
            profile_empty.isVisible = it.isEmpty()
            profile_layout.isGone = it.isEmpty()
            reposAdapter.updateList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestProfile()
        reposViewModel.requestRepoList()
    }
}