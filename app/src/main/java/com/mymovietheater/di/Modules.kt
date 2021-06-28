package com.mymovietheater.di

import android.content.Context
import com.mymovietheater.data.local.CategoryDao
import com.mymovietheater.data.local.LocalDataBase
import com.mymovietheater.data.remote.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesScopesModule {
    @Singleton
    @Provides
    fun providesCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob())
}

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context, scope: CoroutineScope): LocalDataBase =
        LocalDataBase.getDatabase(context, scope)
    @Singleton
    @Provides
    fun provideCategoryDao(database: LocalDataBase): CategoryDao = database.categoryDao()
}

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideMovieService(): MovieService = MovieService.service
}
