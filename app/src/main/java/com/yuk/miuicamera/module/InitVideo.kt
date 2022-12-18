package com.yuk.miuicamera.module

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.github.kyuubiran.ezxhelper.utils.hookMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import de.robv.android.xposed.XC_MethodHook

class InitVideo {

    fun init() {
        try {
            var hook: XC_MethodHook.Unhook? = null
            findMethod("com.android.camera.data.data.config.ComponentConfigVideoQuality") {
                name == "initVideoMode" && parameterCount == 6
            }.hookMethod {
                before {
                    hook = findMethod("com.android.camera.CameraSettings") {
                        name == "isSupportFpsRange" && parameterCount == 4
                    }.hookAfter { hookParam ->
                        hookParam.result = true
                    }
                }
                after {
                    hook?.unhook()
                }
            }
        } catch (e: Throwable) {
            findMethod("com.android.camera.data.data.config.ComponentConfigVideoQuality") {
                name == "isSupport60FPS" && parameterCount == 3
            }.hookReturnConstant(true)
        }
    }

}