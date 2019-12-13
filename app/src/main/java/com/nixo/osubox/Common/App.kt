package com.nixo.osubox.Common


import android.app.Application
import android.content.Context
import android.os.Handler
import android.support.multidex.MultiDexApplication

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        _context = this
        _handler = Handler()
        initCrash()
    }

    private fun initCrash() {
    }
    fun getContext(): Context {
        return applicationContext
    }

    companion object {
        var _context: Application? = null
        var _handler: Handler? = null
        private fun getContext(): Context {
            return _context!!
        }

        fun getWidth(): Int {
            return getContext().resources.displayMetrics.widthPixels
        }

        fun getHeight(): Int {
            return getContext().resources.displayMetrics.heightPixels
        }

        fun getHandler(): Handler {
            return _handler!!
        }


    }
}