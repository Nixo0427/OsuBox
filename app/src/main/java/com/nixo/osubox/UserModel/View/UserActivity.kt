package com.nixo.osubox.UserModel.View

import android.animation.ObjectAnimator
import android.graphics.BitmapFactory
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.nixo.osubox.Common.BaseActivity
import com.nixo.osubox.R
import com.nixo.osubox.SplashActivity
import com.nixo.osubox.UserModel.Adapter.PPFragmentPagerAdapter
import com.nixo.osubox.UserModel.Presenter.UserPresenter
import com.nixo.osubox.UserModel.View.Fragment.PpPlusUserFragment
import com.nixo.osubox.UserModel.View.Fragment.PpyUserFragment
import com.nixo.osubox.Utils.Another.RenderScriptBitmapBlur
import com.nixo.osubox.Utils.Data.SharedUtils
import com.nixo.osubox.Utils.Dialog.AlertUtils
import com.nixo.osubox.Utils.Dialog.SodaDialog
import com.nixo.osubox.Utils.Ext.boi
import com.safframework.log.L
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.include_user_info.*


class UserActivity : BaseActivity<UserPresenter>() ,PpyUserFragment.UserDataInterface {


    override fun onLayout(): Int = R.layout.activity_user


    @RequiresApi(Build.VERSION_CODES.M)
    override fun initActivity() {
        AlertUtils.showProgress(false,this)
        //设置高斯模糊
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.nixo_bg2)
        var blurBitmap = RenderScriptBitmapBlur(this@UserActivity).getBlurBitmap(25, bitmap)
        iv_barbg.setImageBitmap(blurBitmap)
        initViewPager()
        tv_unset.boi {
            SodaDialog(this).also {
                it.setInfs(object : SodaDialog.DialogCallBack {
                    override fun onTrueBack() {
                        SharedUtils(this@UserActivity).putString("account","")
                        Action(SplashActivity::class.java)
                        it.dismiss()
                        finish()
                    }

                    override fun onFalseBack() {
                        it.dismiss()
                    }

                })
            }.show()

        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initViewPager() {
        var ppyUserFragment = PpyUserFragment()
        ppyUserFragment.bindFaceInf(this)
        var ppPlusUserFragment = PpPlusUserFragment()
        var list = ArrayList<Fragment>()
        list.add(ppyUserFragment)
        list.add(ppPlusUserFragment)
        vp_pager.adapter = PPFragmentPagerAdapter(supportFragmentManager,list)
        vp_pager.currentItem = 0
        tv_ppy.boi { vp_pager.currentItem = 0 }
        tv_ppp.boi { vp_pager.currentItem = 1 }
        vp_pager.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->


            if(scrollX>window.decorView.width/2){
//                ObjectAnimator.ofFloat(v_switch,"alpha",1f,0f).start()
//                ObjectAnimator.ofFloat(v_switch2,"alpha",0f,1f).start()
//                animator!!.duration = 1
//                animator.start()
                v_switch.visibility = View.INVISIBLE
                v_switch2.visibility= View.VISIBLE
            }else{
//                ObjectAnimator.ofFloat(v_switch,"alpha",0f,1f).start()
//                ObjectAnimator.ofFloat(v_switch2,"alpha",1f,0f).start()
                v_switch.visibility = View.VISIBLE
                v_switch2.visibility= View.INVISIBLE
            }
//                var animator = ObjectAnimator.ofFloat(v_switch,"translationX",oldScrollX.toFloat(),scrollX.toFloat())
//                animator!!.duration = 1
//                animator.start()



        }
    }

    //Fragment回调，头像和玩家姓名
    override fun onFace(name: String, img: String,level:String) {
        tv_name.text = name
//        Glide.with(this).load(img).ga

        Glide.with(this).load(img).into(civ_head)
        tv_lv.text = "Lv.$level"
    }



    override fun onDestory() {}


}
