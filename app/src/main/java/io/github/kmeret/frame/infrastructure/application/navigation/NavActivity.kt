package io.github.kmeret.frame.infrastructure.application.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import io.github.kmeret.frame.R
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMActivity
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

abstract class NavActivity<VM : BaseViewModel> : VMActivity<VM>() {

    private lateinit var navigator: Navigator

    private val currentFragment: VMFragment<*>?
        get() = supportFragmentManager.findFragmentById(getFragmentsContainerId()) as? VMFragment<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigator()
        lifecycle.addObserver(NavLifecycle(navigator))
    }

    @IdRes
    abstract fun getFragmentsContainerId(): Int

    private fun initNavigator() {
        navigator = object : SupportAppNavigator(this, getFragmentsContainerId()) {
            override fun setupFragmentTransaction(
                command: Command?,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction?
            ) {
                setFragmentAnimation(fragmentTransaction)
            }
        }
    }

    private fun setFragmentAnimation(fragmentTransaction: FragmentTransaction?) {
        fragmentTransaction ?: return

        fragmentTransaction.setCustomAnimations(
            R.anim.fade_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.fade_out
        )
    }
}
