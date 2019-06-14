package io.github.kmeret.frame.presentation.profile

import androidx.lifecycle.MutableLiveData
import io.github.kmeret.frame.data.github.GithubConfig
import io.github.kmeret.frame.data.github.GithubService
import io.github.kmeret.frame.domain.model.Profile
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import io.github.kmeret.frame.infrastructure.application.lifecycle.onNext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val githubService: GithubService
) : BaseViewModel() {

    val networkProfile = MutableLiveData<Profile>()

    override fun onInit() {

    }

    override fun onBackPressed() {

    }

    fun requestProfile() {
        CoroutineScope(Dispatchers.IO).launch {
            val profile = githubService.getProfile(GithubConfig.userName)
            withContext(Dispatchers.Main) {
                networkProfile.onNext(profile.map().map())
            }
        }
    }
}