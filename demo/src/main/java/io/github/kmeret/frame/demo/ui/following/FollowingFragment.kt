package io.github.kmeret.frame.demo.ui.following

import android.view.View
import io.github.kmeret.frame.android.extensions.attachAdapter
import io.github.kmeret.frame.android.ui.RecyclerAdapter
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.demo.domain.entity.User
import io.github.kmeret.frame.lifecycle.ViewModelFragment
import kotlinx.android.synthetic.main.fragment_following.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FollowingFragment : ViewModelFragment<FollowViewModel>() {

    override val layoutResId = R.layout.fragment_following
    override val viewModel: FollowViewModel by sharedViewModel()

    override fun initView(rootView: View) {
        rootView.apply {
            followingListView.attachAdapter(RecyclerAdapter<User>(R.layout.template_following) { user, userRootView ->
                userRootView.apply {

                }
            })
        }
    }

    override fun initViewModel() {

    }

}