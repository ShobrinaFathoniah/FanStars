package com.shobrinaf.stars.detail

import androidx.lifecycle.ViewModel
import com.shobrinaf.stars.core.domain.model.Stars
import com.shobrinaf.stars.core.domain.usecase.StarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailStarViewModel @Inject constructor(private val starUseCase: StarUseCase) :
    ViewModel() {
    fun setFavoriteStar(star: Stars, newStatus: Boolean) =
        starUseCase.setFavoriteStar(star, newStatus)
}

