package com.example.android.persistence.codelab.db;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class BookDao_Impl implements BookDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfBook;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfBook;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public BookDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBook = new EntityInsertionAdapter<Book>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Book`(`id`,`title`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Book value) {
        if (value.id == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.id);
        }
        if (value.title == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.title);
        }
      }
    };
    this.__updateAdapterOfBook = new EntityDeletionOrUpdateAdapter<Book>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `Book` SET `id` = ?,`title` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Book value) {
        if (value.id == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.id);
        }
        if (value.title == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.title);
        }
        if (value.id == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.id);
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Book";
        return _query;
      }
    };
  }

  @Override
  public void insertBook(Book book) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfBook.insert(book);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateBook(Book book) {
    __db.beginTransaction();
    try {
      __updateAdapterOfBook.handle(book);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
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
  public Book loadBookById(int id) {
    final String _sql = "select * from Book where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final Book _result;
      if(_cursor.moveToFirst()) {
        _result = new Book();
        _result.id = _cursor.getString(_cursorIndexOfId);
        _result.title = _cursor.getString(_cursorIndexOfTitle);
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
  public LiveData<List<Book>> findBooksBorrowedByName(String userName) {
    final String _sql = "SELECT * FROM Book INNER JOIN Loan ON Loan.book_id = Book.id INNER JOIN User on User.id = Loan.user_id WHERE User.name LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userName);
    }
    return new ComputableLiveData<List<Book>>() {
      private Observer _observer;

      @Override
      protected List<Book> compute() {
        if (_observer == null) {
          _observer = new Observer("Book","Loan","User") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfId_2 = _cursor.getColumnIndexOrThrow("id");
          final List<Book> _result = new ArrayList<Book>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Book _item;
            _item = new Book();
            _item.id = _cursor.getString(_cursorIndexOfId);
            _item.title = _cursor.getString(_cursorIndexOfTitle);
            _item.id = _cursor.getString(_cursorIndexOfId_1);
            _item.id = _cursor.getString(_cursorIndexOfId_2);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<Book>> findBooksBorrowedByNameAfter(String userName, Date after) {
    final String _sql = "SELECT * FROM Book INNER JOIN Loan ON Loan.book_id = Book.id INNER JOIN User on User.id = Loan.user_id WHERE User.name LIKE ? AND Loan.endTime > ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (userName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userName);
    }
    _argIndex = 2;
    final Long _tmp;
    _tmp = DateConverter.toTimestamp(after);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    return new ComputableLiveData<List<Book>>() {
      private Observer _observer;

      @Override
      protected List<Book> compute() {
        if (_observer == null) {
          _observer = new Observer("Book","Loan","User") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfId_2 = _cursor.getColumnIndexOrThrow("id");
          final List<Book> _result = new ArrayList<Book>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Book _item;
            _item = new Book();
            _item.id = _cursor.getString(_cursorIndexOfId);
            _item.title = _cursor.getString(_cursorIndexOfTitle);
            _item.id = _cursor.getString(_cursorIndexOfId_1);
            _item.id = _cursor.getString(_cursorIndexOfId_2);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public List<Book> findBooksBorrowedByNameSync(String userName) {
    final String _sql = "SELECT * FROM Book INNER JOIN Loan ON Loan.book_id = Book.id INNER JOIN User on User.id = Loan.user_id WHERE User.name LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userName);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfId_2 = _cursor.getColumnIndexOrThrow("id");
      final List<Book> _result = new ArrayList<Book>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Book _item;
        _item = new Book();
        _item.id = _cursor.getString(_cursorIndexOfId);
        _item.title = _cursor.getString(_cursorIndexOfTitle);
        _item.id = _cursor.getString(_cursorIndexOfId_1);
        _item.id = _cursor.getString(_cursorIndexOfId_2);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Book>> findBooksBorrowedByUser(String userId) {
    final String _sql = "SELECT * FROM Book INNER JOIN Loan ON Loan.book_id LIKE Book.id WHERE Loan.user_id LIKE ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return new ComputableLiveData<List<Book>>() {
      private Observer _observer;

      @Override
      protected List<Book> compute() {
        if (_observer == null) {
          _observer = new Observer("Book","Loan") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("id");
          final List<Book> _result = new ArrayList<Book>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Book _item;
            _item = new Book();
            _item.id = _cursor.getString(_cursorIndexOfId);
            _item.title = _cursor.getString(_cursorIndexOfTitle);
            _item.id = _cursor.getString(_cursorIndexOfId_1);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<Book>> findBooksBorrowedByUserAfter(String userId, Date after) {
    final String _sql = "SELECT * FROM Book INNER JOIN Loan ON Loan.book_id LIKE Book.id WHERE Loan.user_id LIKE ? AND Loan.endTime > ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    _argIndex = 2;
    final Long _tmp;
    _tmp = DateConverter.toTimestamp(after);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    return new ComputableLiveData<List<Book>>() {
      private Observer _observer;

      @Override
      protected List<Book> compute() {
        if (_observer == null) {
          _observer = new Observer("Book","Loan") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("id");
          final List<Book> _result = new ArrayList<Book>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Book _item;
            _item = new Book();
            _item.id = _cursor.getString(_cursorIndexOfId);
            _item.title = _cursor.getString(_cursorIndexOfTitle);
            _item.id = _cursor.getString(_cursorIndexOfId_1);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public List<Book> findBooksBorrowedByUserSync(String userId) {
    final String _sql = "SELECT * FROM Book INNER JOIN Loan ON Loan.book_id LIKE Book.id WHERE Loan.user_id LIKE ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("id");
      final List<Book> _result = new ArrayList<Book>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Book _item;
        _item = new Book();
        _item.id = _cursor.getString(_cursorIndexOfId);
        _item.title = _cursor.getString(_cursorIndexOfTitle);
        _item.id = _cursor.getString(_cursorIndexOfId_1);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Book>> findAllBooks() {
    final String _sql = "SELECT * FROM Book";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Book>>() {
      private Observer _observer;

      @Override
      protected List<Book> compute() {
        if (_observer == null) {
          _observer = new Observer("Book") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final List<Book> _result = new ArrayList<Book>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Book _item;
            _item = new Book();
            _item.id = _cursor.getString(_cursorIndexOfId);
            _item.title = _cursor.getString(_cursorIndexOfTitle);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public List<Book> findAllBooksSync() {
    final String _sql = "SELECT * FROM Book";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final List<Book> _result = new ArrayList<Book>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Book _item;
        _item = new Book();
        _item.id = _cursor.getString(_cursorIndexOfId);
        _item.title = _cursor.getString(_cursorIndexOfTitle);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
