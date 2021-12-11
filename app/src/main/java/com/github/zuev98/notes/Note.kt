package com.github.zuev98.notes

import java.util.*

/**
 * Data-класс заметки
 *
 * @param id идентификатор заметки
 * @param heading заголовок заметки
 * @param text текст заметки
 * @param date дата созадния/изменения заметки
 */
data class Note(val id: UUID = UUID.randomUUID(),
                var heading: String = "",
                var text: String = "",
                val date: Date = Date())
