package com.example.carfax.presentation.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carfax.domain.usecase.GetAllCarsUsecase
import com.example.carfax.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val getAllCarsUsecase: GetAllCarsUsecase
) : ViewModel() {


    private val _state = MutableStateFlow(CarInfoState())
    val state: StateFlow<CarInfoState> = _state
    private var loadingJob: Job? = null

    init {
        loadCategory()
    }

    fun loadCategory() {
        _state.value = state.value.copy(isLoading = true)
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getAllCarsUsecase().collect { result ->

                    withContext(Dispatchers.Main) {
                        when (result) {
                            is Resource.Success -> {
                                _state.value = CarInfoState(
                                    carList = result.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                            is Resource.Error -> {
                                _state.value =
                                    state.value.copy(error = result.message, isLoading = false)
                            }
                            is Resource.Loading -> {
                                _state.value = state.value.copy(isLoading = result.isLoading)
                            }
                        }
                    }
                }
            }
        }
    }


}