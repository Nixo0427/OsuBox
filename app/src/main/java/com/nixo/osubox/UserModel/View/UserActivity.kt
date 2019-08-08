package com.nixo.osubox.UserModel.View

import android.graphics.BitmapFactory
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import com.bumptech.glide.Glide
import com.nixo.osubox.Common.BaseActivity
import com.nixo.osubox.R
import com.nixo.osubox.UserModel.Adapter.PPFragmentPagerAdapter
import com.nixo.osubox.UserModel.Presenter.UserPresenter
import com.nixo.osubox.UserModel.View.Fragment.PpPlusUserFragment
import com.nixo.osubox.UserModel.View.Fragment.PpyUserFragment
import com.nixo.osubox.Utils.Another.RenderScriptBitmapBlur
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.include_user_info.*


class UserActivity : BaseActivity<UserPresenter>() ,PpyUserFragment.UserDataInterface {


    override fun onLayout(): Int = R.layout.activity_user


    @RequiresApi(Build.VERSION_CODES.M)
    override fun initActivity() {
        //设置高斯模糊
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.nixo_bg2)
        var blurBitmap = RenderScriptBitmapBlur(this@UserActivity).getBlurBitmap(25, bitmap)
        iv_barbg.setImageBitmap(blurBitmap)
        var ppyUserFragment = PpyUserFragment()
        ppyUserFragment.bindFaceInf(this)
        var ppPlusUserFragment = PpPlusUserFragment()
        var list = ArrayList<Fragment>()
        list.add(ppyUserFragment)
        list.add(ppPlusUserFragment)
        vp_pager.adapter = PPFragmentPagerAdapter(supportFragmentManager,list)
        vp_pager.currentItem = 0


    }
    //Fragment回调，头像和玩家姓名
    override fun onFace(name: String, img: String,level:String) {
        tv_name.text = name
        Glide.with(this).load(img).into(civ_head)
        tv_lv.text = "Lv.$level"
    }



    override fun onDestory() {}


}
