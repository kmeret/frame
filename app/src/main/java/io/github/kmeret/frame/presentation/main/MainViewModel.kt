package io.github.kmeret.frame.presentation.main

import io.github.kmeret.frame.application.Screens
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import ru.terrakok.cicerone.Router

class MainViewModel(
    private val router: Router
) : BaseViewModel() {

    override fun onInit() = router.newRootScreen(Screens.ProfileScreen())

    override fun onBackPressed() = router.finishChain()

    fun onProfilePressed()  = router.newRootScreen(Screens.ProfileScreen())

    fun onReposPressed() = router.newRootScreen(Screens.ReposScreen())

    fun onStarsPressed() = router.newRootScreen(Screens.StarsScreen())

    fun onFollowersPressed() = router.newRootScreen(Screens.FollowersScreen())

    fun onFollowingPressed() = router.newRootScreen(Screens.FollowingScreen())
}