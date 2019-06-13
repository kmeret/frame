package io.github.kmeret.frame.infrastructure.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import io.github.kmeret.frame.infrastructure.application.permissions.PermissionManager

abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutResId: Int

    lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        permissionManager = PermissionManager(this)
        setContentView(layoutResId)
        initView()
    }

    abstract fun initView()

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
