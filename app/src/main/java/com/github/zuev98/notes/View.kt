package com.github.zuev98.notes

/**
 * Interface for [NotesActivity]
 */
interface View {
    /**
     *Save note text
     *
     *@param text note text
     */
    fun addNotesTextView(text: String)

    /**
     * Show error toast about empty data on save
     *
     * @param error error text
     */
    fun getDataFailed(error: String)

    /**
     * Open screen with [AboutActivity]
     */
    fun openAboutScreen()

    /**
     * Share notes
     *
     * @param text notes text
     */
    fun shareNotes(text: String)

    /**
     *Show error toast about empty data when share
     */
    fun shareDataFailed()
}