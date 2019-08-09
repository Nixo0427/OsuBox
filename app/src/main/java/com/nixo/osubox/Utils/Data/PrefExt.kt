package com.nixo.osubox.Utils.Data


import com.nixo.osubox.Common.AppContext
import kotlin.reflect.jvm.jvmName

inline fun <reified R, T> R.pref(default: T,key:String) = Preference(
        AppContext,key, default, R::class
        .jvmName)