package io.github.kmeret.frame.presentation.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.demo.databinding.FragmentReposBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ReposFragment : Fragment() {

    private val viewModel by sharedViewModel<ReposViewModel>()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val reposBinding: FragmentReposBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_repos, container, false)
        reposBinding.setLifecycleOwner(viewLifecycleOwner)
        reposBinding.viewModel = viewModel
        return reposBinding.root
    }

}