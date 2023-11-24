package com.credicel.ineqreset

import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import java.util.*


class AdminReceiver : DeviceAdminReceiver() {
    var dpm: DevicePolicyManager? = null
    var current_time: Long = 0
    var myThread: Timer? = null

    override fun onDisabled(context: Context, intent: Intent) {
        // Called when the app is about to be deactivated as a device administrator.
        // Deletes previously stored password policy.
        super.onDisabled(context, intent)
        //Log.d("Root", "Device Owner DISABLED");

    }

    override fun onEnabled(context: Context, intent: Intent) {
        // Called when the app is about to be deactivated as a device administrator.
        // Deletes previously stored password policy.
        super.onDisabled(context, intent)
        //Log.d("Root", "Device Owner ENABLED");
    }
}