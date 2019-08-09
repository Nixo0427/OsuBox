package com.nixo.osubox.UserModel.View.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.PopupWindow
import com.nixo.osubox.Common.BaseFragment
import com.nixo.osubox.R
import com.nixo.osubox.UserModel.Moudle.PPPUserData
import com.nixo.osubox.UserModel.Presenter.PpPlusUserPresenter
import com.nixo.osubox.Utils.Data.Preference
import com.nixo.osubox.Utils.Data.SharedUtils
import com.nixo.osubox.Utils.Data.pref
import com.nixo.osubox.Utils.View.ZLPolygonView
import com.safframework.log.L
import kotlinx.android.synthetic.main.pp_plus_user_fragment.*


class PpPlusUserFragment : BaseFragment<PpPlusUserPresenter>() {
    override fun onLayout(): Int = R.layout.pp_plus_user_fragment

    override fun initActivity() {
        presenter.getPPPUserData(SharedUtils(activity!!).getString("account"))
    }

    fun initStatistical(data: PPPUserData){
        initUserHexagonal(data)
        initUserBaseData(data)
    }

    @SuppressLint("SetTextI18n")
    private fun initUserBaseData(data: PPPUserData) {
        tv_user_data.text =
            """
        PP+Rank: ${data.user_data.Rank}
        PP+CountryRank: ${data.user_data.CountryRank}
        PerformanceTotal: ${String.format("%.2f",data.user_data.PerformanceTotal)}
        SpeedTotal: ${String.format("%.2f",data.user_data.SpeedTotal)}
        AimTotal:${String.format("%.2f",data.user_data.AimTotal)}
        JumpAimTotal: ${String.format("%.2f",data.user_data.JumpAimTotal)}
        FlowAimTotal: ${String.format("%.2f",data.user_data.FlowAimTotal)}
        AccuracyPercentTotal: ${String.format("%.2f",data.user_data.AccuracyPercentTotal)}
        AccuracyTotal: ${String.format("%.2f",data.user_data.AccuracyTotal)}
        PrecisionTotal: ${String.format("%.2f",data.user_data.PrecisionTotal)}
        StaminaTotal: ${String.format("%.2f",data.user_data.StaminaTotal)}
        OsuTotal:${String.format("%.2f",(data.user_data.AimTotal+data.user_data.SpeedTotal+data.user_data.AccuracyPercentTotal
                    +data.user_data.PrecisionTotal+data.user_data.JumpAimTotal+data.user_data.FlowAimTotal
                    +data.user_data.AccuracyTotal+data.user_data.StaminaTotal+data.user_data.StaminaTotal+
                    data.user_data.PerformanceTotal+data.user_data.PerformanceTotal)/11)}
        """.trimIndent()
    }

    private fun initUserHexagonal(data: PPPUserData) {
        var FlowT = (data.user_data.FlowAimTotal/10000+0.5).toFloat()
        var AimT = (data.user_data.JumpAimTotal/15000+0.5).toFloat()
        var AccT = (data.user_data.AccuracyTotal/10000+0.5).toFloat()
        var PreT = (data.user_data.PrecisionTotal/10000+0.5).toFloat()
        var SpdT = (data.user_data.SpeedTotal/10000+0.5).toFloat()
        var StmT = (data.user_data.StaminaTotal/10000+0.5).toFloat()

        val values = ArrayList<Float>()
        values.add(AccT)
        values.add(AimT)
        values.add(PreT)
        values.add(SpdT)
        values.add(StmT)
        values.add(FlowT)

        L.i("StmT$StmT AimT$AimT PreT$PreT SpdT$SpdT FlowT$FlowT AccT$AccT")
        var textLabs = ArrayList<String>()
        textLabs.add("""Accuracy${data.user_data.AccuracyTotal.toInt()}""".trimMargin())
        textLabs.add("""Aim${data.user_data.JumpAimTotal.toInt()}""".trimMargin())
        textLabs.add("""Precision${data.user_data.PrecisionTotal.toInt()}""".trimMargin())
        textLabs.add("""Speed${data.user_data.SpeedTotal.toInt()}""".trimMargin())
        textLabs.add("""Stamina${data.user_data.StaminaTotal.toInt()}""".trimMargin())
        textLabs.add("""Flow${data.user_data.FlowAimTotal.toInt()}""".trimMargin())
        polygonview.setTextLabels(textLabs)
        polygonview.setPolygonValues(values)
    }

    override fun onDestory() {}
}