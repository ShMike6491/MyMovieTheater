package com.mymovietheater

import android.app.Application
import com.mymovietheater.data.local.LocalDataBase
import com.mymovietheater.data.remote.MovieService
import com.mymovietheater.data.repositories.Repository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class App : Application()
