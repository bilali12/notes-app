package com.allescoders.notesapp.dao


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.allescoders.notesapp.model.Note


class NoteDAO(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "notesapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "notes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = "CREATE TABLE ${TABLE_NAME} ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_CONTENT text)"
        db?.execSQL(CREATE_TABLE_QUERY)

    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE_QUERY = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(DROP_TABLE_QUERY)
        onCreate(db)
    }

    fun insertNote(note: Note) {
        if (note.title.isNotEmpty() && note.content.isNotEmpty()) {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_TITLE, note.title)
                put(COLUMN_CONTENT, note.content)
            }
            db.insert(TABLE_NAME, null, values)
            db.close()
        } else {
            println("Something is wrong (: ...")
        }
    }


    fun getAllNotes(): List<Note> {
        val listOfNotes = mutableListOf<Note>()
        val db = readableDatabase
        val QUERY = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(QUERY,null)

        while(cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            listOfNotes.add(Note(id, title, content))
        }
        cursor.close()
        db.close()
        return listOfNotes
    }
}