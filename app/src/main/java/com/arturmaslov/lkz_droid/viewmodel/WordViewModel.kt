package com.arturmaslov.lkz_droid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arturmaslov.lkz_droid.data.model.Word
import com.arturmaslov.lkz_droid.data.source.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WordViewModel(private val repository: MainRepository) : ViewModel() {

    private val savedWords = MutableStateFlow<List<Word>>(emptyList())

    init {
        getAllWords()
    }

    fun saveWord(
        word: String,
        description: String,
    ) {
        viewModelScope.launch {
            repository.insertWord(
                Word(
                    word = word,
                    description = description
                )
            )
            getAllWords()
        }
    }

    fun getAllWords() {
        viewModelScope.launch {
            savedWords.value = repository.getAllWords() ?: emptyList()
        }
    }

    fun savedWords() = savedWords

}