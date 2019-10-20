package com.example.android.persistence.codelab.db;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
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
public class LoanDao_Impl implements LoanDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfLoan;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public LoanDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLoan = new EntityInsertionAdapter<Loan>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Loan`(`id`,`startTime`,`endTime`,`book_id`,`user_id`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Loan value) {
        if (value.id == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.id);
        }
        final Long _tmp;
        _tmp = DateConverter.toTimestamp(value.startTime);
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
        }
        final Long _tmp_1;
        _tmp_1 = DateConverter.toTimestamp(value.endTime);
        if (_tmp_1 == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp_1);
        }
        if (value.bookId == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.bookId);
        }
        if (value.userId == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.userId);
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Loan";
        return _query;
      }
    };
  }

  @Override
  public void insertLoan(Loan loan) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfLoan.insert(loan);
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
  public LiveData<List<Loan>> findAllLoans() {
    final String _sql = "SELECT * From Loan";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Loan>>() {
      private Observer _observer;

      @Override
      protected List<Loan> compute() {
        if (_observer == null) {
          _observer = new Observer("Loan") {
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
          final int _cursorIndexOfStartTime = _cursor.getColumnIndexOrThrow("startTime");
          final int _cursorIndexOfEndTime = _cursor.getColumnIndexOrThrow("endTime");
          final int _cursorIndexOfBookId = _cursor.getColumnIndexOrThrow("book_id");
          final int _cursorIndexOfUserId = _cursor.getColumnIndexOrThrow("user_id");
          final List<Loan> _result = new ArrayList<Loan>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Loan _item;
            _item = new Loan();
            _item.id = _cursor.getString(_cursorIndexOfId);
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfStartTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfStartTime);
            }
            _item.startTime = DateConverter.toDate(_tmp);
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfEndTime);
            }
            _item.endTime = DateConverter.toDate(_tmp_1);
            _item.bookId = _cursor.getString(_cursorIndexOfBookId);
            _item.userId = _cursor.getString(_cursorIndexOfUserId);
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
  public LiveData<List<LoanWithUserAndBook>> findAllWithUserAndBook() {
    final String _sql = "SELECT Loan.id, Book.title, User.name, Loan.startTime, Loan.endTime From Loan INNER JOIN Book ON Loan.book_id = Book.id INNER JOIN User ON Loan.user_id = User.id ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<LoanWithUserAndBook>>() {
      private Observer _observer;

      @Override
      protected List<LoanWithUserAndBook> compute() {
        if (_observer == null) {
          _observer = new Observer("Loan","Book","User") {
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
          final int _cursorIndexOfBookTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfUserName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfStartTime = _cursor.getColumnIndexOrThrow("startTime");
          final int _cursorIndexOfEndTime = _cursor.getColumnIndexOrThrow("endTime");
          final List<LoanWithUserAndBook> _result = new ArrayList<LoanWithUserAndBook>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final LoanWithUserAndBook _item;
            _item = new LoanWithUserAndBook();
            _item.id = _cursor.getString(_cursorIndexOfId);
            _item.bookTitle = _cursor.getString(_cursorIndexOfBookTitle);
            _item.userName = _cursor.getString(_cursorIndexOfUserName);
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfStartTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfStartTime);
            }
            _item.startTime = DateConverter.toDate(_tmp);
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfEndTime);
            }
            _item.endTime = DateConverter.toDate(_tmp_1);
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
  public LiveData<List<LoanWithUserAndBook>> findLoansByNameAfter(String userName, Date after) {
    final String _sql = "SELECT Loan.id, Book.title as title, User.name as name, Loan.startTime, Loan.endTime FROM Book INNER JOIN Loan ON Loan.book_id = Book.id INNER JOIN User on User.id = Loan.user_id WHERE User.name LIKE ? AND Loan.endTime > ? ";
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
    return new ComputableLiveData<List<LoanWithUserAndBook>>() {
      private Observer _observer;

      @Override
      protected List<LoanWithUserAndBook> compute() {
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
          final int _cursorIndexOfBookTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfUserName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfStartTime = _cursor.getColumnIndexOrThrow("startTime");
          final int _cursorIndexOfEndTime = _cursor.getColumnIndexOrThrow("endTime");
          final List<LoanWithUserAndBook> _result = new ArrayList<LoanWithUserAndBook>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final LoanWithUserAndBook _item;
            _item = new LoanWithUserAndBook();
            _item.id = _cursor.getString(_cursorIndexOfId);
            _item.bookTitle = _cursor.getString(_cursorIndexOfBookTitle);
            _item.userName = _cursor.getString(_cursorIndexOfUserName);
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStartTime)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfStartTime);
            }
            _item.startTime = DateConverter.toDate(_tmp_1);
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfEndTime);
            }
            _item.endTime = DateConverter.toDate(_tmp_2);
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
}
