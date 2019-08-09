package com.nixo.osubox.Utils.Dialog

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import com.nixo.osubox.R
import com.nixo.osubox.Utils.Ext.boi

class SodaDialog(context: Context) : BaseDialog(context) {


    private val mContext = context
    private var infs :DialogCallBack? = null

    init {
        initView()
    }
    fun setInfs(infs :DialogCallBack):SodaDialog{
        this.infs = infs
        return this
    }

    interface DialogCallBack{
        fun onTrueBack()
        fun onFalseBack()
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
        var tvYes = view.findViewById<TextView>(R.id.tv_yes)
        var tvNo = view.findViewById<TextView>(R.id.tv_no)
        tvYes.boi {
            infs!!.onTrueBack()
        }
        tvNo.boi {
            infs!!.onFalseBack()
        }
        setContentView(view)
        window!!.setGravity(Gravity.CENTER)



    }


}