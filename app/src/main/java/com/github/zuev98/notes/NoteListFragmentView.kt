package com.github.zuev98.notes

import java.util.*

/**
 * Интерфейс для [NoteListFragment]
 */
interface NoteListFragmentView {
    /**
     * Метод для отображения mock-данных
     *
     * @param mockData mock-данные
     */
    fun updateUI(mockData: List<Note>)

    /**
     * callback при выборе заметки из списка
     *
     * @param noteId идентификатор заметки
     */
    fun callback(noteId: UUID)
}