package com.nixo.osubox.Utils.Ext

import android.view.View

inline fun<T : View> T.boi(crossinline block: (T) -> Unit)=setOnClickListener { block(this) }