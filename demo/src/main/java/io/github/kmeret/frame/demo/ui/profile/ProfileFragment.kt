package io.github.kmeret.frame.demo.ui.profile

import android.view.View
import io.github.kmeret.frame.android.extensions.attachGridAdapter
import io.github.kmeret.frame.android.extensions.visible
import io.github.kmeret.frame.android.ui.RecyclerAdapter
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.demo.domain.entity.Repo
import io.github.kmeret.frame.demo.ui.ExceptionHandler
import io.github.kmeret.frame.demo.ui.repos.ReposViewModel
import io.github.kmeret.frame.lifecycle.ViewModelFragment
import io.github.kmeret.frame.network.loadByUrl
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.template_profile_repo.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : ViewModelFragment<ProfileViewModel>() {

    override val layoutResId = R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModel()
    private val reposViewModel: ReposViewModel by viewModel()
    private val reposAdapter = RecyclerAdapter<Repo>(R.layout.template_profile_repo) { repo, repoView ->
        repoView.apply {
            repoProfileNameView.text = repo.name
            repoProfileDescriptionView.text = repo.description
            repoProfileLanguageView.text = repo.language
        }
    }

    override fun initView(rootView: View) {
        rootView.apply {
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
        viewModel.profile.observe {
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