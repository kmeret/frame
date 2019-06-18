package io.github.kmeret.frame.presentation.main

import io.github.kmeret.frame.R
import io.github.kmeret.frame.application.Screens
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMCommand
import ru.terrakok.cicerone.Router

class MainViewModel(
    private val router: Router
) : BaseViewModel() {

    override fun onInit() = router.newRootScreen(Screens.ProfileScreen())

    override fun onBackPressed() = router.finishChain()

    fun onCommandReceived(command: VMCommand) {

    }

    fun onBottomNavBarPressed(itemId: Int) {
        val rootScreen = when (itemId) {
            R.id.nav_profile -> Screens.ProfileScreen()
            R.id.nav_repos -> Screens.ReposScreen()
            R.id.nav_stars -> Screens.StarsScreen()
            R.id.nav_followers -> Screens.FollowersScreen()
            R.id.nav_following -> Screens.FollowingScreen()
            else -> return
        }
        router.newRootScreen(rootScreen)
    }
}