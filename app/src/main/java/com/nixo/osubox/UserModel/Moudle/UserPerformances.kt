package com.nixo.osubox.UserModel.Moudle

data class UserPerformances(
    val accuracy: List<Accuracy>,
    val aim: List<Aim>,
    val flow_aim: List<FlowAim>,
    val jump_aim: List<JumpAim>,
    val precision: List<Precision>,
    val speed: List<Speed>,
    val stamina: List<Stamina>,
    val total: List<Total>
)