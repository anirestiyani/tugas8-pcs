package resti.example.appmemori.data.model

data class ResepList(
    val method: String = "",
    val status: String = "",
    val results: List<Resep> = arrayListOf()
)