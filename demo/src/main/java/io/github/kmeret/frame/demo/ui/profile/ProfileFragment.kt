package io.github.kmeret.frame.demo.ui.profile

import android.view.View
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.demo.ui.repos.ReposViewModel
import io.github.kmeret.frame.lifecycle.ViewModelFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : ViewModelFragment<ProfileViewModel>() {

    override val layoutResId = R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModel()
    private val reposViewModel: ReposViewModel by viewModel()

    override fun initView(rootView: View) {

    }

    override fun initViewModel() {

    }

    override fun onResume() {
        super.onResume()
        reposViewModel.requestRepoList()
    }

}