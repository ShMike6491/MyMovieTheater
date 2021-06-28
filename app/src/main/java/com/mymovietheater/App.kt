package com.mymovietheater

import android.app.Application
import com.mymovietheater.data.local.LocalDataBase
import com.mymovietheater.data.remote.MovieService
import com.mymovietheater.data.repositories.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {
    private val appScope = CoroutineScope(SupervisorJob())

    val dataBase by lazy { LocalDataBase.getDatabase(this, appScope) }
    val repository by lazy { Repository(dataBase.categoryDao(), MovieService.service) }
}