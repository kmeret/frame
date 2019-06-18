package io.github.kmeret.frame.presentation.main

import android.os.Bundle
import io.github.kmeret.frame.R
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMCommand
import io.github.kmeret.frame.infrastructure.application.navigation.NavActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : NavActivity<MainViewModel>() {
    override val layoutResId = R.layout.activity_main

    override val coordinatorLayoutId = R.id.main_coordinator

    override val fragmentsContainerId = R.id.main_container

    override val viewModel: MainViewModel by viewModel()

    override fun initLayout(savedInstanceState: Bundle?) {
        main_bottom_nav_bar.setOnNavigationItemSelectedListener { viewModel.onBottomNavBarPressed(it.itemId); true }
    }

    override fun subscribeLiveData() {

    }

    override fun onCommandReceived(command: VMCommand) {
        viewModel.onCommandReceived(command)
    }
}