package su.leff.network

import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("movies.json")
    fun signUp(): Call<List<PosterResponse>>
}