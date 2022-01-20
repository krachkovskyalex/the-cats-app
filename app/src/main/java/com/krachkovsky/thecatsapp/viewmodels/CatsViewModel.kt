package com.krachkovsky.thecatsapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krachkovsky.thecatsapp.api.CatsRetrofit
import com.krachkovsky.thecatsapp.db.CatsDatabase
import com.krachkovsky.thecatsapp.util.Constants.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatsViewModel(private val request: CatsRetrofit, application: Application) : ViewModel() {

    private val db = CatsDatabase.getInstance(context = application)
    val catsList = db.favoriteCatsDao().getAll()

    private var currentPage = 0

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            getCatsList()
        }
    }

    private suspend fun getCatsList() {
        try {
            val response = request.apiRequest().getCatsList(page = currentPage)
            if (response.isSuccessful) {
                val result = response.body()
                if (result?.isNotEmpty() == true) {
                    result.let { db.favoriteCatsDao().insertAllCats(it) }
                    currentPage++
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Response is not successful -> ${e.message}")
        }
    }
}