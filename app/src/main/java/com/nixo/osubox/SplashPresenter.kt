package com.nixo.osubox

import com.nixo.osubox.Common.BasePresenter
import com.nixo.osubox.Common.Config
import com.nixo.osubox.UserModel.Moudle.UserResponse
import com.nixo.osubox.Utils.NetWork.RetrofitClient
import com.safframework.log.L
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.Query

class SplashPresenter : BasePresenter<SplashActivity>() {
    interface UserServce {

        //获取玩家信息
        @GET("/api/get_user")
        fun getUser(@Query("k") k: String, @Query("u") userName: String): Deferred<List<UserResponse>>

    }

    object EventServceApi : SplashPresenter.UserServce by RetrofitClient.retrofit.create(SplashPresenter.UserServce::class.java)

    fun authUserVisibiliable(u: String) {
        GlobalScope.launch(Dispatchers.Main) {
            L.e("------Presenter-------------->OSUER IS VISIBIABLE  ${EventServceApi.getUser(Config.k, u).await().isEmpty()}")
            L.json("------Presenter-------------->OSUER IS VISIBIABLE  ${EventServceApi.getUser(Config.k, u).await()}")
            view.dudeFighterLock(EventServceApi.getUser(Config.k, u).await().isEmpty())
        }
    }

}