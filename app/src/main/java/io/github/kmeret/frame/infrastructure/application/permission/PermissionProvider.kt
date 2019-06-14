package io.github.kmeret.frame.infrastructure.application.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionProvider(
    private val appContext: Context
) {

    fun isGranted(permission: Permission): Boolean {
        return ContextCompat.checkSelfPermission(appContext, permission.name) == PackageManager.PERMISSION_GRANTED
    }

    fun request(fragment: Fragment, permission: Permission) {
        fragment.requestPermissions(arrayOf(permission.name), permission.requestCode)
    }

    fun isGrantedAfterRequest(permission: Permission, requestCode: Int, grantResults: IntArray): Boolean {
        if (permission.requestCode != requestCode) return false
        if (grantResults.isEmpty()) return false

        return grantResults[0] == PackageManager.PERMISSION_GRANTED
    }

}