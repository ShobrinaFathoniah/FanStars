package com.shobrinaf.stars.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shobrinaf.stars.core.domain.usecase.StarUseCase

class FavoriteViewModel(starUseCase: StarUseCase) : ViewModel() {
    val favorite = starUseCase.getFavoriteStar().asLiveData()
}