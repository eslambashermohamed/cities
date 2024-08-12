package com.islam.cities.di

import android.content.Context
import com.islam.cities.data.repository.Repository
import com.islam.cities.data.repository.RepositoryImp
import com.islam.cities.domain.GetCitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun getRepository(@ApplicationContext context:Context):Repository{
        return RepositoryImp(context)
    }

    @Provides
    fun getCitiesUseCase(repository: Repository):GetCitiesUseCase{
        return GetCitiesUseCase(repository)
    }

}