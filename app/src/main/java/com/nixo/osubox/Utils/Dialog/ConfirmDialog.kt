package com.nixo.osubox.Utils.Dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import com.nixo.osubox.R

class ConfirmDialog(context: Context, themeResId: Int) : BaseDialog(context, themeResId) {


    private val mContext = context


    init {
        initView()
    }


    @SuppressLint("ObjectAnimatorBinding")
    override fun show() {
        super.show()
    }

    @SuppressLint("ObjectAnimatorBinding")
    override fun dismiss() {
        super.dismiss()
    }



    private fun initView() {
        var view = LayoutInflater.from(mContext).inflate(R.layout.dialog_confirm, null)
        setContentView(view)
        window!!.setGravity(Gravity.TOP)



    }

}