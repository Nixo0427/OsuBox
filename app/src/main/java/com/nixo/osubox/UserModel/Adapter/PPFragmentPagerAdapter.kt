package com.nixo.osubox.UserModel.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PPFragmentPagerAdapter(manager:FragmentManager,val fragmentList:List<Fragment>) : FragmentPagerAdapter(manager) {
    override fun getItem(p0: Int): Fragment = fragmentList[p0]

    override fun getCount(): Int = fragmentList.size
}