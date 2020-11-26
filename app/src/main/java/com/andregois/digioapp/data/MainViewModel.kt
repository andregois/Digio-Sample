package com.andregois.digioapp.data

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andregois.digioapp.domain.DigioResponse
import com.andregois.digioapp.utils.Result
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val digioRepositoryImpl: DigioRepository
) : ViewModel() {

    private val _response = MutableLiveData<Result<DigioResponse>>()
    val response: LiveData<Result<DigioResponse>> = _response


    init {
        _response.postValue(Result.loading(null))
        fetchData()
    }


    fun fetchData() {
        viewModelScope.launch {
            try {
                _response.value = Result.success(digioRepositoryImpl.getApiData())
            } catch (exception: Exception) {
                _response.value = Result.error(
                    data = null,
                    message = exception.message ?: "Error Occurred!"
                )
            }
        }
    }
}