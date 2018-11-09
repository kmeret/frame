package io.github.kmeret.frame.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract val layoutResId: Int

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            layoutInflater.inflate(layoutResId, container, false)

    protected fun changeFragment(fragment: Fragment, slideAnimation: Boolean = true) =
            getBaseActivity().changeFragment(fragment, slideAnimation)

    protected fun changeTitle(title: String) = getBaseActivity().changeTitle(title)

    protected fun closeFragment() = requireActivity().onBackPressed()


    protected fun showBackArrow(action: (() -> Unit)? = null) =
            getBaseActivity().apply {
                backArrowVisible(true)
                onBackPressed = action
            }

    protected fun hideBackArrow() =
            getBaseActivity().apply {
                backArrowVisible(false)
                onBackPressed = null
            }


    protected fun getPermissionManager() = getBaseActivity().getPermissionManager()

    private fun getBaseActivity() = activity as BaseActivity

}