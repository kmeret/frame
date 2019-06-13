package io.github.kmeret.frame.presentation.profile

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import io.github.kmeret.frame.application.App
import io.github.kmeret.frame.data.github.GithubConfig
import io.github.kmeret.frame.data.github.GithubService
import io.github.kmeret.frame.data.github.model.GithubProfile
import io.github.kmeret.frame.data.storage.dao.ProfileDao
import io.github.kmeret.frame.data.storage.entity.RoomProfile
import io.github.kmeret.frame.demo.BuildConfig
import io.github.kmeret.frame.domain.cases.NetworkLiveUseCase
import io.github.kmeret.frame.domain.entity.Profile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    application: Application,
    private val githubService: GithubService,
    private val profileDao: ProfileDao
) : AndroidViewModel(application) {

    private val sharedPrefs =
        getApplication<App>().getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    private val requestProfileUseCase = NetworkLiveUseCase<GithubProfile>()

    private val profileId: LiveData<Long> = Transformations.switchMap(requestProfileUseCase.data) { githubProfile ->
        profileDao.insert(githubProfile.map())
        ProfileIdLiveData(sharedPrefs).apply { save(githubProfile.id) }
    }

    private val roomProfile: LiveData<RoomProfile?> =
        Transformations.switchMap(profileId) { profileDao.getLiveById(it) }

    val profile: LiveData<Profile> = Transformations.map(roomProfile) { it?.map() }
    val networkProfile = MutableLiveData<Profile>()
    val loading: LiveData<Boolean> = requestProfileUseCase.loading
    val empty: LiveData<Boolean> = requestProfileUseCase.empty
    val error: LiveData<Exception> = requestProfileUseCase.error

    fun requestProfile() {
        CoroutineScope(Dispatchers.IO).launch {
            val profile = githubService.getProfile(GithubConfig.userName)
            withContext(Dispatchers.Main) {
                networkProfile.value = profile.map().map()
            }

        }
    }

}