package com.example.flowerappcopy.domain.usecase

import com.example.flowerappcopy.domain.model.MapaYayoResponse
import com.example.flowerappcopy.domain.repository.MapaYayoRepository
import javax.inject.Inject

class GetMapaYayoUseCase @Inject constructor(
    private val repository: MapaYayoRepository
) {
    suspend operator fun invoke(): MapaYayoResponse {
        val getMapaUnoYayo = repository.getMapaUno()
        return getMapaUnoYayo
    }
}