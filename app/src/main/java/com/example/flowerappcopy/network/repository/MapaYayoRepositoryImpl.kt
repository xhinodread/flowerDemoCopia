package com.example.flowerappcopy.network.repository

import android.util.Log
import com.example.flowerappcopy.domain.model.MapaYayoResponse
import com.example.flowerappcopy.domain.repository.MapaYayoRepository
import com.example.flowerappcopy.network.service.MapaYayoService
import javax.inject.Inject

class MapaYayoRepositoryImpl @Inject constructor(
    private val mapaYayoService: MapaYayoService,
    private val apiKey2: String,
    ) : MapaYayoRepository {

    override suspend fun getMapaUno(): MapaYayoResponse {
        val responseApi = mapaYayoService.getMapaUnoUno(apiKey2)
        //Log.d("CLick vwmdl", responseApi.toString())
        return responseApi
    }

}