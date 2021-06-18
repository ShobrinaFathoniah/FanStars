package com.shobrinaf.stars.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shobrinaf.stars.core.domain.usecase.StarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(starUseCase: StarUseCase) : ViewModel() {
    val stars = starUseCase.getAllStars().asLiveData()
}

