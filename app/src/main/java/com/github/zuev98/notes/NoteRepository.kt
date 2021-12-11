package com.github.zuev98.notes

import java.util.*

/**
 * Репозиторий с mock-данными
 */
object NoteRepository {
    val notes = mutableListOf<Note>()

    init {
        for (i in 1..20) {
            val note = Note()
            note.heading = "heading$i"
            note.text = "text$i\n\n".repeat(i)
            notes += note
        }
    }

    /**
     * Метод возвращает заметку с нужным id или пустую
     *
     * @param noteID идентификатор заметки
     */
    fun getNote(noteID: UUID): Note {
        return notes.find { it.id == noteID } ?: Note()
    }

    /**
     * Метод возвращает mock-данные
     */
    fun getMockNotes(): MutableList<Note> {
        return notes
    }
}