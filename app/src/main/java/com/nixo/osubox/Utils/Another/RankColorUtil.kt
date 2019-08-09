package com.nixo.osubox.Utils.Another

import com.nixo.osubox.R

object RankColorUtil {

    fun getRankColor(rank : String) : Int{
        val rank = if(rank == "") 0 else rank.toInt()
        if(rank <2){
            return R.color.color_rank1
        }else if(rank in 2..50){
            return R.color.color_rank2
        }else if(rank in 51..100){
            return R.color.color_rank3
        }else if(rank in 101..1000){
            return R.color.color_rank4
        }else if(rank in 1001..4000){
            return R.color.color_rank5
        }else if(rank in 4001..10000){
            return R.color.color_rank6
        }else if(rank in 10000..100000){
            return R.color.color_rank7
        }else {
            return R.color.color_rank8
        }

    }

    fun getModeType(mode : String){
        mode.toInt()
    }

}