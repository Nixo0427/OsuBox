package com.nixo.osubox.Utils.Data


import com.nixo.osubox.Common.App
import kotlin.reflect.jvm.jvmName

inline fun <reified R, T> R.pref(default: T,key:String) = Preference(
        App._context!!,key, default, R::class
        .jvmName)