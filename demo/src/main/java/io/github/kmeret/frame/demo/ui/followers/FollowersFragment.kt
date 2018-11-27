package io.github.kmeret.frame.demo.ui.followers

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import io.github.kmeret.frame.android.extensions.attachAdapter
import io.github.kmeret.frame.android.extensions.visible
import io.github.kmeret.frame.android.ui.RecyclerAdapter
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.demo.domain.entity.User
import io.github.kmeret.frame.demo.ui.ExceptionHandler
import io.github.kmeret.frame.lifecycle.ViewModelFragment
import io.github.kmeret.frame.network.loadByUrl
import kotlinx.android.synthetic.main.fragment_followers.*
import kotlinx.android.synthetic.main.fragment_followers.view.*
import kotlinx.android.synthetic.main.template_user.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class FollowersFragment : ViewModelFragment<FollowersViewModel>() {

    override val layoutResId = R.layout.fragment_followers
    override val viewModel: FollowersViewModel by viewModel()

    private lateinit var userAdapter: RecyclerAdapter<User>

    override fun initView(rootView: View) {
        userAdapter = RecyclerAdapter(R.layout.template_user) { user, userRootView ->
            userRootView.apply {
                userAvatarView.loadByUrl(user.avatarUrl)
                userFullNameView.text = user.login
            }
        }

        rootView.apply {
            followersListView.apply {
                attachAdapter(userAdapter)
                addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
            }
            followersRefreshLayout.setOnRefreshListener { viewModel.requestUserListRx(viewLifecycleOwner) }
        }
    }

    override fun initViewModel() {
        viewModel.getUserList().observe { userAdapter.updateList(it) }
        viewModel.getError().observe { ExceptionHandler.handle(it, requireContext()) }
        viewModel.isEmpty().observe { isEmpty ->
            followersEmptyView.visible(isEmpty)
            followersListView.visible(!isEmpty)
        }
        viewModel.isLoading().observe { isLoading ->
            followersRefreshLayout.isRefreshing = isLoading
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestUserListRx(viewLifecycleOwner)
    }
}