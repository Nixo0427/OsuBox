package com.nixo.osubox.Common
import android.content.res.Configuration
import android.os.Bundle

interface ILifecycler{

    fun onCreate(savedInstanceState: Bundle?)

//    public fun onSaveInstanceState(outState : Bundle?)

    fun onSaveInstanceState(outState : Bundle)

    fun onConfigurationChanged(newConfig: Configuration)

    fun onResume()

    fun onStop()

    fun onDestory()

    fun onStart()

    fun onPause()



}