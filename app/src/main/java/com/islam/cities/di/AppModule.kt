package com.islam.cities.di

import android.content.Context
import com.islam.cities.data.repository.Repository
import com.islam.cities.data.repository.RepositoryImp
import com.islam.cities.utils.Parser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun getRepository(parser: Parser):Repository{
        return RepositoryImp(parser)
    }
    @Provides
    fun getParser(@ApplicationContext context:Context):Parser{
        return Parser(context)
    }

}