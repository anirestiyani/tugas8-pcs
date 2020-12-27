package resti.example.appmemori.ui.resep

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import resti.example.appmemori.data.model.ActionState
import resti.example.appmemori.data.model.Resep
import resti.example.appmemori.data.repository.ResepRepository

class ResepViewModel : ViewModel() {
    private val repo: ResepRepository by lazy { ResepRepository() }

    val loading = MutableLiveData(false)
    val actionState = MutableLiveData<ActionState<*>>()

    val listResp = MutableLiveData<List<Resep>>()
    val detailResp = MutableLiveData<Resep>()
    val searchResep = MutableLiveData<List<Resep>>()

    val url = MutableLiveData("")
    val query = MutableLiveData("")

    fun listResep(){
        viewModelScope.launch {
            loading.value = true
            val resp = repo.listResep()
            actionState.value = resp
            listResp.value = resp.data
            loading.value = false
        }
    }

    fun detailResep(url: String? = this.url.value){
        url?.let {
            viewModelScope.launch {
                loading.value = true
                val resp = repo.detailResep(it)
                actionState.value = resp
                detailResp.value = resp.data
                loading.value = false
            }
        }
    }
    fun searchResep(query: String? = this.query.value){
        query?.let {
            viewModelScope.launch {
                loading.value = true
                val resp = repo.searchResep(it)
                actionState.value = resp
                searchResep.value = resp.data
                loading.value = false
            }
        }
    }
}