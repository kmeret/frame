package io.github.kmeret.frame.application

import io.github.kmeret.frame.presentation.screens.auth.LoginFragment
import io.github.kmeret.frame.presentation.screens.followers.FollowersFragment
import io.github.kmeret.frame.presentation.screens.following.FollowingFragment
import io.github.kmeret.frame.presentation.screens.profile.ProfileFragment
import io.github.kmeret.frame.presentation.screens.repos.ReposFragment
import io.github.kmeret.frame.presentation.screens.stars.StarsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    class LoginScreen : SupportAppScreen() {
        override fun getFragment() = LoginFragment()
    }
    class ProfileScreen : SupportAppScreen() {
        override fun getFragment() = ProfileFragment()
    }
    class ReposScreen : SupportAppScreen() {
        override fun getFragment() = ReposFragment()
    }
    class StarsScreen : SupportAppScreen() {
        override fun getFragment() = StarsFragment()
    }
    class FollowersScreen : SupportAppScreen() {
        override fun getFragment() = FollowersFragment()
    }
    class FollowingScreen : SupportAppScreen() {
        override fun getFragment() = FollowingFragment()
    }
}