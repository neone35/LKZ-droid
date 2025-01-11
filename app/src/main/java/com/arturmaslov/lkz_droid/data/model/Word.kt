package com.arturmaslov.lkz_droid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arturmaslov.lkz_droid.utils.Constants.EMPTY_STRING

@Entity
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val word: String,
    val description: String
)

data class Word(
    val id: Int? = null,
    val word: String? = null,
    val description: String? = null
)

fun WordEntity.toDomainModel() = Word(id, word, description)
fun Word.toEntity() = WordEntity(
    id ?: 0,
    word ?: EMPTY_STRING,
    description ?: EMPTY_STRING
)