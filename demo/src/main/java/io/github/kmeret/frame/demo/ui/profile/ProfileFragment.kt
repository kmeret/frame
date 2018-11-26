package io.github.kmeret.frame.demo.ui.profile

import android.view.View
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.lifecycle.ViewModelFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : ViewModelFragment<ProfileViewModel>() {

    override val layoutResId = R.layout.fragment_profile

    override val viewModel: ProfileViewModel by viewModel()

    override fun initView(rootView: View) {

    }

    override fun initViewModel() {

    }

}