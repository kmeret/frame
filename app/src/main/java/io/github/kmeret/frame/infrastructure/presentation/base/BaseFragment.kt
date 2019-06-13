package io.github.kmeret.frame.infrastructure.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract val layoutResId: Int

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val rootView = layoutInflater.inflate(layoutResId, container, false)
        initView(rootView)
        return rootView
    }

    abstract fun initView(rootView: View)

    protected fun getPermissionManager() = getBaseActivity().permissionManager

    protected fun getBaseActivity() = activity as BaseActivity

}