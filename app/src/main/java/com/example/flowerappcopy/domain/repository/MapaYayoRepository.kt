package com.example.flowerappcopy.domain.repository

import com.example.flowerappcopy.domain.model.MapaYayoResponse

interface MapaYayoRepository {

    suspend fun getMapaUno(): MapaYayoResponse

}