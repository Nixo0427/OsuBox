package com.nixo.osubox.UserModel.View.Fragment

import com.nixo.osubox.Common.BaseFragment
import com.nixo.osubox.R
import com.nixo.osubox.UserModel.Moudle.PPPUserData
import com.nixo.osubox.UserModel.Presenter.PpPlusUserPresenter
import com.safframework.log.L
import kotlinx.android.synthetic.main.pp_plus_user_fragment.*


class PpPlusUserFragment : BaseFragment<PpPlusUserPresenter>() {
    override fun onLayout(): Int = R.layout.pp_plus_user_fragment

    override fun initActivity() {
        presenter.getPPPUserData("Sustain")
    }

    fun initStatistical(data: PPPUserData){
        var SpdT = (data.user_data.SpeedTotal/10000+0.5).toFloat()
        var AimT = (data.user_data.AimTotal/10000+0.5).toFloat()
        var PreT = (data.user_data.PrecisionTotal/10000+0.5).toFloat()
        var FlowT = (data.user_data.FlowAimTotal/10000+0.5).toFloat()
        var AccT = (data.user_data.AccuracyTotal/10000+0.5).toFloat()
        var StmT = (data.user_data.StaminaTotal/10000+0.5).toFloat()

        val values = ArrayList<Float>()
        values.add(AccT)
        values.add(AimT)
        values.add(FlowT)
        values.add(PreT)
        values.add(StmT)
        values.add(SpdT)
        L.i("StmT$StmT AimT$AimT PreT$PreT SpdT$SpdT FlowT$FlowT AccT$AccT")
        var textLabs = ArrayList<String>()
        textLabs.add("""Accuracy
            |${data.user_data.AccuracyTotal.toInt()}""".trimMargin())
        textLabs.add("""Aim
            |${data.user_data.AimTotal.toInt()}""".trimMargin())
        textLabs.add("""Flow
            |${data.user_data.FlowAimTotal.toInt()}""".trimMargin())
        textLabs.add("""Precision
            |${data.user_data.PrecisionTotal.toInt()}""".trimMargin())
        textLabs.add("""Stamina
            |${data.user_data.StaminaTotal.toInt()}""".trimMargin())
        textLabs.add("""Speed
            |${data.user_data.SpeedTotal.toInt()}""".trimMargin())
//        textLabs.add("Accuracy")
//        textLabs.add("Aim")
//        textLabs.add("FlowAim")
//        textLabs.add("Precision")
//        textLabs.add("Stamina")
//        textLabs.add("Speed")
        polygonview.setTextLabels(textLabs)
        polygonview.setPolygonValues(values)
//        polygonview.setOnClickPolygonListeren(ZLPolygonView.onClickPolygonListeren { event, index ->
//            val rootView = LayoutInflater.from(activity!!).inflate(R.layout.pp_plus_user_fragment, null)
//
//            ValuePopupWindow(this@PpPlusUserFragment).showAtLocation(
//                rootView,
//                Gravity.TOP or Gravity.LEFT,
//                event.rawX.toInt(), event.rawY.toInt()
//            )
//        })
    }

    override fun onDestory() {}
}