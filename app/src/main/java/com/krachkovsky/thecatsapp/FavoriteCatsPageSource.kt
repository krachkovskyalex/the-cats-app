package com.krachkovsky.thecatsapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.krachkovsky.thecatsapp.db.CatsDatabase
import com.krachkovsky.thecatsapp.models.AnyCat
import com.krachkovsky.thecatsapp.util.Constants.INITIAL_PAGE_NUMBER
import com.krachkovsky.thecatsapp.util.Constants.PARAM_PAGE_LIMIT_MAX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class FavoriteCatsPageSource(private val db: CatsDatabase) : PagingSource<Int, AnyCat>() {

    override fun getRefreshKey(state: PagingState<Int, AnyCat>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnyCat> {
        try {
            val pageNumber: Int = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize: Int = params.loadSize.coerceAtMost(PARAM_PAGE_LIMIT_MAX)

            val catsList = coroutineScope {
                withContext(Dispatchers.IO) {
                    db.catsDao().getAll()
                }
            }

            return if (catsList.isNotEmpty()) {
                val nextKey = if (catsList.size < pageSize) null else pageNumber + 1
                val prevKey = if (pageNumber == 0) null else pageNumber - 1
                LoadResult.Page(catsList, prevKey, nextKey)
            } else {
                error("Database is empty")
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}