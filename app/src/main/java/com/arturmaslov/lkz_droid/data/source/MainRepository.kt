package com.arturmaslov.lkz_droid.data.source

import com.arturmaslov.lkz_droid.data.model.Word

class MainRepository(
    private val mLocalDataSource: LocalDataSource
) : LocalData {

    override suspend fun getAllWords(): List<Word>? =
        mLocalDataSource.getAllWords()


    override suspend fun insertWord(word: Word): Long? =
        mLocalDataSource.insertWord(word)
}