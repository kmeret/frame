package io.github.kmeret.frame.presentation.screens.auth

import android.os.Bundle
import android.text.InputType
import com.google.android.material.textfield.TextInputLayout
import io.github.kmeret.frame.R
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMFragment
import io.github.kmeret.frame.infrastructure.presentation.extensions.isGone
import io.github.kmeret.frame.infrastructure.presentation.extensions.isVisible
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : VMFragment<LoginViewModel>() {
    override val layoutResId = R.layout.fragment_login

    override val viewModel: LoginViewModel by viewModel()

    override fun initLayout(savedInstanceState: Bundle?) {
        login_username.layout.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
        login_password.run {
            layout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
            input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }

    override fun subscribeLiveData() {
        viewModel.isLoading.subscribe {
            login_content.isGone = it
            login_progress.isVisible = it
        }
    }
}