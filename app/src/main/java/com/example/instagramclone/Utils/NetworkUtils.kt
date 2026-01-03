package com.example.instagramclone.Utils

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission

object NetworkUtils {

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isNetworkAvailable(context: Context): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        @Suppress("DEPRECATION")
        val networkInfo = cm.activeNetworkInfo

        @Suppress("DEPRECATION")
        return networkInfo != null && networkInfo.isConnected
    }
}
