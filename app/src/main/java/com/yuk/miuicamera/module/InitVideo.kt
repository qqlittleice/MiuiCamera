package com.yuk.miuicamera.module

import com.github.kyuubiran.ezxhelper.utils.Log
import com.yuk.miuicamera.utils.ktx.findClass
import com.yuk.miuicamera.utils.ktx.hookAfterMethod

class InitVideo {

    fun init() {
        try {
            val cameraCapabilitiesClass = "com.android.camera2.CameraCapabilities".findClass()
            "com.android.camera.data.data.config.ComponentConfigVideoQuality".hookAfterMethod(
                "initVideoMode",
                List::class.java,
                Int::class.java,
                Int::class.java,
                cameraCapabilitiesClass,
                List::class.java
            ) {

            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
    }

}