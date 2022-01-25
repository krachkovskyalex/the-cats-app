package com.krachkovsky.thecatsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.krachkovsky.thecatsapp.models.AllCatsPageSource
import com.krachkovsky.thecatsapp.models.AnyCat
import kotlinx.coroutines.flow.*

class CatsViewModel(private val pagingSource: AllCatsPageSource) : ViewModel() {

    val catsList: Flow<PagingData<AnyCat>> =
        newPager()
            .flow
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(): Pager<Int, AnyCat> {
        return Pager(PagingConfig(10)) {
            pagingSource
        }
    }

}