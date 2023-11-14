package com.example.flowerappcopy.network.service

import com.example.flowerappcopy.domain.model.MapaYayoResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface MapaYayoService {

    @GET("getMapaElquiWeb")
    suspend fun getMapaUno(
       @Query("apiKey") key : String,
    ): Result<ResponseBody>

    @GET("getMapaElquiWeb")
    suspend fun getMapaUnoUno(
        @Query("apiKey") key : String,
    ): MapaYayoResponse



}