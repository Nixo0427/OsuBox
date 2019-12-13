package com.nixo.osubox.UserModel.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.nixo.osubox.R
import com.nixo.osubox.UserModel.Moudle.BeatmapsetInfo
import com.nixo.osubox.UserModel.Moudle.UserBP
import com.nixo.osubox.Utils.Another.ModeUtils
import com.nixo.osubox.Utils.RecyclerView.BaseAdapter
import com.nixo.osubox.Utils.RecyclerView.BaseHolder
import com.safframework.log.L
import java.lang.ref.Reference
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat

class UserBpAdapter(val context: Context, layout:Int, bpList : MutableList<UserBP> ) : BaseAdapter<UserBP>(context,layout,bpList){


    var format = NumberFormat.getInstance().apply {

    }

    var infoList = ArrayList<BeatmapsetInfo>()
    fun  setBpInfo(infoList : ArrayList<BeatmapsetInfo>){
        this.infoList = infoList
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun initView(p0: BaseHolder, position: Int) {
        var tvRank = p0.getView<TextView>(R.id.tv_rank)
        var tvAcc = p0.getView<TextView>(R.id.tv_acc)
        format.minimumIntegerDigits = 1
        format.roundingMode = RoundingMode.HALF_UP
        p0.getView<TextView>(R.id.tv_title).text = "${infoList[position].title}[${infoList[position].version}]"
//        p0.getView<TextView>(R.id.tv_acc).text = mDataList[position].accuracy
        p0.getView<TextView>(R.id.tv_mode).text = ModeUtils().getModeName(mDataList[position].enabled_mods)
        when(mDataList[position].rank){
            "S" -> {tvRank.setTextColor(context.resources.getColor(R.color.color_b_rank_s))}
            "X" -> {tvRank.setTextColor(context.resources.getColor(R.color.color_b_rank_s))}
            "XH" -> {tvRank.setTextColor(context.resources.getColor(R.color.color_b_rank_sh))}
            "SH" -> {tvRank.setTextColor(context.resources.getColor(R.color.color_b_rank_sh))}
            "A" -> {tvRank.setTextColor(context.resources.getColor(R.color.color_b_rank_a))}
            "B" -> {tvRank.setTextColor(context.resources.getColor(R.color.color_b_rank_b))}
            "C" -> {tvRank.setTextColor(context.resources.getColor(R.color.color_b_rank_c))}
            "D" -> {tvRank.setTextColor(context.resources.getColor(R.color.color_b_rank_d))}
            else -> {tvRank.setTextColor(context.resources.getColor(R.color.color_b_rank_d))}
        }

        var doubleAcc = calculationACC(
            mDataList[position].count300.toInt(), mDataList[position].count100.toInt()
            , mDataList[position].count50.toInt(), mDataList[position].countmiss.toInt())

        tvAcc.text = "${ DecimalFormat("#.00").format(doubleAcc)}% Acc"
        //ACC
        tvRank.text = if(mDataList[position].rank == "SH") "S" else if(mDataList[position].rank == "XH") "X" else  mDataList[position].rank
        p0.getView<TextView>(R.id.tv_pp).text ="${String.format("%.2f",mDataList[position].pp.toDouble())}PP"

    }
    fun calculationACC(c300:Int,c100:Int,c50:Int,cMiss:Int):Float = 100*(6*c300+2*c100+c50)/(6*(c50+c100+c300+cMiss)).toFloat()
}