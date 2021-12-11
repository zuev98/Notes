package com.github.zuev98.notes

/**
 * Presenter для [NotesActivity]
 *
 * @param notesView view, к которой можно получить доступ через интерфейс
 */
class NotesPresenter(private var notesView: NoteView?) {
    /**
     * Обработка нажатия на кнопку "About"
     */
    fun onAboutItemSelected() {
        notesView?.openAboutScreen()
    }

    /**
     * Метод для уничтожения жизненного цикла [NotesActivity]
     */
    fun onDestroy() {
        notesView = null
    }
}