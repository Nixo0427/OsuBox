package com.nixo.osubox.UserModel.Moudle

data class UserResponse(
    val accuracy: String,
    val count100: String,
    val count300: String,
    val count50: String,
    val count_rank_a: String,
    val count_rank_s: String,
    val count_rank_sh: String,
    val count_rank_ss: String,
    val count_rank_ssh: String,
    val country: String,
    val events: List<Any>,
    val join_date: String,
    val level: String,
    val playcount: String,
    val pp_country_rank: String,
    val pp_rank: String,
    val pp_raw: String,
    val ranked_score: String,
    val total_score: String,
    val total_seconds_played: String,
    val user_id: String,
    val username: String,
    var user_img: String
)