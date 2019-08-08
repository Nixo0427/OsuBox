package com.nixo.osubox.UserModel.Presenter

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.nixo.osubox.Common.BasePresenter
import com.nixo.osubox.Common.Config
import com.nixo.osubox.UserModel.Moudle.BeatmapsetInfo
import com.nixo.osubox.UserModel.Moudle.UserBP
import com.nixo.osubox.UserModel.Moudle.UserResponse
import com.nixo.osubox.UserModel.Moudle.userBpList
import com.nixo.osubox.UserModel.View.Fragment.PpyUserFragment
import com.nixo.osubox.Utils.NetWork.RetrofitClient
import com.safframework.log.L
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.StringBuilder
import java.util.ArrayList

class PpyUserPresenter : BasePresenter<PpyUserFragment>() {

    var mUserImg: StringBuilder = StringBuilder("""https://a.ppy.sh/""")

    interface UserServce {

        //获取玩家信息
        @GET("/api/get_user")
        fun getUser(@Query("k") k: String, @Query("u") userName: String): Deferred<List<UserResponse>>

        //获取玩家BP
        @GET("/api/get_user_best")
        fun getUserBp(@Query("k") k: String, @Query("u") userName: String, @Query("limit") limit: Int): Deferred<JsonArray>

        @GET("/api/get_beatmaps")
        fun getBeatMaps(@Query("k") k: String, @Query("b") b: String): Deferred<List<BeatmapsetInfo>>

    }

    object EventServceApi : UserServce by RetrofitClient.retrofit.create(UserServce::class.java)

    fun getUser(u: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = EventServceApi.getUser(Config.k, u).await()
            L.json(data)
            mUserImg.append("""${data[0].user_id}?""")
            data[0].user_img = mUserImg.toString()
            view.initUserAppBar(data[0])

        }
    }

    fun getUserBp(u: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = EventServceApi.getUserBp(Config.k, u, 50).await()
            var list = userBpList(data)
            val mBpList = ArrayList<UserBP>()
            val beatMapData = ArrayList<BeatmapsetInfo>()
            data.forEach {
                var bp = Gson().fromJson<UserBP>(it, UserBP::class.java)
                mBpList.add(Gson().fromJson<UserBP>(it, UserBP::class.java))
                beatMapData.add(EventServceApi.getBeatMaps(Config.k, bp.beatmap_id).await()[0])
            }

            view.initUserBp(mBpList, beatMapData)

        }

    }
}