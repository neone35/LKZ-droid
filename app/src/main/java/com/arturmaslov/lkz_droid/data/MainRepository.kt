package com.arturmaslov.lkz_droid.data

class MainRepository(
    private val mLocalDataSource: LocalDataSource
) : LocalData {

    override suspend fun getAllWords(): List<Word>? =
        mLocalDataSource.getAllWords()


    override suspend fun insertWord(word: Word): Long? =
        mLocalDataSource.insertWord(word)
}