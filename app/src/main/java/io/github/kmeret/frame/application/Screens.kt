package io.github.kmeret.frame.application

import io.github.kmeret.frame.presentation.followers.FollowersFragment
import io.github.kmeret.frame.presentation.following.FollowingFragment
import io.github.kmeret.frame.presentation.profile.ProfileFragment
import io.github.kmeret.frame.presentation.repos.ReposFragment
import io.github.kmeret.frame.presentation.stars.StarsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
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