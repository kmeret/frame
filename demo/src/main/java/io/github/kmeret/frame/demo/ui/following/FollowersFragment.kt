package io.github.kmeret.frame.demo.ui.following

import android.view.View
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.lifecycle.ViewModelFragment
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FollowersFragment : ViewModelFragment<FollowViewModel>() {

    override val layoutResId = R.layout.fragment_followers

    override val viewModel: FollowViewModel by sharedViewModel()

    override fun initView(rootView: View) {

    }

    override fun initViewModel() {

    }
}