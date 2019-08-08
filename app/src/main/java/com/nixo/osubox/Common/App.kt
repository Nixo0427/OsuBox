package com.nixo.osubox.Common

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.os.Handler
import android.support.multidex.MultiDexApplication
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobConfig
import com.nixo.osubox.Common.Config.BMOBKEY
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
        initBmob()
    }

    private fun initBmob() {
        var config = BmobConfig.Builder(this)
            //设置appkey
            .setApplicationId(BMOBKEY)
            //请求超时时间（单位为秒）：默认15s
            .setConnectTimeout(30)
            //文件分片上传时每片的大小（单位字节），默认512*1024
            .setUploadBlockSize(1024*1024)
            //文件的过期时间(单位为秒)：默认1800s
            .setFileExpiration(2500)
            .build()
        Bmob.initialize(config)
    }

    private fun initCrash() {
        //集成bugly
    }




}

object AppContext : ContextWrapper(INSTANCE.baseContext)