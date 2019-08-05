package com.example.android.persistence.codelab.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class AppDatabase_Impl extends AppDatabase {
  private volatile UserDao _userDao;

  private volatile BookDao _bookDao;

  private volatile LoanDao _loanDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `User` (`id` TEXT NOT NULL, `name` TEXT, `lastName` TEXT, `age` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Book` (`id` TEXT NOT NULL, `title` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Loan` (`id` TEXT NOT NULL, `startTime` INTEGER, `endTime` INTEGER, `book_id` TEXT, `user_id` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`book_id`) REFERENCES `Book`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`user_id`) REFERENCES `User`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"5f83ccac1f6b4e836fca0611836efb16\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `User`");
        _db.execSQL("DROP TABLE IF EXISTS `Book`");
        _db.execSQL("DROP TABLE IF EXISTS `Loan`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(4);
        _columnsUser.put("id", new TableInfo.Column("id", "TEXT", true, 1));
        _columnsUser.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsUser.put("lastName", new TableInfo.Column("lastName", "TEXT", false, 0));
        _columnsUser.put("age", new TableInfo.Column("age", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("User", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "User");
        if (! _infoUser.equals(_existingUser)) {
          throw new IllegalStateException("Migration didn't properly handle User(com.example.android.persistence.codelab.db.User).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        final HashMap<String, TableInfo.Column> _columnsBook = new HashMap<String, TableInfo.Column>(2);
        _columnsBook.put("id", new TableInfo.Column("id", "TEXT", true, 1));
        _columnsBook.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBook = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBook = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBook = new TableInfo("Book", _columnsBook, _foreignKeysBook, _indicesBook);
        final TableInfo _existingBook = TableInfo.read(_db, "Book");
        if (! _infoBook.equals(_existingBook)) {
          throw new IllegalStateException("Migration didn't properly handle Book(com.example.android.persistence.codelab.db.Book).\n"
                  + " Expected:\n" + _infoBook + "\n"
                  + " Found:\n" + _existingBook);
        }
        final HashMap<String, TableInfo.Column> _columnsLoan = new HashMap<String, TableInfo.Column>(5);
        _columnsLoan.put("id", new TableInfo.Column("id", "TEXT", true, 1));
        _columnsLoan.put("startTime", new TableInfo.Column("startTime", "INTEGER", false, 0));
        _columnsLoan.put("endTime", new TableInfo.Column("endTime", "INTEGER", false, 0));
        _columnsLoan.put("book_id", new TableInfo.Column("book_id", "TEXT", false, 0));
        _columnsLoan.put("user_id", new TableInfo.Column("user_id", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLoan = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysLoan.add(new TableInfo.ForeignKey("Book", "NO ACTION", "NO ACTION",Arrays.asList("book_id"), Arrays.asList("id")));
        _foreignKeysLoan.add(new TableInfo.ForeignKey("User", "NO ACTION", "NO ACTION",Arrays.asList("user_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesLoan = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLoan = new TableInfo("Loan", _columnsLoan, _foreignKeysLoan, _indicesLoan);
        final TableInfo _existingLoan = TableInfo.read(_db, "Loan");
        if (! _infoLoan.equals(_existingLoan)) {
          throw new IllegalStateException("Migration didn't properly handle Loan(com.example.android.persistence.codelab.db.Loan).\n"
                  + " Expected:\n" + _infoLoan + "\n"
                  + " Found:\n" + _existingLoan);
        }
      }
    }, "5f83ccac1f6b4e836fca0611836efb16", "1b144e4ae87f9bb34afa852b09ad3603");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "User","Book","Loan");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `Loan`");
      _db.execSQL("DELETE FROM `User`");
      _db.execSQL("DELETE FROM `Book`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public UserDao userModel() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public BookDao bookModel() {
    if (_bookDao != null) {
      return _bookDao;
    } else {
      synchronized(this) {
        if(_bookDao == null) {
          _bookDao = new BookDao_Impl(this);
        }
        return _bookDao;
      }
    }
  }

  @Override
  public LoanDao loanModel() {
    if (_loanDao != null) {
      return _loanDao;
    } else {
      synchronized(this) {
        if(_loanDao == null) {
          _loanDao = new LoanDao_Impl(this);
        }
        return _loanDao;
      }
    }
  }
}
