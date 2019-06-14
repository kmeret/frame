package io.github.kmeret.frame.infrastructure.application.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import io.github.kmeret.frame.R
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMActivity
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMFragment
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

abstract class NavActivity<VM : BaseViewModel> : VMActivity<VM>() {

    abstract val fragmentsContainerId: Int

    private val navigationHolder: NavigatorHolder by inject()
    private val currentFragment: VMFragment<*>?
        get() = supportFragmentManager.findFragmentById(fragmentsContainerId) as? VMFragment<*>

    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigator()
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        currentFragment?.onBackPressed()
    }

    private fun initNavigator() {
        navigator = object : SupportAppNavigator(this, fragmentsContainerId) {
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
