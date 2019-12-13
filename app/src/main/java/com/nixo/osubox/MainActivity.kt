package com.nixo.osubox

import android.app.ActionBar
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.ImageView

class MainActivity : AppCompatActivity() , View.OnTouchListener {
    override fun onTouch(v: View,event: MotionEvent): Boolean {
        var rawX = event.rawX
        var rawY = event.rawY
        when(event.action){
            MotionEvent.ACTION_MOVE->{
                mLayoutParames!!.x = rawX.toInt()
                mLayoutParames!!.y = rawY.toInt()
                windowManager.updateViewLayout(mImageView,mLayoutParames)
            }
        }
        return false
    }
    var mLayoutParames:WindowManager.LayoutParams? = null
    var mImageView : ImageView? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        initWindowTest()
    }

    private fun initWindowTest() {
        mImageView = ImageView(this)
        mImageView!!.setOnTouchListener(this)
        mImageView!!.setImageResource(R.mipmap.nixo)
        mLayoutParames = WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,99,0,PixelFormat.TRANSLUCENT)
        mLayoutParames!!.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
        mLayoutParames!!.gravity = Gravity.LEFT or Gravity.TOP
        mLayoutParames!!.x = 100
        mLayoutParames!!.y = 300
        windowManager.addView(mImageView,mLayoutParames)

    }
}
