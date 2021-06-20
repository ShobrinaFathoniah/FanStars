package com.shobrinaf.stars.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shobrinaf.stars.core.data.Resource
import com.shobrinaf.stars.core.domain.model.Stars
import com.shobrinaf.stars.core.domain.usecase.StarUseCase

class SearchViewModel(val starUseCase: StarUseCase) : ViewModel() {
    fun search(query: String): LiveData<Resource<List<Stars>>> =
        starUseCase.getSearch(query).asLiveData()
}