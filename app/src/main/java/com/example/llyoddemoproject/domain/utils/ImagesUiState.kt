package com.example.llyoddemoproject.domain.utils

import com.example.llyoddemoproject.domain.model.Images

data class ImagesUiState(
    val imageList: List<Images>? = emptyList(),
    val imageData: Images = Images(),
    val isLoading: Boolean = false
)
