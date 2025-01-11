package com.arturmaslov.lkz_droid.di

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import com.arturmaslov.lkz_droid.data.source.LocalDatabase
import com.arturmaslov.lkz_droid.data.source.LocalDataSource
import com.arturmaslov.lkz_droid.data.source.MainRepository

val repoModule = module {
    single { provideLocalDatabase(androidApplication()) }
    single { provideLocalDataSource(get()) }
    single { provideMainRepository(get()) }
}

fun provideLocalDatabase(app: Application): LocalDatabase {
    val dbName = LocalDatabase.DATABASE_NAME
    return Room.databaseBuilder(
        app,
        LocalDatabase::class.java, dbName
    )
        .fallbackToDestructiveMigration()
        .build()
}

private fun provideLocalDataSource(
    localDB: LocalDatabase
): LocalDataSource {
    return LocalDataSource(localDB, Dispatchers.IO)
}

private fun provideMainRepository(
    localDataSource: LocalDataSource
): MainRepository {
    return MainRepository(localDataSource)
}