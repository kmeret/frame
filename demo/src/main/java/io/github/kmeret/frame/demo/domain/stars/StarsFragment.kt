package io.github.kmeret.frame.demo.domain.stars

import android.view.View
import io.github.kmeret.frame.android.extensions.attachAdapter
import io.github.kmeret.frame.android.ui.RecyclerAdapter
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.lifecycle.ViewModelFragment
import io.github.kmeret.frame.lifecycle.observe
import kotlinx.android.synthetic.main.fragment_stars.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class StarsFragment : ViewModelFragment<StarsViewModel>() {

    override val layoutResId = R.layout.fragment_stars
    override val viewModel: StarsViewModel by viewModel()

    private lateinit var repoListAdapter: RecyclerAdapter<Repo>

    override fun initView(rootView: View) {
        repoListAdapter = RecyclerAdapter(R.layout.template_repo) { repo, repoRootView ->
            repoRootView.let {

            }
        }
        rootView.repoListView.attachAdapter(repoListAdapter)
    }

    override fun initViewModel() {
        viewModel.getRepoList().observe(this) {
            repoListAdapter.updateList(it)
        }
    }

}