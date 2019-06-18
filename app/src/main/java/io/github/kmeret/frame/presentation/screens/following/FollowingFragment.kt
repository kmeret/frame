package io.github.kmeret.frame.presentation.screens.following

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import io.github.kmeret.frame.R
import io.github.kmeret.frame.domain.model.User
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMFragment
import io.github.kmeret.frame.infrastructure.presentation.extensions.initList
import io.github.kmeret.frame.infrastructure.presentation.extensions.isVisible
import io.github.kmeret.frame.infrastructure.presentation.extensions.loadByUrl
import io.github.kmeret.frame.infrastructure.presentation.list.ListAdapter
import kotlinx.android.synthetic.main.fragment_following.*
import kotlinx.android.synthetic.main.template_user.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class FollowingFragment : VMFragment<FollowingViewModel>() {
    override val layoutResId = R.layout.fragment_following

    override val viewModel: FollowingViewModel by viewModel()

    private val userAdapter: ListAdapter<User> = ListAdapter(R.layout.template_user) { user, userRootView ->
        userRootView.run {
            user_avatar.loadByUrl(user.avatarUrl)
            user_name.text = user.login
        }
    }

    override fun initLayout(savedInstanceState: Bundle?) {
        following_refresh.setOnRefreshListener { viewModel.requestFollowingList() }
        following_list.apply {
            initList(userAdapter)
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        }
    }

    override fun subscribeLiveData() {
        viewModel.isLoading.subscribe { following_refresh.isRefreshing = it }
        viewModel.followingList.subscribe {
            following_list.isVisible = it.isNotEmpty()
            following_empty.isVisible = it.isEmpty()
            userAdapter.updateList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestFollowingList()
    }
}