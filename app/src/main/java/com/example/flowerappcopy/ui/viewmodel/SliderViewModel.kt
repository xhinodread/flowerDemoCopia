package com.example.flowerappcopy.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SliderViewModel: ViewModel() {

    private val _sliderValue = MutableLiveData(1f)
    val sliderValue : LiveData<Float> = _sliderValue

    fun cambioValor(valor: Float){
        viewModelScope.launch {
            _sliderValue.value = valor
        }
    }

}