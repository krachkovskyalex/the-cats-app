package com.krachkovsky.thecatsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.krachkovsky.thecatsapp.AllCatsPageSource
import com.krachkovsky.thecatsapp.FavoriteCatsPageSource
import com.krachkovsky.thecatsapp.db.CatsDatabase
import com.krachkovsky.thecatsapp.models.AnyCat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CatsViewModel(
    private val pagingAllCats: AllCatsPageSource,
    private val pagingFavoriteCats: FavoriteCatsPageSource,
    private val db: CatsDatabase
) : ViewModel() {

    val catsList: Flow<PagingData<AnyCat>> =
        allCatsPager()
            .flow
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    val favoriteCatsList: Flow<PagingData<AnyCat>> =
        favoriteCatsPager()
            .flow
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun allCatsPager(): Pager<Int, AnyCat> {
        return Pager(PagingConfig(10)) {
            pagingAllCats
        }
    }

    private fun favoriteCatsPager(): Pager<Int, AnyCat> {
        return Pager(PagingConfig(10)) {
            pagingFavoriteCats
        }
    }

    fun saveFavoriteCat(cat: AnyCat) {
        viewModelScope.launch(Dispatchers.IO) { db.catsDao().insertCat(cat) }
    }

    fun deleteFavoriteCat(cat: AnyCat) {
        viewModelScope.launch(Dispatchers.IO) { db.catsDao().deleteCat(cat) }
    }
}