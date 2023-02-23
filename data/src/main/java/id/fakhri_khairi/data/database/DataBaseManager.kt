package id.fakhri_khairi.data.database

import android.content.ContentValues
import android.content.Context
import id.fakhri_khairi.data.misc.DBConstants
import id.fakhri_khairi.data.misc.convertToHash
import id.fakhri_khairi.domain.Member

class DataBaseManager(context: Context) {
    val dbHelper = DatabaseHelper(context)

    fun insert(member: Member) : Long {
        val db = dbHelper.writableDatabase
        val value = ContentValues().apply {
            put(DBConstants.COLUMN_NAME, member.name)
            put(DBConstants.COLUMN_DATEOFBIRTH, member.dateOfBirth)
            put(DBConstants.COLUMN_ADDRESS, member.address)
            put(DBConstants.COLUMN_GENDER, member.gender)
            put(DBConstants.COLUMN_USERNAME, member.username)
            put(DBConstants.COLUMN_PASSWORD, member.password.convertToHash())
        }
        return db.insert(DBConstants.TABLE_NAME, null, value)
    }

    fun fetchAll(): List<Member> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DBConstants.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        val memberResult: MutableList<Member> = mutableListOf()
        with(cursor) {
            while (moveToNext()) {
                val member = Member(
                    id = getInt(getColumnIndexOrThrow(DBConstants.COLUMN_ID)),
                    name = getString(getColumnIndexOrThrow(DBConstants.COLUMN_NAME)),
                    dateOfBirth = getString(getColumnIndexOrThrow(DBConstants.COLUMN_DATEOFBIRTH)),
                    address = getString(getColumnIndexOrThrow(DBConstants.COLUMN_ADDRESS)),
                    gender = getString(getColumnIndexOrThrow(DBConstants.COLUMN_GENDER)),
                    username = getString(getColumnIndexOrThrow(DBConstants.COLUMN_USERNAME)),
                    password = getString(getColumnIndexOrThrow(DBConstants.COLUMN_PASSWORD))
                )

                memberResult.add(member)
            }
        }
        cursor.close()
        return memberResult
    }

    fun fetchDetail(id: Int): Member {
        val db = dbHelper.readableDatabase

        val selection = "${DBConstants.COLUMN_ID} = ?"
        val selectionArgs = arrayOf("$id")
        val cursor = db.query(
            DBConstants.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var member: Member = Member()
        with(cursor) {
            while (moveToNext()) {
                member = Member(
                    id = getInt(getColumnIndexOrThrow(DBConstants.COLUMN_ID)),
                    name = getString(getColumnIndexOrThrow(DBConstants.COLUMN_NAME)),
                    dateOfBirth = getString(getColumnIndexOrThrow(DBConstants.COLUMN_DATEOFBIRTH)),
                    address = getString(getColumnIndexOrThrow(DBConstants.COLUMN_ADDRESS)),
                    gender = getString(getColumnIndexOrThrow(DBConstants.COLUMN_GENDER)),
                    username = getString(getColumnIndexOrThrow(DBConstants.COLUMN_USERNAME)),
                    password = getString(getColumnIndexOrThrow(DBConstants.COLUMN_PASSWORD))
                )
            }
        }
        cursor.close()
        return member
    }

    fun fetchByName(name: String): List<Member> {
        val db = dbHelper.readableDatabase

        val selection = "${DBConstants.COLUMN_NAME} LIKE ?"
        val selectionArgs = arrayOf(name)
        val cursor = db.query(
            DBConstants.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val members: MutableList<Member> = mutableListOf()
        with(cursor) {
            while (moveToNext()) {
                val member = Member(
                    id = getInt(getColumnIndexOrThrow(DBConstants.COLUMN_ID)),
                    name = getString(getColumnIndexOrThrow(DBConstants.COLUMN_NAME)),
                    dateOfBirth = getString(getColumnIndexOrThrow(DBConstants.COLUMN_DATEOFBIRTH)),
                    address = getString(getColumnIndexOrThrow(DBConstants.COLUMN_ADDRESS)),
                    gender = getString(getColumnIndexOrThrow(DBConstants.COLUMN_GENDER)),
                    username = getString(getColumnIndexOrThrow(DBConstants.COLUMN_USERNAME)),
                    password = getString(getColumnIndexOrThrow(DBConstants.COLUMN_PASSWORD))
                )
                members.add(member)
            }
        }
        cursor.close()
        return members
    }

    fun fetchByUsernameAndPassword(username: String, password: String): Member {
        val db = dbHelper.readableDatabase

        val selection = "${DBConstants.COLUMN_USERNAME} = ? AND ${DBConstants.COLUMN_PASSWORD} = ?"
        val selectionArgs = arrayOf(username, password.convertToHash())
        val cursor = db.query(
            DBConstants.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var member: Member = Member()
        with(cursor) {
            while (moveToNext()) {
                member = Member(
                    id = getInt(getColumnIndexOrThrow(DBConstants.COLUMN_ID)),
                    name = getString(getColumnIndexOrThrow(DBConstants.COLUMN_NAME)),
                    dateOfBirth = getString(getColumnIndexOrThrow(DBConstants.COLUMN_DATEOFBIRTH)),
                    address = getString(getColumnIndexOrThrow(DBConstants.COLUMN_ADDRESS)),
                    gender = getString(getColumnIndexOrThrow(DBConstants.COLUMN_GENDER)),
                    username = getString(getColumnIndexOrThrow(DBConstants.COLUMN_USERNAME)),
                    password = getString(getColumnIndexOrThrow(DBConstants.COLUMN_PASSWORD))
                )
            }
        }
        cursor.close()
        return member
    }

    fun updatePassword(id: Int, newPassword: String) : Int {
        val db = dbHelper.writableDatabase
        val value = ContentValues().apply {
            put(DBConstants.COLUMN_PASSWORD, newPassword)
        }

        val selection = "${DBConstants.COLUMN_ID} LIKE ?"
        val selectionArgs = arrayOf("$id")
        return db.update(
            DBConstants.TABLE_NAME,
            value,
            selection,
            selectionArgs
        )
    }

    companion object {
        val projection = arrayOf(
            DBConstants.COLUMN_ID,
            DBConstants.COLUMN_NAME,
            DBConstants.COLUMN_DATEOFBIRTH,
            DBConstants.COLUMN_ADDRESS,
            DBConstants.COLUMN_GENDER,
            DBConstants.COLUMN_USERNAME,
            DBConstants.COLUMN_PASSWORD
        )
    }
}