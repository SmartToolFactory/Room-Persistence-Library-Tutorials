package com.example.android.persistence.codelab.db;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfUser;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUsersByName;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `User`(`id`,`name`,`lastName`,`age`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        if (value.id == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.id);
        }
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        if (value.lastName == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.lastName);
        }
        stmt.bindLong(4, value.age);
      }
    };
    this.__deletionAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `User` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        if (value.id == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.id);
        }
      }
    };
    this.__preparedStmtOfDeleteUsersByName = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from user where name like ? OR lastName like ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM User";
        return _query;
      }
    };
  }

  @Override
  public void insertUser(User user) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertOrReplaceUsers(User... users) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteUser(User user) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfUser.handle(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteUsers(User user1, User user2) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfUser.handle(user1);
      __deletionAdapterOfUser.handle(user2);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteUsersByName(String badName) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUsersByName.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (badName == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, badName);
      }
      _argIndex = 2;
      if (badName == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, badName);
      }
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteUsersByName.release(_stmt);
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<User> loadAllUsers() {
    final String _sql = "select * from user";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfLastName = _cursor.getColumnIndexOrThrow("lastName");
      final int _cursorIndexOfAge = _cursor.getColumnIndexOrThrow("age");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        _item = new User();
        _item.id = _cursor.getString(_cursorIndexOfId);
        _item.name = _cursor.getString(_cursorIndexOfName);
        _item.lastName = _cursor.getString(_cursorIndexOfLastName);
        _item.age = _cursor.getInt(_cursorIndexOfAge);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User loadUserById(int id) {
    final String _sql = "select * from user where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfLastName = _cursor.getColumnIndexOrThrow("lastName");
      final int _cursorIndexOfAge = _cursor.getColumnIndexOrThrow("age");
      final User _result;
      if(_cursor.moveToFirst()) {
        _result = new User();
        _result.id = _cursor.getString(_cursorIndexOfId);
        _result.name = _cursor.getString(_cursorIndexOfName);
        _result.lastName = _cursor.getString(_cursorIndexOfLastName);
        _result.age = _cursor.getInt(_cursorIndexOfAge);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<User> findUserByNameAndLastName(String firstName, String lastName) {
    final String _sql = "select * from user where name = ? and lastName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (firstName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, firstName);
    }
    _argIndex = 2;
    if (lastName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, lastName);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfLastName = _cursor.getColumnIndexOrThrow("lastName");
      final int _cursorIndexOfAge = _cursor.getColumnIndexOrThrow("age");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        _item = new User();
        _item.id = _cursor.getString(_cursorIndexOfId);
        _item.name = _cursor.getString(_cursorIndexOfName);
        _item.lastName = _cursor.getString(_cursorIndexOfLastName);
        _item.age = _cursor.getInt(_cursorIndexOfAge);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<User> findUsersYoungerThan(int age) {
    final String _sql = "SELECT * FROM User WHERE ? == ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, age);
    _argIndex = 2;
    _statement.bindLong(_argIndex, age);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfLastName = _cursor.getColumnIndexOrThrow("lastName");
      final int _cursorIndexOfAge = _cursor.getColumnIndexOrThrow("age");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        _item = new User();
        _item.id = _cursor.getString(_cursorIndexOfId);
        _item.name = _cursor.getString(_cursorIndexOfName);
        _item.lastName = _cursor.getString(_cursorIndexOfLastName);
        _item.age = _cursor.getInt(_cursorIndexOfAge);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<User> findUsersYoungerThanSolution(int age) {
    final String _sql = "SELECT * FROM User WHERE age < ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, age);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfLastName = _cursor.getColumnIndexOrThrow("lastName");
      final int _cursorIndexOfAge = _cursor.getColumnIndexOrThrow("age");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        _item = new User();
        _item.id = _cursor.getString(_cursorIndexOfId);
        _item.name = _cursor.getString(_cursorIndexOfName);
        _item.lastName = _cursor.getString(_cursorIndexOfLastName);
        _item.age = _cursor.getInt(_cursorIndexOfAge);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
