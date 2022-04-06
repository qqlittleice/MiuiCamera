package com.yuk.miuicamera.utils

import android.os.Build
import com.yuk.miuicamera.BuildConfig

object Config {
    val AndroidSDK: Int = Build.VERSION.SDK_INT
    const val modulePackage = BuildConfig.APPLICATION_ID
    const val hostPackage = "com.android.camera"
    const val SP_NAME = "MiuiCameraPerf"
    const val TAG = "MiuiCamera"
}
