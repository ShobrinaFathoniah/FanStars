package com.shobrinaf.stars.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shobrinaf.stars.core.domain.usecase.StarUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val starUseCase: StarUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(starUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}