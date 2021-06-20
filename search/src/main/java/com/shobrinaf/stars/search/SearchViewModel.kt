package com.shobrinaf.stars.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shobrinaf.stars.core.data.Resource
import com.shobrinaf.stars.core.domain.model.Stars
import com.shobrinaf.stars.core.domain.usecase.StarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val starUseCase: StarUseCase) : ViewModel() {
    fun search(query: String): LiveData<Resource<List<Stars>>> =
        starUseCase.getSearch(query).asLiveData()
}

