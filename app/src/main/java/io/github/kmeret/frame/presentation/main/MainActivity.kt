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
        main_bottom_nav_bar.setOnNavigationItemSelectedListener { onBottomNavItemPressed(it.itemId); true }
    }

    override fun subscribeLiveData() {

    }

    override fun onCommandReceived(command: VMCommand) {

    }

    private fun onBottomNavItemPressed(itemId: Int) {
        return when (itemId) {
            R.id.nav_profile -> viewModel.onProfilePressed()
            R.id.nav_repos -> viewModel.onReposPressed()
            R.id.nav_stars -> viewModel.onStarsPressed()
            R.id.nav_followers -> viewModel.onFollowersPressed()
            R.id.nav_following -> viewModel.onFollowingPressed()
            else -> return
        }
    }
}