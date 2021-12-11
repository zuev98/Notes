package com.github.zuev98.notes

/**
 * Интерфейс для [NoteFragment]
 */
interface NoteFragmentView  {
    /**
     * Поделиться заметкой
     *
     * @param heading заголовок заметки
     * @param text текст заметки
     */
    fun shareNote(heading: String, text: String)

    /**
     * Выводит toast о незаполненных полях при попытки поделиться
     */
    fun shareDataFailed()

    /**
     * Сохранить заметку
     *
     *@param text текст заметки
     */
    fun addNotesTextView(text: String)

    /**
     * Выводит toast о незаполненных полях при сохранении
     *
     * @param error error text
     */
    fun getDataFailed(error: String)
}