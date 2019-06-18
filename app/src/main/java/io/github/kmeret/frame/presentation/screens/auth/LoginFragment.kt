package io.github.kmeret.frame.presentation.screens.auth

import android.os.Bundle
import io.github.kmeret.frame.R
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMFragment
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : VMFragment<LoginViewModel>() {
    override val layoutResId = R.layout.fragment_login

    override val viewModel: LoginViewModel by viewModel()

    override fun initLayout(savedInstanceState: Bundle?) {

    }

    override fun subscribeLiveData() {

    }
}