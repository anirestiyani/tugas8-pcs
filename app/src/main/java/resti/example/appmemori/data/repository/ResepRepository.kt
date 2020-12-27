package resti.example.appmemori.data.repository

import resti.example.appmemori.data.model.ActionState
import resti.example.appmemori.data.model.Resep
import resti.example.appmemori.data.remote.ResepService
import resti.example.appmemori.data.remote.RetrofitApi
import retrofit2.Retrofit
import retrofit2.await
import java.lang.Exception

class ResepRepository {
    private val resepService: ResepService by lazy { RetrofitApi.ResepService() }

    suspend fun listResep() : ActionState<List<Resep>>{
        return try {
            val list = resepService.listResep().await()
            ActionState(list.results)
        } catch (e: Exception){
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun detailResep(url: String) : ActionState<Resep> {
        return try{
            val list = resepService.detailResep(url).await()
            ActionState(list.results.first())
        }catch (e: Exception){
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun searchResep(query: String) : ActionState<List<Resep>>{
        return try {
            val list = resepService.searchResep(query).await()
            ActionState(list.results)
        } catch (e: Exception){
            ActionState(message = e.message, isSuccess = false)
        }
    }
}