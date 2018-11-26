package io.github.kmeret.frame.demo.ui.following

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.github.kmeret.frame.demo.github.GithubService
import io.github.kmeret.frame.demo.github.model.GithubUser

class RxUserList(private val githubService: GithubService) : LiveData<List<GithubUser>>() {



    override fun observe(owner: LifecycleOwner, observer: Observer<in List<GithubUser>>) {
        super.observe(owner, observer)
    }

}

