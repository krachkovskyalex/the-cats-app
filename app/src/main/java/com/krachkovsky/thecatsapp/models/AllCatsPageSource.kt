package com.krachkovsky.thecatsapp.models

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.krachkovsky.thecatsapp.api.CatsRetrofit
import com.krachkovsky.thecatsapp.util.Constants.INITIAL_PAGE_NUMBER
import com.krachkovsky.thecatsapp.util.Constants.PARAM_PAGE_LIMIT_MAX
import retrofit2.HttpException

class AllCatsPageSource(private val request: CatsRetrofit) : PagingSource<Int, AnyCat>() {

    override fun getRefreshKey(state: PagingState<Int, AnyCat>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnyCat> {
        try {
            val pageNumber: Int = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize: Int = params.loadSize.coerceAtMost(PARAM_PAGE_LIMIT_MAX)
            Log.d("ALEX", "Page size $pageSize")
            val response = request.apiRequest().getCatsList(page = pageNumber, limit = pageSize)

            return if (response.isSuccessful) {
                val cats = checkNotNull(response.body())
                val nextKey = if (cats.size < pageSize) null else pageNumber + 1
                val prevKey = if (pageNumber == 0) null else pageNumber - 1
                LoadResult.Page(cats, prevKey, nextKey)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}