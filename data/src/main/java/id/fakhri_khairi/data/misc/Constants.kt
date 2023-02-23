package id.fakhri_khairi.data.misc

class Constants {

    companion object {
        const val ONE_SECOND = 1 * 1000L
        const val BASE_VIEW_TYPE = 0
        const val MEMBER_ID = "MEMBER_ID"
        const val MEMBER_TYPE = "MEMBER_TYPE"
        const val USERNAME_ADMIN = "admin123"
        const val PASSWORD_ADMIN = "admin123"
    }
}

object DBConstants {
    const val DB_VERSION = 1
    const val DB_NAME = "MEMBERS.DB"
    const val TABLE_NAME = "MEMBER"
    const val COLUMN_ID = "Id"
    const val COLUMN_NAME = "Name"
    const val COLUMN_DATEOFBIRTH = "DateOfBirth"
    const val COLUMN_ADDRESS = "Address"
    const val COLUMN_GENDER = "Gender"
    const val COLUMN_USERNAME = "Username"
    const val COLUMN_PASSWORD = "Password"
    const val SQL_CREATE_ENTRIES = "CREATE TABLE $TABLE_NAME (" +
            "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$COLUMN_NAME TEXT," +
            "$COLUMN_DATEOFBIRTH TEXT," +
            "$COLUMN_ADDRESS TEXT," +
            "$COLUMN_GENDER TEXT," +
            "$COLUMN_USERNAME TEXT," +
            "$COLUMN_PASSWORD TEXT)"
    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
}