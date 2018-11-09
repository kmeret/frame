package io.github.kmeret.frame.android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import io.github.kmeret.frame.android.permissions.PermissionManager

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var permissionManager: PermissionManager
    protected var fragmentLayoutResId = 0
    var onBackPressed: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        permissionManager = PermissionManager(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (fragmentLayoutResId == 0) return
        val fragment = supportFragmentManager.findFragmentById(fragmentLayoutResId) ?: return

        fragment.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed?.invoke()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        onBackPressed?.invoke()
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
            return
        }
        super.onBackPressed()
    }

    fun changeFragment(fragment: Fragment,
                       slideAnimation: Boolean = true,
                       clearStack: Boolean = false,
                       fragmentLayoutResId: Int = this.fragmentLayoutResId) {

        if (clearStack)
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        val transaction = supportFragmentManager.beginTransaction()

        if (slideAnimation)
            transaction.setCustomAnimations(
                    R.anim.start_in,
                    R.anim.start_out,
                    R.anim.finish_in,
                    R.anim.finish_out
            )

        transaction.replace(fragmentLayoutResId, fragment)
                .addToBackStack(null)
                .commit()
    }

    fun changeTitle(title: String) {
        supportActionBar?.title = title
    }

    fun backArrowVisible(visible: Boolean) =
            supportActionBar?.setDisplayHomeAsUpEnabled(visible)

    fun hamburgerVisible(visible: Boolean) =
            supportActionBar?.setHomeButtonEnabled(visible)

    fun actionBarVisible(visible: Boolean) =
            supportActionBar?.run { if (visible) show() else hide() }

    fun getPermissionManager() = permissionManager


}
