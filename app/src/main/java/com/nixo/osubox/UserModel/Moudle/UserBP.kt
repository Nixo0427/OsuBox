package com.nixo.osubox.UserModel.Moudle

import com.google.gson.JsonArray
import org.json.JSONArray

data class UserBP(
    val beatmap_id: String,
    val count100: String,
    val count300: String,
    val count50: String,
    val countgeki: String,
    val countkatu: String,
    val countmiss: String,
    val date: String,
    val enabled_mods: String,
    val maxcombo: String,
    val perfect: String,
    val pp: String,
    val rank: String,
    val score: String,
    val score_id: String,
    val user_id: String,
    val accuracy : String
)

data class userBpList(
    var userBp : JsonArray
)