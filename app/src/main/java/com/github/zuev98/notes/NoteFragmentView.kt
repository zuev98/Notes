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
     * Выводит toast о сохранении заметки
     */
    fun showSavedNoteToast()

    /**
     * Выводит toast о незаполненном заголовке при сохранении
     */
    fun onHeadingEmpty()

    /**
     * Выводит toast о незаполненном тексте заметки при сохранении
     */
    fun onNoteEmpty()
}