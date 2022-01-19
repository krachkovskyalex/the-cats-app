package com.krachkovsky.thecatsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.krachkovsky.thecatsapp.api.CatsRetrofit
import com.krachkovsky.thecatsapp.util.Constants.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val request = CatsRetrofit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            getCatsList()
        }

    }

    private suspend fun getCatsList() = withContext(Dispatchers.IO) {
        try {
            val response = request.apiRequest().getCatsList()
            if (response.isSuccessful) {
                Log.d(TAG, response.body().toString())
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Response is not successful -> ${e.message}")
        }
    }
}