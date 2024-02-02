package com.example.llyoddemoproject.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.llyoddemoproject.domain.model.Images
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.llyoddemoproject.domain.usecase.GetImageDetailUseCase
import com.example.llyoddemoproject.domain.utils.ResultData
import com.example.llyoddemoproject.ui.base.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getImageDetailUseCase: GetImageDetailUseCase,
) : BaseViewModel() {

    private val _showSmartMode = MutableLiveData(true)
    val showSmartMode: LiveData<Boolean> = _showSmartMode

    private val _imageLive = MutableLiveData<Images>()
    val imageLive: LiveData<Images> = _imageLive

    fun getImagesById(newsId: Int) {
        fetchImageDetail(newsId)
    }

    private fun fetchImageDetail(newsId: Int) {
        viewModelScope.launch {
            getImageDetailUseCase.getImageDetail(newsId).onEach { result ->
                when (result) {
                    is ResultData.Loading -> {}
                    is ResultData.Success -> {
                        _imageLive.postValue(result.data ?: Images())
                    }
                    is ResultData.Error -> {}
                }
            }.launchIn(this)
        }
    }

    fun toggleMode() {
        _showSmartMode.value = !_showSmartMode.value!!
    }
}
