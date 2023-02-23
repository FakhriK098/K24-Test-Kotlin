package id.fakhri_khairi.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import id.fakhri_khairi.data.misc.DBConstants

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DBConstants.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DBConstants.SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}