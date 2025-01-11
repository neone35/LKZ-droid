package com.arturmaslov.lkz_droid.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

class LocalDataSource(
    mLocalDatabase: LocalDatabase,
    private val mDispatcher: CoroutineDispatcher
) : LocalData {

    private val userSettingDao: WordDao? = mLocalDatabase.wordDao

    override suspend fun getAllWords() =
        withContext(mDispatcher) {
            Timber.i("Running getAllWords()")
            val allWordEntities = userSettingDao?.getAllWords()
            val allWords = allWordEntities?.map { it.toDomainModel() }
            if (allWords != null) {
                Timber.i("Success: user settings retrieved: $allWords")
            } else {
                Timber.i("Failure: unable to retrieve all user settings")
            }
            allWords
        }

    override suspend fun insertWord(word: Word) =
        withContext(mDispatcher) {
            Timber.i("Running insertWord()")
            val insertedId = userSettingDao?.insertWord(word.toEntity())
            if (insertedId != null) {
                Timber.i("Success: user setting for ${word.id} inserted")
            } else {
                Timber.i("Failure: unable to insert setting for ${word.id}")
            }
            insertedId
        }

}

interface LocalData {
    suspend fun getAllWords(): List<Word>?
    suspend fun insertWord(word: Word): Long?
}