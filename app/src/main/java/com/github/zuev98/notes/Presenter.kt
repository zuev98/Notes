package com.github.zuev98.notes

interface Presenter {
    //method to be called when the button is clicked
    //check fields for emptiness
    fun onButtonClick(heading: String, note: String)

    //method to destroy lifecycle of NotesActivity
    fun onDestroy()
}