package com.nixo.osubox.UserModel.Presenter

import com.nixo.osubox.Common.BasePresenter
import com.nixo.osubox.UserModel.Moudle.PPPUserData
import com.nixo.osubox.UserModel.View.Fragment.PpPlusUserFragment
import com.nixo.osubox.Utils.NetWork.RetrofitClient
import com.safframework.log.L
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.Path


class PpPlusUserPresenter : BasePresenter<PpPlusUserFragment>(){

    interface UserServce {

        @GET("api/user/{u}")
        fun getPPPUserData(@Path("u") u : String):Deferred<PPPUserData>

    }

    object EventServceApi : UserServce by RetrofitClient.retrofitPPP.create(UserServce::class.java)

    fun getPPPUserData(u:String){
//        EventServceApi.getPPPUserData(u)
        GlobalScope.launch(Dispatchers.Main){
            var await = EventServceApi.getPPPUserData(u).await()
            L.json(await)
            view.initStatistical(await)
        }


    }

}