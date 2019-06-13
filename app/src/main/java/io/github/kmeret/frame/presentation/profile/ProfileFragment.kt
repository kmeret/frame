package io.github.kmeret.frame.presentation.profile

import android.view.View
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.domain.entity.Repo
import io.github.kmeret.frame.infrastructure.data.network.loadByUrl
import io.github.kmeret.frame.infrastructure.presentation.list.ListAdapter
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMFragment
import io.github.kmeret.frame.infrastructure.presentation.extensions.attachGridAdapter
import io.github.kmeret.frame.infrastructure.presentation.extensions.visible
import io.github.kmeret.frame.presentation.ExceptionHandler
import io.github.kmeret.frame.presentation.repos.ReposViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.template_profile_repo.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : VMFragment<ProfileViewModel>() {
    override val layoutResId = R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModel()
    private val reposViewModel: ReposViewModel by viewModel()

    private val reposAdapter =
        ListAdapter<Repo>(R.layout.template_profile_repo) { repo, repoView ->
            repoView.apply {
                repoProfileNameView.text = repo.name
                repoProfileDescriptionView.text = repo.description
                repoProfileLanguageView.text = repo.language
            }
        }

    override fun initView(rootView: View) {
        rootView.run {
            profileRefreshLayout.setOnRefreshListener {
                viewModel.requestProfile()
                reposViewModel.requestRepoList()
            }
            profileRepoListView.apply {
                attachGridAdapter(reposAdapter)
                isNestedScrollingEnabled = false
            }
        }
    }

    override fun initViewModel() {
        viewModel.networkProfile.observe {
            profileAvatarView.loadByUrl(it.avatarUrl)
            profileBioView.text = it.bio
            profileCompanyView.text = it.company
            profileLocationView.text = it.location
            profileLoginView.text = it.login
            profileNameView.text = it.name
        }
        viewModel.loading.observe {
            profileRefreshLayout.isRefreshing = it
        }
        viewModel.error.observe {
            ExceptionHandler.handle(it, requireContext())
        }
        viewModel.empty.observe { isEmpty ->
            profileEmptyView.visible(isEmpty)
            profileLayout.visible(!isEmpty)
        }
        reposViewModel.repoList.observe {
            reposAdapter.updateList(it)
        }
        reposViewModel.error.observe {
            ExceptionHandler.handle(it, requireContext())
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestProfile()
        reposViewModel.requestRepoList()
    }

}