package com.example.llyoddemoproject.ui.lists


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.llyoddemoproject.domain.usecase.GetImageListUseCase
import com.example.llyoddemoproject.domain.utils.ImagesUiState
import com.example.llyoddemoproject.domain.utils.ResultData
import com.example.llyoddemoproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getImageListUseCase: GetImageListUseCase
) : BaseViewModel() {

    private val _state = mutableStateOf(ImagesUiState())
    val state: State<ImagesUiState> = _state

    init {
        fetchListImages()
    }

    private fun fetchListImages() {
        viewModelScope.launch {
            getImageListUseCase.getImagesList().onEach { result ->
                when (result) {
                    is ResultData.Loading -> {
                        _state.value = state.value.copy(
                            imageList = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is ResultData.Success -> {
                        _state.value = state.value.copy(
                            imageList =  result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is ResultData.Error -> {
                        _state.value = state.value.copy(
                            imageList =  result.data?: emptyList(),
                            isLoading = false
                        )

                    }
                }
            }.launchIn(this)
        }

    }
}