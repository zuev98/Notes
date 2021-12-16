package com.github.zuev98.notes

import java.util.*

/**
 * Presenter для [NoteListFragment]
 *
 * @param noteListFragmentView view, к которой можно получить доступ через интерфейс
 */
class NoteListFragmentPresenter(private var noteListFragmentView: NoteListFragmentView?) {
    /**
     * Метод для получения mock-данных списка заметок
     */
    fun getMockData() {
        val notes = NoteRepository.getMockNotes()
        noteListFragmentView?.updateUI(notes)
    }

    /**
     * Обработка нажатия на заметку из списка
     *
     * @param noteID идентификатор заметки
     */
    fun onNoteClicked(noteID: UUID) {
        noteListFragmentView?.pushFragment(noteID)
    }
}