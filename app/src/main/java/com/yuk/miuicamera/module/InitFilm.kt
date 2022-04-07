package com.yuk.miuicamera.module

import com.github.kyuubiran.ezxhelper.utils.Log
import com.yuk.miuicamera.utils.ktx.*

class InitFilm {

    fun init() {
        try {
            "com.android.camera.features.mode.film.delay.LongExpModuleEntry".hookBeforeMethod("support") {
                it.result = true
            }
            "com.android.camera.features.mode.film.slowshutter.SlowShutterModuleEntry".hookBeforeMethod("support") {
                it.result = true
            }
            "com.android.camera.features.mode.film.timefreeze.TimeFreezeModuleEntry".hookBeforeMethod("support") {
                it.result = true
            }
            "com.android.camera.features.mode.film.dream.DreamModuleEntry".hookBeforeMethod("support") {
                it.result = true
            }
            "com.android.camera.features.mode.more.film.oreFilmModuleEntry".hookBeforeMethod("support") {
                it.result = true
            }
            "com.android.camera.features.mode.film.dollyzoom.DollyZoomModuleEntry".hookBeforeMethod("support") {
                it.result = "com.android.camera.CameraSettings".findClass().callStaticMethod("shouldUltraWideLDCBeVisibleInMode", 163) as Boolean
            }
            "com.android.camera.data.observeable.FilmViewModel".hookAfterMethod("getFilmListObservable") {

            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
    }

}