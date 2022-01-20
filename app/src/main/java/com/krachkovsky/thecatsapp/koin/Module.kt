package com.krachkovsky.thecatsapp.koin

import com.krachkovsky.thecatsapp.api.CatsRetrofit
import com.krachkovsky.thecatsapp.viewmodels.CatsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { CatsRetrofit() }
    viewModel { CatsViewModel(get(), get()) }

}