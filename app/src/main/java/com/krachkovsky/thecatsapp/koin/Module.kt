package com.krachkovsky.thecatsapp.koin

import com.krachkovsky.thecatsapp.api.CatsRetrofit
import com.krachkovsky.thecatsapp.db.CatsDatabase
import com.krachkovsky.thecatsapp.AllCatsPageSource
import com.krachkovsky.thecatsapp.FavoriteCatsPageSource
import com.krachkovsky.thecatsapp.viewmodels.CatsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { CatsRetrofit() }
    single { CatsDatabase.getInstance(get()) }
}

val pageSourceModule = module {
    factory { AllCatsPageSource(get()) }
    factory { FavoriteCatsPageSource(get()) }
}

val viewModelModule = module {
    viewModel { CatsViewModel(get(), get(), get()) }
}