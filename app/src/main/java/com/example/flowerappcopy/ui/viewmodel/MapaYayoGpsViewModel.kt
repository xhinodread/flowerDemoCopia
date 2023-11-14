package com.example.flowerappcopy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerappcopy.domain.usecase.GetMapaYayoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapaYayoGpsViewModel @Inject constructor(
    private val getMapaYayoUseCase: GetMapaYayoUseCase
) : ViewModel() {

    private val _cargando = MutableLiveData<Boolean>(false)
    val cargando : LiveData<Boolean> =_cargando

    private val _mapaYayo = MutableLiveData<MapaYayo>()
    val mapaYayo: LiveData<MapaYayo> = _mapaYayo

    fun getMapaUno(){
        viewModelScope.launch {
            _cargando.value = true
            val getMapaUno = getMapaYayoUseCase.invoke()
            val resp = getMapaUno.resp
            Log.d("CLick resp vwmdl", resp.toString())
            _mapaYayo.postValue(MapaYayo(resp.id, resp.lat, resp.lon) )
            _cargando.value = false
        }
    }

}

data class MapaYayo(
     val id:String="",
     val lat: String = "",
     val lon: String = ""
)