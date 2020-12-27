package resti.example.appmemori.data.remote

import resti.example.appmemori.data.model.ResepList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ResepService {
    @GET("/api/recipes")
    fun listResep() : Call<ResepList>

    @GET("/api/recipe/")
    fun detailResep(@Query("url") url: String) : Call<ResepList>

    @GET("/api/search/")
    fun searchResep(@Query("q") query: String) : Call<ResepList>

}