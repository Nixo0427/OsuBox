package com.nixo.osubox.Utils.Dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.nixo.osubox.Utils.Another.StringUtils


/**
 * 自定义dialog宽度
 * @author zhanghongyu
 * @created at 2018/12/31
 */
open class BaseDialog : Dialog {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, themeResId: Int) : super(context, themeResId) {}

    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {}

    override fun show() {
        super.show()
        val window = window
        window.setLayout(StringUtils.dip2px(context,280f),StringUtils.dip2px(context,150f))
        //背景透明
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}