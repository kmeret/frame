package io.github.kmeret.frame.presentation.followers

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import io.github.kmeret.frame.R
import io.github.kmeret.frame.domain.model.User
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMFragment
import io.github.kmeret.frame.infrastructure.data.network.loadByUrl
import io.github.kmeret.frame.infrastructure.presentation.extensions.attachAdapter
import io.github.kmeret.frame.infrastructure.presentation.extensions.isVisible
import io.github.kmeret.frame.infrastructure.presentation.list.ListAdapter
import kotlinx.android.synthetic.main.fragment_followers.*
import kotlinx.android.synthetic.main.template_user.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class FollowersFragment : VMFragment<FollowersViewModel>() {
    override val layoutResId = R.layout.fragment_followers

    override val viewModel: FollowersViewModel by viewModel()

    private val userAdapter: ListAdapter<User> = ListAdapter(R.layout.template_user) { user, userRootView ->
            userRootView.run {
                user_avatar.loadByUrl(user.avatarUrl)
                user_name.text = user.login
            }
        }

    override fun initLayout(savedInstanceState: Bundle?) {
        followers_refresh.setOnRefreshListener { viewModel.requestUserList() }
        followers_list.run {
            attachAdapter(userAdapter)
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        }
    }

    override fun subscribeLiveData() {
        viewModel.isLoading.subscribe { followers_refresh.isRefreshing = it }
        viewModel.followersList.subscribe {
            followers_list.isVisible = it.isNotEmpty()
            followers_empty.isVisible = it.isEmpty()
            userAdapter.updateList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestUserList()
    }
}