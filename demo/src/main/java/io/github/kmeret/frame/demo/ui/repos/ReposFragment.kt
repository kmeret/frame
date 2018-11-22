package io.github.kmeret.frame.demo.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.kmeret.frame.android.extensions.attachAdapter
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.demo.databinding.FragmentReposBinding
import io.github.kmeret.frame.demo.databinding.TemplateRepoBinding
import io.github.kmeret.frame.demo.domain.entity.Repo
import io.github.kmeret.frame.lifecycle.observe
import kotlinx.android.synthetic.main.fragment_repos.view.*
import kotlinx.android.synthetic.main.template_repo.*
import org.koin.android.viewmodel.ext.android.viewModel

class ReposFragment : Fragment() {

    private val viewModel by viewModel<ReposViewModel>()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val reposBinding: FragmentReposBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_repos, container, false)
        reposBinding.setLifecycleOwner(viewLifecycleOwner)
        reposBinding.viewModel = viewModel
        return reposBinding.root
    }

}