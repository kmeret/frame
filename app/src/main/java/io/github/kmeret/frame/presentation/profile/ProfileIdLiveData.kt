package io.github.kmeret.frame.presentation.profile

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

class ProfileIdLiveData(private val prefs: SharedPreferences) : LiveData<Long>() {

    private val profileIdKey = "PROFILE_ID"

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener listener@ { sharedPreferences, key ->
        if (key != profileIdKey) return@listener

        value = sharedPreferences.getLong(profileIdKey, 0)
    }

    override fun onActive() {
        super.onActive()
        value = prefs.getLong(profileIdKey, 0)
        prefs.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onInactive() {
        prefs.unregisterOnSharedPreferenceChangeListener(listener)
        super.onInactive()
    }

    fun save(id: Long) {
        with(prefs.edit()) {
            putLong(profileIdKey, id)
            apply()
        }
    }

}