package eu.rcaceiro.yourfirstandroidapplication.api

import eu.rcaceiro.yourfirstandroidapplication.model.Score
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST

interface ScoreService {
    @POST("/score")
    fun sendScore(@Field("score") score: Score): Call<Boolean>
}