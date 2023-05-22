package com.rnrootchecker

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.rnrootchecker.NativeRootCheckerSpec
import java.io.File
import android.os.Build
import android.util.Log


class RootCheckerModule(reactContext: ReactApplicationContext) : NativeRootCheckerSpec(reactContext) {

    override fun getName() = NAME

    override fun add(a: Double, b: Double): Double {
        return a + b;
    }

    override fun isDeviceRooted(): Boolean {
        val paths = arrayOf(
                "/system/app/Superuser.apk",
                "/sbin/su",
                "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su",
                "/su/bin/su"
        )

        try {
            for (path in paths) {
                if (File(path).exists()) return true
            }

            val buildTags: String = android.os.Build.TAGS
            Log.i("RootChecker", buildTags)
            if (buildTags != null && buildTags.contains("test-keys")) {
                return true;
            }

            // Check for root native process
            if (Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su")).exitValue() == 0) {
                return true
            }

        } catch (exception: Exception) {
            Log.e("RootChecker", "There was an error ", exception)
        }

        return false
    }


    companion object {
        const val NAME = "RNRootChecker"
    }
}