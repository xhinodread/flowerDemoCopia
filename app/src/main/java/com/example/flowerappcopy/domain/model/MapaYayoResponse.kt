package com.example.flowerappcopy.domain.model

import com.google.gson.annotations.SerializedName

data class MapaYayoResponse(
    @SerializedName("resp") val resp: RespCoordenadas
)

data class RespCoordenadas(
    val id: String ="",
    val lat: String = "",
    val lon: String = ""
)