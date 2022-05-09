package com.yuk.miuicamera

import android.app.Application
import android.content.Context
import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.github.kyuubiran.ezxhelper.init.InitFields.appContext
import com.yuk.miuicamera.module.InitVideo
import com.yuk.miuicamera.utils.Config
import com.yuk.miuicamera.utils.Config.TAG
import com.yuk.miuicamera.utils.ktx.hookBeforeMethod
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_LoadPackage

class XposedInit : IXposedHookLoadPackage, IXposedHookZygoteInit {

    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam) {
        EzXHelperInit.initZygote(startupParam)
    }

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (lpparam.packageName) {
            Config.hostPackage -> {
                Application::class.java.hookBeforeMethod("attach", Context::class.java) {
                    EzXHelperInit.apply {
                        initHandleLoadPackage(lpparam)
                        setLogTag(TAG)
                        setToastTag(TAG)
                        setLogXp(true)
                        initAppContext(it.args[0] as Context)
                        setEzClassLoader(appContext.classLoader)
                    }
                       doHook()
                }
            }
            else -> return
        }
    }

    private fun doHook() {
        InitVideo().init()  // 启用4K60Fps支持
    }
}