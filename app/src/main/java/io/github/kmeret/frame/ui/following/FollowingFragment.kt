package io.github.kmeret.frame.ui.following

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import io.github.kmeret.frame.android.extensions.attachAdapter
import io.github.kmeret.frame.android.extensions.visible
import io.github.kmeret.frame.android.ui.RecyclerAdapter
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.domain.entity.User
import io.github.kmeret.frame.lifecycle.ViewModelFragment
import io.github.kmeret.frame.network.loadByUrl
import io.github.kmeret.frame.ui.ExceptionHandler
import kotlinx.android.synthetic.main.fragment_following.*
import kotlinx.android.synthetic.main.fragment_following.view.*
import kotlinx.android.synthetic.main.template_user.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class FollowingFragment : ViewModelFragment<FollowingViewModel>() {

    override val layoutResId = R.layout.fragment_following
    override val viewModel: FollowingViewModel by viewModel()

    private lateinit var userAdapter: RecyclerAdapter<User>

    override fun initView(rootView: View) {
        userAdapter = RecyclerAdapter(R.layout.template_user) { user, userRootView ->
            userRootView.apply {
                userAvatarView.loadByUrl(user.avatarUrl)
                userFullNameView.text = user.login
            }
        }

        rootView.apply {
            followingListView.apply {
                attachAdapter(userAdapter)
                addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
            }
            followingRefreshLayout.setOnRefreshListener { viewModel.requestUserList() }
        }
    }

    override fun initViewModel() {
        viewModel.getUserList().observe { userAdapter.updateList(it) }
        viewModel.getError().observe { ExceptionHandler.handle(it, requireContext()) }
        viewModel.isEmpty().observe { isEmpty ->
            followingEmptyView.visible(isEmpty)
            followingListView.visible(!isEmpty)
        }
        viewModel.isLoading().observe { isLoading ->
            followingRefreshLayout.isRefreshing = isLoading
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestUserList()
    }

}