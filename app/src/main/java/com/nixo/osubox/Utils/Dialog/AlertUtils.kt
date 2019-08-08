package com.nixo.osubox.Utils.Dialog

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.widget.CardView
import android.view.View
import com.nixo.osubox.R



/**
 *
 * @description 自定义公共弹窗
 */
@SuppressLint("StaticFieldLeak")
object AlertUtils {
    private var mDialog: Dialog? = null
    private var mContext: Context? = null

    /*
	 * 显示进度条
	 */
    @SuppressLint("ObjectAnimatorBinding")
    fun showProgress(isCancel: Boolean, cxt: Context) {
        var view : View? = null
        if (mDialog == null || mContext != null && mContext!!.hashCode() != cxt.hashCode()) {
            mContext = cxt
            mDialog = Dialog(cxt, R.style.LoadingDialog)
            view = View.inflate(mContext, R.layout.dialog_loading, null)
            mDialog!!.setContentView(view)
            mDialog!!.setCanceledOnTouchOutside(false)// 按返回键不能退出
            mDialog!!.setOnDismissListener {
                dismissProgress()
            }
        }
        if (null != mDialog && !mDialog!!.isShowing) {
            mDialog!!.setCancelable(isCancel)
            mDialog!!.show()
        }
    }

    /*
	 * 进度条消失
	 */
    fun dismissProgress() {
        if (mDialog != null)
            if (mDialog!!.isShowing)
                mDialog!!.dismiss()
    }
    fun onDestroy(){
        if(mContext != null){
            mContext = null
//        if (gifDrawable!= null){
//
//            gifDrawable!!.recycle()
        }
    }
}
