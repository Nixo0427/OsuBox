package com.nixo.osubox.UserModel.View

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nixo.osubox.Common.BaseActivity
import com.nixo.osubox.R
import com.nixo.osubox.UserModel.Adapter.UserBpAdapter
import com.nixo.osubox.UserModel.Moudle.BeatmapsetInfo
import com.nixo.osubox.UserModel.Moudle.UserBP
import com.nixo.osubox.UserModel.Moudle.UserResponse
import com.nixo.osubox.UserModel.Presenter.UserPresenter
import com.nixo.osubox.Utils.Another.RankColorUtil
import com.nixo.osubox.Utils.Dialog.AlertUtils
import kotlinx.android.synthetic.main.include_user_info.*

class UserActivity : BaseActivity<UserPresenter>() {

    override fun onLayout(): Int = R.layout.activity_user

    private var u = """Sustain"""
    var  bpAdapter :UserBpAdapter? = null


    override fun initActivity() {
        AlertUtils.showProgress(true,this@UserActivity)
        rv_bp.layoutManager = LinearLayoutManager(this@UserActivity)
        presenter.getUser(u)
        presenter.getUserBp(u)

    }


    /**
     * Ouser信息装填
     */
    @SuppressLint("SetTextI18n")
    fun initUserAppBar(userData : UserResponse){
        tv_name.text = userData.username
        Glide.with(this@UserActivity).load(userData.user_img).into(civ_head)
        tv_rank.setTextColor(resources.getColor(RankColorUtil.getRankColor(userData.pp_rank)))
        tv_rank.text = "#${userData.pp_rank}"
        tv_c_rank.setTextColor(resources.getColor(RankColorUtil.getRankColor(userData.pp_country_rank)))
        tv_c_rank.text = "#${userData.pp_country_rank}"
        tv_lv.text = "Lv.${userData.level}"
        tv_pc.text = userData.playcount
        tv_acc.text = "${String.format("%.2f",userData.accuracy.toDouble())}%"
        tv_country.text = userData.country
        tv_watched.text = "${(userData.total_seconds_played.toDouble()*0.0002778)}H"
        tv_join.text = userData.join_date



    }

    /**
     * OuserBp信息装填
     */
    fun initUserBp(bpList : MutableList<UserBP> , beatMapsList:ArrayList<BeatmapsetInfo>){
        var adapter = UserBpAdapter(this@UserActivity,R.layout.item_user_bp,bpList)
        adapter.setBpInfo(beatMapsList)
        rv_bp.adapter = adapter
        AlertUtils.dismissProgress()
    }


    override fun onDestory() {

    }


}
