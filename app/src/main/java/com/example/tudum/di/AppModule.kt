package com.example.tudum.di

import android.app.Application
import androidx.room.Room
import com.example.tudum.data.Database
import com.example.tudum.data.Repo
import com.example.tudum.data.RepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module

@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): Database{
        return Room.databaseBuilder(
            app,
            Database::class.java,
            name = "Todo_db"
        ).build()

    }

    @Provides
    @Singleton
    fun provideRepo(db: Database): Repo{
        return RepoImpl(db.dao)
    }

}