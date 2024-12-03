package com.example.puzzlepictureadventure.database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "game_database.db"
        const val DATABASE_VERSION = 1

        // Table and column names
        const val TABLE_LEVEL = "levels"
        const val COLUMN_LEVEL = "level"
        const val COLUMN_BINTANG = "bintang"
        const val COLUMN_AKSES = "akses"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_LEVEL (
                $COLUMN_LEVEL INTEGER PRIMARY KEY,
                $COLUMN_BINTANG INTEGER DEFAULT 0,
                $COLUMN_AKSES INTEGER DEFAULT 0
            )
        """
        db.execSQL(createTableQuery)

        // Initialize data
        for (i in 1..9) {
            val akses = if (i == 1) 1 else 0
            db.execSQL("INSERT INTO $TABLE_LEVEL ($COLUMN_LEVEL, $COLUMN_BINTANG, $COLUMN_AKSES) VALUES ($i, 0, $akses)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_LEVEL")
        onCreate(db)
    }

    fun getAllLevelData(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_LEVEL", null)
    }
}
