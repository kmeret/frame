package io.github.kmeret.frame.demo.navigation

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import io.github.kmeret.frame.android.base.BaseActivity
import io.github.kmeret.frame.demo.R
import kotlinx.android.synthetic.main.activity_nav.*

class NavActivity : BaseActivity() {

    override val layoutResId = R.layout.activity_nav

    private lateinit var navController: NavController

    override fun initView() {
        navController = Navigation.findNavController(this, R.id.navHostFragment)
        bottomNavBar.setupWithNavController(navController)
    }

}
