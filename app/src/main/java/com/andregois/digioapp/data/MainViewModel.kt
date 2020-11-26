package com.andregois.digioapp.data

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andregois.digioapp.domain.DigioResponse
import com.andregois.digioapp.utils.ApiResult
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val digioRepository: DigioRepository
) : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private val _response = MutableLiveData<ApiResult<DigioResponse>>()
    val response: LiveData<ApiResult<DigioResponse>> = _response

    init {
        _response.postValue(ApiResult.loading(null))
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            _response.value = digioRepository.getApiData()
        }
    }
}