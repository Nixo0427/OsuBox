package com.nixo.osubox.Utils.Data

import com.nixo.osubox.Common.App


object SharedSetting{
    var account : String by Preference(App._context!!,"account","")
    var passWord : String by Preference(App._context!!,"password","")

}