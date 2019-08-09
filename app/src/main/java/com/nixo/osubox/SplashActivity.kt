package com.nixo.osubox

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.nixo.osubox.Common.BaseActivity
import com.nixo.osubox.UserModel.View.UserActivity
import kotlinx.android.synthetic.main.activity_splash.*
import android.support.design.animation.AnimatorSetCompat.playTogether
import android.opengl.ETC1.getWidth
import android.opengl.ETC1.getHeight
import android.support.v4.view.ViewCompat.animate
import android.R.attr.translationY
import android.animation.*
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.support.annotation.NonNull
import android.support.annotation.RequiresApi
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.TextView
import android.widget.Toast
import cn.bmob.v3.util.V
import com.nixo.osubox.Common.APP
import com.nixo.osubox.Utils.Another.StringUtils
import com.nixo.osubox.Utils.Data.Preference
import com.nixo.osubox.Utils.Data.SharedUtils
import com.nixo.osubox.Utils.Data.pref
import com.nixo.osubox.Utils.Dialog.AlertUtils
import com.nixo.osubox.Utils.Ext.boi
import com.safframework.log.L
import es.dmoral.toasty.Toasty


class SplashActivity : BaseActivity<SplashPresenter>() {


    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            Action(UserActivity::class.java)
            super.handleMessage(msg)
        }


    }



    override fun onLayout(): Int = R.layout.activity_splash

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initActivity() {
        if(SharedUtils(this@SplashActivity).getString("account").isEmpty()){
            rv_first.visibility = View.VISIBLE
            welcomeAnimation()
        }else{
            rv_first.visibility = View.GONE
            Action(UserActivity::class.java)
            finish()
        }


    }

    private fun welcomeAnimation() {
        AnimatorSet().also {
            it.playTogether(
                ObjectAnimator.ofFloat(view_cc,"scaleX",1.0f,0.2f),
                ObjectAnimator.ofFloat(view_cc,"scaleY",1.0f,0.2f)
            )
            it.addListener(object : AnimatorListenerAdapter() {
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
//                   view_before.visibility = View.GONE
                    view_after.visibility = View.VISIBLE
                    AnimatorSet().also {
                        it.playTogether(
                            ObjectAnimator.ofFloat(tv_logo,"translationY",0f,-100f),
                            ObjectAnimator.ofFloat(view_after,"scaleX",1.0f,5.6f),
                            ObjectAnimator.ofFloat(view_after,"scaleY",1.0f,8f))
                        it.duration = 1000
                        it.addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                cd_login_bg.visibility = View.VISIBLE
                                et_account.visibility = View.VISIBLE
                                ObjectAnimator.ofFloat(et_account,"alpha",0f,1f).start()
                                iv_join.visibility = View.VISIBLE
                                ObjectAnimator.ofFloat(iv_join,"scaleX",0f,1.2f,1f).start()
                                ObjectAnimator.ofFloat(iv_join,"scaleY",0f,1.2f,1f).start()

                                iv_join.boi {
                                    AlertUtils.showProgress(false,this@SplashActivity)
                                    presenter.authUserVisibiliable(et_account.text.toString())


                                }
                            }
                        })
                    }.start()

//                   ViewAnimationUtils.createCircularReveal(view_after,StringUtils.dip2px(this@SplashActivity,200f),StringUtils.dip2px(this@SplashActivity,400f),0f,0f).start()
                }
            })
            it.duration = 1000
        }.start()


    }

    fun dudeFighterLock(visiable : Boolean){
        if (!visiable) {
            AlertUtils.dismissProgress()
            SharedUtils(this@SplashActivity).putString("account",et_account.text.toString())
            Action(UserActivity::class.java)
            finish()
        }else{
            AlertUtils.dismissProgress()
            Toasty.error(this@SplashActivity,"Login Fail,Not this Ouser ;w;").show()

        }
    }

    override fun onDestory() {}


}
