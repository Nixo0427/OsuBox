package com.nixo.osubox

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.view.View
import com.bumptech.glide.Glide
import com.nixo.colagauss.GaussBuilder
import com.nixo.osubox.Common.BaseActivity
import com.nixo.osubox.UserModel.View.UserActivity
import com.nixo.osubox.Utils.Another.RenderScriptBitmapBlur
import com.nixo.osubox.Utils.Data.SharedUtils
import com.nixo.osubox.Utils.Dialog.AlertUtils
import com.nixo.osubox.Utils.Ext.boi
import com.nixo.osubox.Utils.LaucherTime
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_splash.*
import java.lang.NullPointerException


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
        LaucherTime.endTime()
        val  splashBgBitmap = BitmapFactory.decodeResource(resources, R.mipmap.splash_bg)
        val  blurBitmap = RenderScriptBitmapBlur(this@SplashActivity).getBlurBitmap(25,splashBgBitmap)
        Glide.with(this).load(blurBitmap).into(iv_bg)
//       initBg()
        if(SharedUtils(this@SplashActivity).getString("account").isEmpty()){
            rv_first.visibility = View.VISIBLE
            welcomeAnimation()
        }else{
            rv_first.visibility = View.GONE
            Action(UserActivity::class.java)
            finish()
        }


    }
//    private  fun initBg() {
//        GaussBuilder(this).resources(R.mipmap.splash_bg).load(iv_bg)
//    }

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
                            ObjectAnimator.ofFloat(view_after,"scaleX",1.0f,6.6f),
                            ObjectAnimator.ofFloat(view_after,"scaleY",1.0f,9f))
                        it.duration = 1000
                        it.addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                cd_login_bg.visibility = View.VISIBLE
                                et_account.visibility = View.VISIBLE
                                ObjectAnimator.ofFloat(et_account,"alpha",0f,1f).start()
                                iv_join.visibility = View.VISIBLE
                                ObjectAnimator.ofFloat(iv_join,"scaleX",0f,1.8f,1f).start()
                                ObjectAnimator.ofFloat(iv_join,"scaleY",0f,1.8f,1f).start()
                                iv_join.boi {
//                                    throw NullPointerException("test")
                                    AlertUtils.showProgress(false,this@SplashActivity)
                                    presenter.authUserVisibiliable(et_account.text.toString())
//                                     Action(MainActivity::class.java)

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
            Toasty.error(this@SplashActivity,"Bind Fail,Not this Ouser ;w;").show()

        }
    }

    override fun onDestory() {}


}
