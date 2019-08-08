package com.nixo.osubox.Common

object Config{
    const val k = "363c0b111c00b59dbeee36b3dbdc241ecf6ead2c"
    const val BMOBKEY = "363c0b111c00b59dbeee36b3dbdc241ecf6ead2c"

    var name = ""
    var img  = ""


    const val None  = 0
    const val NF    = 1
    const val EZ           = 2
    const val HD         = 8
    const val HR       = 16
    const val SD    = 32
    const val DT     = 64
    const val NC      = 512 // Only set along with DoubleTime. i.e: NC only gives 576
    const val FL     = 1024
    const val PF        = 16384 // Only set along with SuddenDeath. i.e: PF only gives 16416

    //NF
    const val NFHD = NF+HD
    const val NFDT = NF+DT
    const val NFNC = NF+NC
    const val NFHR = NF+HR
    const val NFFL = NF+FL
    const val NFHRFL = NF+HR+FL
    const val NFHDHR = NF+HD+HR
    const val NFHDDT = NF+HD+DT
    const val NFHDHRDT = NF+HD+HR+DT
    const val NFHDHRNC = NF+HD+HR+NC
    const val NFHDHRFL = NF+HD+HR+FL

    //HD
    const val HDDT = HD+DT
    const val HDNC = HD+NC
    const val HDHR = HD+HR
    const val HDFL = HD+FL
    const val HDHRDT = HD+HR+DT
    const val HDHRNC = HD+NC+HR
    const val HDHRFL = HD+HR+FL

    //HR
    const val HRDT = HR+DT
    const val HRFL = HR+FL
    const val HRNC = HD+NC

    //EZ
    const val EZDT = EZ+ DT
    const val EZHD = EZ+ HD
    const val EZHDDT = EZ+ HDDT
    const val EZNFHDDT = EZ+ NFHDDT

}