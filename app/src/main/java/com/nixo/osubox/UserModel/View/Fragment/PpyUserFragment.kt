package com.nixo.osubox.UserModel.View.Fragment

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import com.nixo.osubox.Common.BaseFragment
import com.nixo.osubox.R
import com.nixo.osubox.UserModel.Adapter.UserBpAdapter
import com.nixo.osubox.UserModel.Moudle.BeatmapsetInfo
import com.nixo.osubox.UserModel.Moudle.UserBP
import com.nixo.osubox.UserModel.Moudle.UserResponse
import com.nixo.osubox.UserModel.Presenter.PpyUserPresenter
import com.nixo.osubox.Utils.Another.RankColorUtil
import com.nixo.osubox.Utils.Data.SharedUtils
import com.nixo.osubox.Utils.Dialog.AlertUtils
import kotlinx.android.synthetic.main.ppy_user_fragment.*


class PpyUserFragment : BaseFragment<PpyUserPresenter>() {

    override fun onLayout(): Int = R.layout.ppy_user_fragment


    private var u = ""
    var  bpAdapter :UserBpAdapter? = null
    private var inf : UserDataInterface? = null

    override fun initActivity() {
        u = SharedUtils(activity!!).getString("account")
        presenter.getUser(u)
        rv_bp.layoutManager = LinearLayoutManager(activity)
        rv_bp.isFocusable = false
        presenter.getUserBp(u)


    }

    fun bindFaceInf(inf : UserDataInterface){
        this.inf = inf
    }

    interface UserDataInterface{
        suspend  fun onFace(name:String,img:String,level:String)
    }

    //Acc计算公式


    /**
     * Ouser信息装填
     */
    @SuppressLint("SetTextI18n")
    suspend fun initUserAppBar(userData : UserResponse){
        //设置玩家信息
        inf!!.onFace(userData.username?:"",userData.user_img?:"",userData.level?:"")
        tv_rank.setTextColor(resources.getColor(RankColorUtil.getRankColor(userData.pp_rank)))
        tv_rank.text = "#${userData.pp_rank?:""}"
        tv_pp.text = "${userData.pp_raw} pp"
        tv_c_rank.setTextColor(resources.getColor(RankColorUtil.getRankColor(userData.pp_country_rank?:"")))
        tv_c_rank.text = "#${userData.pp_country_rank?:""}"
        tv_pc.text = userData.playcount?:""
        tv_acc.text = "${String.format("%.2f",userData.accuracy.toDouble())}%"
        tv_country.text = userData.country?:""
        tv_watched.text = "${String.format("%.2f",userData.total_seconds_played.toDouble()*0.0002778)}H"
        tv_join.text = userData.join_date?:""
    }

    /**
     * OuserBp信息装填
     */
    fun initUserBp(bpList : MutableList<UserBP>, beatMapsList:ArrayList<BeatmapsetInfo>){
        var adapter = UserBpAdapter(activity!!,R.layout.item_user_bp,bpList)
        adapter.setBpInfo(beatMapsList)
        rv_bp.adapter = adapter
        AlertUtils.dismissProgress()
    }


    override fun onDestory() {

    }
}