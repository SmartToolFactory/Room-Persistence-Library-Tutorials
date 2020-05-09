package com.example.tutorial4migratingdatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Workflow to Upgrade db:
 *
 *  *
 * Increase version of database
 *
 *
 *  *
 * Create a [Migration] file and write 1 or more update queries inside [Migration.migrate]
 *
 *
 *  *
 * If new table is added add Table's model class to entities={NewTableModel.class}
 *
 *
 *  *
 * Add  .addMigrations(FROM_OLD_TO_NEW)
 *
 */
// Entities for Version 1
//@Database(entities = {User.class}, version = DATABASE_VERSION)
// Entities for Version 2
@Database(
    entities = [User::class, Student::class],
    version = AppDatabase.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun studentDao(): StudentDao

    companion object {
        const val DATABASE_NAME = "database_sample.db"
        const val DATABASE_VERSION = 2
        private var INSTANCE: AppDatabase? = null
        private val sLock = Any()
        @JvmStatic
        fun getInstance(context: Context): AppDatabase? {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            DATABASE_NAME
                        ) // Don't perform ops on main thread
                            .allowMainThreadQueries()
                            .addMigrations(MIGRATION_1_TO_2)
                            .build()
                }
                return INSTANCE
            }
        }

        val MIGRATION_1_TO_2: Migration =
            object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("DROP TABLE IF EXISTS students")

                    // Retrieved from schema file
                    val statement =
                        "CREATE TABLE IF NOT EXISTS students (uid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, student_name TEXT, student_number INTEGER NOT NULL)"
                    database.execSQL(statement)
                }
            }
    }
}