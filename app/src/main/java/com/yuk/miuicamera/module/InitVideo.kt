package com.yuk.miuicamera.module

import com.github.kyuubiran.ezxhelper.utils.Log
import com.yuk.miuicamera.utils.ktx.findClass
import com.yuk.miuicamera.utils.ktx.hookAfterMethod
import com.yuk.miuicamera.utils.ktx.hookBeforeMethod
import de.robv.android.xposed.XC_MethodHook

class InitVideo {

    fun init() {
        try {
            val cameraCapabilitiesClass = "com.android.camera2.CameraCapabilities".findClass()
            var hook: XC_MethodHook.Unhook? = null
            "com.android.camera.data.data.config.ComponentConfigVideoQuality".hookBeforeMethod("initVideoMode", List::class.java, Int::class.javaPrimitiveType, Int::class.javaPrimitiveType, cameraCapabilitiesClass, Int::class.javaPrimitiveType, List::class.java
            ) {
                hook = "com.android.camera.CameraSettings".hookAfterMethod("isSupportFpsRange", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType, Int::class.javaPrimitiveType, cameraCapabilitiesClass
                ) {
                    it.result = true
                }
            }
            "com.android.camera.data.data.config.ComponentConfigVideoQuality".hookAfterMethod("initVideoMode", List::class.java, Int::class.javaPrimitiveType, Int::class.javaPrimitiveType, cameraCapabilitiesClass, Int::class.javaPrimitiveType, List::class.java
            ) {
                hook?.unhook()
            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
    }
    
}