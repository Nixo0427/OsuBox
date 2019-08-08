package com.nixo.osubox.Utils.Data


import com.nixo.osubox.Common.AppContext
import kotlin.reflect.jvm.jvmName

inline fun <reified R, T> R.pref(default: T) = Preference(
        AppContext,"", default, R::class
        .jvmName)