package io.github.kmeret.frame.demo.ui.profile

 import android.app.Application
 import android.content.Context
 import androidx.lifecycle.AndroidViewModel
 import androidx.lifecycle.LiveData
 import androidx.lifecycle.Transformations
 import io.github.kmeret.frame.demo.App
 import io.github.kmeret.frame.demo.BuildConfig
 import io.github.kmeret.frame.demo.domain.cases.NetworkLiveUseCase
 import io.github.kmeret.frame.demo.domain.entity.Profile
 import io.github.kmeret.frame.demo.github.GithubConfig
 import io.github.kmeret.frame.demo.github.GithubService
 import io.github.kmeret.frame.demo.github.model.GithubProfile
 import io.github.kmeret.frame.demo.storage.dao.ProfileDao
 import io.github.kmeret.frame.demo.storage.entity.RoomProfile

class ProfileViewModel(
        application: Application,
        private val githubService: GithubService,
        private val profileDao: ProfileDao
) : AndroidViewModel(application) {

    private val sharedPrefs = getApplication<App>().getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    private val requestProfileUseCase = NetworkLiveUseCase<GithubProfile>()

    private val profileId: LiveData<Long> = Transformations.switchMap(requestProfileUseCase.data) { githubProfile ->
        profileDao.insert(githubProfile.map())
        ProfileIdLiveData(sharedPrefs).apply { save(githubProfile.id) }
    }

    private val roomProfile: LiveData<RoomProfile?> = Transformations.switchMap(profileId) { profileDao.getLiveById(it) }

    val profile: LiveData<Profile> = Transformations.map(roomProfile) { it?.map() }
    val loading: LiveData<Boolean> = requestProfileUseCase.loading
    val empty: LiveData<Boolean> = requestProfileUseCase.empty
    val error: LiveData<Exception> = requestProfileUseCase.error

    fun requestProfile() {
        requestProfileUseCase.execute(githubService.getProfile(GithubConfig.userName))
    }

}