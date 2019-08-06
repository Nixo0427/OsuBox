package com.nixo.osubox.Utils.Another

import com.nixo.osubox.Common.Config.DT
import com.nixo.osubox.Common.Config.EZ
import com.nixo.osubox.Common.Config.FL
import com.nixo.osubox.Common.Config.HD
import com.nixo.osubox.Common.Config.HR
import com.nixo.osubox.Common.Config.NC
import com.nixo.osubox.Common.Config.NF
import com.nixo.osubox.Common.Config.NFDT
import com.nixo.osubox.Common.Config.NFFL
import com.nixo.osubox.Common.Config.NFHD
import com.nixo.osubox.Common.Config.NFHDDT
import com.nixo.osubox.Common.Config.NFHDHR
import com.nixo.osubox.Common.Config.NFHR
import com.nixo.osubox.Common.Config.NFHRFL
import com.nixo.osubox.Common.Config.NFNC
import com.nixo.osubox.Common.Config.None
import com.nixo.osubox.Common.*
import com.nixo.osubox.Common.Config.EZDT
import com.nixo.osubox.Common.Config.EZHD
import com.nixo.osubox.Common.Config.EZHDDT
import com.nixo.osubox.Common.Config.EZNFHDDT
import com.nixo.osubox.Common.Config.HDDT
import com.nixo.osubox.Common.Config.HDFL
import com.nixo.osubox.Common.Config.HDHR
import com.nixo.osubox.Common.Config.HDHRDT
import com.nixo.osubox.Common.Config.HDHRFL
import com.nixo.osubox.Common.Config.HDHRNC
import com.nixo.osubox.Common.Config.HDNC
import com.nixo.osubox.Common.Config.HRDT
import com.nixo.osubox.Common.Config.HRFL
import com.nixo.osubox.Common.Config.HRNC
import com.nixo.osubox.Common.Config.NFHDHRDT
import com.nixo.osubox.Common.Config.NFHDHRFL
import com.nixo.osubox.Common.Config.NFHDHRNC
import com.nixo.osubox.Common.Config.SD

class ModeUtils{

    fun getModeName(mode:String):String= when(mode.toInt()){
        NF->"NF"
        EZ->"EZ"
        HD->"HD"
        HR->"HR"
        SD->"SD"
        DT->"DT"
        NC->"NC"
        FL->"FL"
        NFHD->"NFHD"
        NFDT->"NFDT"
        NFNC->"NFNC"
        NFHR->"NFHR"
        NFFL->"NFFL"
        HDDT->"HDDT"
        HDNC->"HDNC"
        HDHR->"HDHR"
        HDFL->"HDFL"
        HRDT->"HRDT"
        HRFL->"HRFL"
        HRNC->"HRNC"
        EZDT->"EZDT"
        EZHD->"EZHD"
        NFHRFL->"NFHRFL"
        NFHDHR->"NFHDHR"
        NFHDDT->"NFHDDT"
        HDHRDT->"HDHRDT"
        HDHRNC->"HDHRNC"
        HDHRFL->"HDHRFL"
        EZHDDT->"EZHDDT"
        NFHDHRDT->"NFHDHRDT"
        NFHDHRNC->"NFHDHRNC"
        NFHDHRFL->"NFHDHRFL"
        EZNFHDDT->"EZNFHDDT"
        else -> ""
    }

}