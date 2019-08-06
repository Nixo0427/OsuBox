package com.nixo.osubox.Common

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.os.Handler
import android.support.multidex.MultiDexApplication
import com.safframework.log.L

private lateinit var INSTANCE:MultiDexApplication

class APP: MultiDexApplication(){
    companion object {
        var mHandler: Handler? = null
        var mContext: Context? = null
    }


        fun getWidth(): Int {
            return AppContext.resources.displayMetrics.widthPixels
        }

        fun getHeight(): Int {
            return AppContext.resources.displayMetrics.heightPixels
        }

        fun getHandler(): Handler {
            return mHandler!!
        }
        fun getContext():Context = mContext!!


    override fun onCreate() {
        super.onCreate()
        mHandler = Handler()
        mContext = baseContext
        L.logLevel= L.LogLevel.INFO
        initCrash()
    }

    private fun initCrash() {
        //集成bugly
    }




}

object AppContext : ContextWrapper(INSTANCE.baseContext)