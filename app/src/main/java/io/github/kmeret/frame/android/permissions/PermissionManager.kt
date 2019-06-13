package io.github.kmeret.frame.android.permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionManager(private val activity: AppCompatActivity) {

    companion object {
        const val REQUEST_CODE = 1
        const val GRANTED = PackageManager.PERMISSION_GRANTED
        const val DENIED = PackageManager.PERMISSION_DENIED

        const val WRITE = Manifest.permission.WRITE_EXTERNAL_STORAGE
        const val BAD_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
        const val GOOD_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    }

    var onPermissionsChecked: ((granted: Boolean) -> Unit)? = null

    fun requestPermission(vararg permissions: String) {
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE)
    }

    fun onRequestPermissionsResult(requestCode: Int,
                                   permissions: Array<out String>,
                                   grantResults: IntArray) {

        if (requestCode != REQUEST_CODE) {
            onPermissionsChecked?.invoke(false)
            return
        }

        if (permissions.isEmpty() || grantResults.isEmpty()) {
            onPermissionsChecked?.invoke(false)
            return
        }

        val permissionList = ArrayList<Permission>()

        permissions.forEachIndexed { index, permission ->
            val isGranted = grantResults[index] == GRANTED
            permissionList.add(Permission(permission, isGranted))
        }

        with(permissionList) {
            val granted = this.isNotEmpty() && this.all { it.isGranted }
            onPermissionsChecked?.invoke(granted)
        }

    }

    private fun isPermissionGranted(permission: String): Boolean {
        val response = ContextCompat.checkSelfPermission(activity, permission)
        return when (response) {
            GRANTED -> true
            DENIED -> false
            else -> false
        }
    }

}