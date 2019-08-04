package com.example.tutorial4migratingdatabase.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import static com.example.tutorial4migratingdatabase.db.AppDatabase.DATABASE_VERSION;

/**
 * Workflow to Upgrade db:
 *
 * <li>
 * Increase version of database
 * </li>
 *
 * <li>
 * Create a {@link Migration} file and write 1 or more update queries inside {@link Migration#migrate(SupportSQLiteDatabase)}
 * </li>
 *
 * <li>
 * If new table is added add Table's model class to entities={NewTableModel.class}
 * </li>
 *
 * <li>
 * Add  .addMigrations(FROM_OLD_TO_NEW)
 * </li>
 */

// Entities for Version 1
//@Database(entities = {User.class}, version = DATABASE_VERSION)
// Entities for Version 2
@Database(entities = {User.class, Student.class}, version = DATABASE_VERSION)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "database_sample.db";
    public static final int DATABASE_VERSION = 2;


    private static AppDatabase INSTANCE;

    private static final Object sLock = new Object();


    public abstract UserDao userDao();

    public abstract StudentDao studentDao();


    public static AppDatabase getInstance(Context context) {

        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, DATABASE_NAME)
                        // Don't perform ops on main thread
                        .allowMainThreadQueries()
                        .addMigrations(MIGRATION_1_TO_2)
                        .build();
            }

            return INSTANCE;
        }
    }

    public static final Migration MIGRATION_1_TO_2 = new Migration(1, 2) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {

            database.execSQL("DROP TABLE IF EXISTS students");

            // Retrieved from schema file
            String statement = "CREATE TABLE IF NOT EXISTS students (uid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, student_name TEXT, student_number INTEGER NOT NULL)";
            database.execSQL(statement);

        }
    };

}

