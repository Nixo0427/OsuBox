package com.nixo.osubox.Utils.Data

import com.nixo.osubox.Common.AppContext

object SharedSetting{
    var account : String by Preference(AppContext,"account","")
    var passWord : String by Preference(AppContext,"password","")

}