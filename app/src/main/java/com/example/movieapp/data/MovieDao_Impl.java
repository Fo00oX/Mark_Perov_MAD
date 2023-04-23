package com.example.movieapp.data;

import android.database.Cursor;

import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.example.movieapp.models.Movie;
import com.example.movieapp.utils.CustomConverters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MovieDao_Impl implements MovieDao {
    private final RoomDatabase __db;

    private final EntityInsertionAdapter<Movie> __insertionAdapterOfMovie;

    private final CustomConverters __customConverters = new CustomConverters();

    private final EntityDeletionOrUpdateAdapter<Movie> __deletionAdapterOfMovie;

    private final EntityDeletionOrUpdateAdapter<Movie> __updateAdapterOfMovie;

    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public MovieDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfMovie = new EntityInsertionAdapter<Movie>(__db) {
            @Override
            public String createQuery() {
                return "INSERT OR REPLACE INTO `Movie` (`id`,`title`,`year`,`genre`,`director`,`actors`,`plot`,`images`,`rating`,`isFavorite`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
            }

            @Override
            public void bind(SupportSQLiteStatement stmt, Movie value) {
                stmt.bindLong(1, value.getId());
                if (value.getTitle() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getTitle());
                }
                if (value.getYear() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getYear());
                }
                if (value.getGenre() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getGenre());
                }
                if (value.getDirector() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getDirector());
                }
                if (value.getActors() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getActors());
                }
                if (value.getPlot() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getPlot());
                }
                final String _tmp = __customConverters.listToString(value.getImages());
                if (_tmp == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, _tmp);
                }
                stmt.bindDouble(9, value.getRating());
                final int _tmp_1 = value.isFavorite() ? 1 : 0;
                stmt.bindLong(10, _tmp_1);
            }
        };
        this.__deletionAdapterOfMovie = new EntityDeletionOrUpdateAdapter<Movie>(__db) {
            @Override
            public String createQuery() {
                return "DELETE FROM `Movie` WHERE `id` = ?";
            }

            @Override
            public void bind(SupportSQLiteStatement stmt, Movie value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__updateAdapterOfMovie = new EntityDeletionOrUpdateAdapter<Movie>(__db) {
            @Override
            public String createQuery() {
                return "UPDATE OR ABORT `Movie` SET `id` = ?,`title` = ?,`year` = ?,`genre` = ?,`director` = ?,`actors` = ?,`plot` = ?,`images` = ?,`rating` = ?,`isFavorite` = ? WHERE `id` = ?";
            }

            @Override
            public void bind(SupportSQLiteStatement stmt, Movie value) {
                stmt.bindLong(1, value.getId());
                if (value.getTitle() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getTitle());
                }
                if (value.getYear() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getYear());
                }
                if (value.getGenre() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getGenre());
                }
                if (value.getDirector() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getDirector());
                }
                if (value.getActors() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getActors());
                }
                if (value.getPlot() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getPlot());
                }
                final String _tmp = __customConverters.listToString(value.getImages());
                if (_tmp == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, _tmp);
                }
                stmt.bindDouble(9, value.getRating());
                final int _tmp_1 = value.isFavorite() ? 1 : 0;
                stmt.bindLong(10, _tmp_1);
                stmt.bindLong(11, value.getId());
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
            @Override
            public String createQuery() {
                final String _query = "DELETE FROM movie";
                return _query;
            }
        };
    }

    @Override
    public Object add(final Movie movie, final Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
            @Override
            public Unit call() throws Exception {
                __db.beginTransaction();
                try {
                    __insertionAdapterOfMovie.insert(movie);
                    __db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    __db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override
    public Object delete(final Movie movie, final Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
            @Override
            public Unit call() throws Exception {
                __db.beginTransaction();
                try {
                    __deletionAdapterOfMovie.handle(movie);
                    __db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    __db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override
    public Object update(final Movie movie, final Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
            @Override
            public Unit call() throws Exception {
                __db.beginTransaction();
                try {
                    __updateAdapterOfMovie.handle(movie);
                    __db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    __db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override
    public Object deleteAll(final Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
            @Override
            public Unit call() throws Exception {
                final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
                __db.beginTransaction();
                try {
                    _stmt.executeUpdateDelete();
                    __db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    __db.endTransaction();
                    __preparedStmtOfDeleteAll.release(_stmt);
                }
            }
        }, continuation);
    }

    @Override
    public Flow<List<Movie>> getAllMovies() {
        final String _sql = "SELECT * FROM movie";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
        return CoroutinesRoom.createFlow(__db, false, new String[]{"movie"}, new Callable<List<Movie>>() {
            @Override
            public List<Movie> call() throws Exception {
                final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
                try {
                    final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
                    final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
                    final int _cursorIndexOfGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "genre");
                    final int _cursorIndexOfDirector = CursorUtil.getColumnIndexOrThrow(_cursor, "director");
                    final int _cursorIndexOfActors = CursorUtil.getColumnIndexOrThrow(_cursor, "actors");
                    final int _cursorIndexOfPlot = CursorUtil.getColumnIndexOrThrow(_cursor, "plot");
                    final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
                    final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
                    final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
                    final List<Movie> _result = new ArrayList<Movie>(_cursor.getCount());
                    while(_cursor.moveToNext()) {
                        final Movie _item;
                        final int _tmpId;
                        _tmpId = _cursor.getInt(_cursorIndexOfId);
                        final String _tmpTitle;
                        if (_cursor.isNull(_cursorIndexOfTitle)) {
                            _tmpTitle = null;
                        } else {
                            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
                        }
                        final String _tmpYear;
                        if (_cursor.isNull(_cursorIndexOfYear)) {
                            _tmpYear = null;
                        } else {
                            _tmpYear = _cursor.getString(_cursorIndexOfYear);
                        }
                        final String _tmpGenre;
                        if (_cursor.isNull(_cursorIndexOfGenre)) {
                            _tmpGenre = null;
                        } else {
                            _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
                        }
                        final String _tmpDirector;
                        if (_cursor.isNull(_cursorIndexOfDirector)) {
                            _tmpDirector = null;
                        } else {
                            _tmpDirector = _cursor.getString(_cursorIndexOfDirector);
                        }
                        final String _tmpActors;
                        if (_cursor.isNull(_cursorIndexOfActors)) {
                            _tmpActors = null;
                        } else {
                            _tmpActors = _cursor.getString(_cursorIndexOfActors);
                        }
                        final String _tmpPlot;
                        if (_cursor.isNull(_cursorIndexOfPlot)) {
                            _tmpPlot = null;
                        } else {
                            _tmpPlot = _cursor.getString(_cursorIndexOfPlot);
                        }
                        final List<String> _tmpImages;
                        final String _tmp;
                        if (_cursor.isNull(_cursorIndexOfImages)) {
                            _tmp = null;
                        } else {
                            _tmp = _cursor.getString(_cursorIndexOfImages);
                        }
                        _tmpImages = __customConverters.stringToList(_tmp);
                        final float _tmpRating;
                        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
                        final boolean _tmpIsFavorite;
                        final int _tmp_1;
                        _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
                        _tmpIsFavorite = _tmp_1 != 0;
                        _item = new Movie(_tmpId,_tmpTitle,_tmpYear,_tmpGenre,_tmpDirector,_tmpActors,_tmpPlot,_tmpImages,_tmpRating,_tmpIsFavorite);
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
        });
    }

    @Override
    public Flow<List<Movie>> getAllFavorites() {
        final String _sql = "SELECT * FROM movie WHERE isFavorite = true";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
        return CoroutinesRoom.createFlow(__db, false, new String[]{"movie"}, new Callable<List<Movie>>() {
            @Override
            public List<Movie> call() throws Exception {
                final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
                try {
                    final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
                    final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
                    final int _cursorIndexOfGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "genre");
                    final int _cursorIndexOfDirector = CursorUtil.getColumnIndexOrThrow(_cursor, "director");
                    final int _cursorIndexOfActors = CursorUtil.getColumnIndexOrThrow(_cursor, "actors");
                    final int _cursorIndexOfPlot = CursorUtil.getColumnIndexOrThrow(_cursor, "plot");
                    final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
                    final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
                    final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
                    final List<Movie> _result = new ArrayList<Movie>(_cursor.getCount());
                    while(_cursor.moveToNext()) {
                        final Movie _item;
                        final int _tmpId;
                        _tmpId = _cursor.getInt(_cursorIndexOfId);
                        final String _tmpTitle;
                        if (_cursor.isNull(_cursorIndexOfTitle)) {
                            _tmpTitle = null;
                        } else {
                            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
                        }
                        final String _tmpYear;
                        if (_cursor.isNull(_cursorIndexOfYear)) {
                            _tmpYear = null;
                        } else {
                            _tmpYear = _cursor.getString(_cursorIndexOfYear);
                        }
                        final String _tmpGenre;
                        if (_cursor.isNull(_cursorIndexOfGenre)) {
                            _tmpGenre = null;
                        } else {
                            _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
                        }
                        final String _tmpDirector;
                        if (_cursor.isNull(_cursorIndexOfDirector)) {
                            _tmpDirector = null;
                        } else {
                            _tmpDirector = _cursor.getString(_cursorIndexOfDirector);
                        }
                        final String _tmpActors;
                        if (_cursor.isNull(_cursorIndexOfActors)) {
                            _tmpActors = null;
                        } else {
                            _tmpActors = _cursor.getString(_cursorIndexOfActors);
                        }
                        final String _tmpPlot;
                        if (_cursor.isNull(_cursorIndexOfPlot)) {
                            _tmpPlot = null;
                        } else {
                            _tmpPlot = _cursor.getString(_cursorIndexOfPlot);
                        }
                        final List<String> _tmpImages;
                        final String _tmp;
                        if (_cursor.isNull(_cursorIndexOfImages)) {
                            _tmp = null;
                        } else {
                            _tmp = _cursor.getString(_cursorIndexOfImages);
                        }
                        _tmpImages = __customConverters.stringToList(_tmp);
                        final float _tmpRating;
                        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
                        final boolean _tmpIsFavorite;
                        final int _tmp_1;
                        _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
                        _tmpIsFavorite = _tmp_1 != 0;
                        _item = new Movie(_tmpId,_tmpTitle,_tmpYear,_tmpGenre,_tmpDirector,_tmpActors,_tmpPlot,_tmpImages,_tmpRating,_tmpIsFavorite);
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
        });
    }

    @Override
    public Flow<Movie> getMovieById(final int movieId) {
        final String _sql = "SELECT * FROM movie WHERE id=?";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
        int _argIndex = 1;
        _statement.bindLong(_argIndex, movieId);
        return CoroutinesRoom.createFlow(__db, false, new String[]{"movie"}, new Callable<Movie>() {
            @Override
            public Movie call() throws Exception {
                final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
                try {
                    final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
                    final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
                    final int _cursorIndexOfGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "genre");
                    final int _cursorIndexOfDirector = CursorUtil.getColumnIndexOrThrow(_cursor, "director");
                    final int _cursorIndexOfActors = CursorUtil.getColumnIndexOrThrow(_cursor, "actors");
                    final int _cursorIndexOfPlot = CursorUtil.getColumnIndexOrThrow(_cursor, "plot");
                    final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
                    final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
                    final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
                    final Movie _result;
                    if(_cursor.moveToFirst()) {
                        final int _tmpId;
                        _tmpId = _cursor.getInt(_cursorIndexOfId);
                        final String _tmpTitle;
                        if (_cursor.isNull(_cursorIndexOfTitle)) {
                            _tmpTitle = null;
                        } else {
                            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
                        }
                        final String _tmpYear;
                        if (_cursor.isNull(_cursorIndexOfYear)) {
                            _tmpYear = null;
                        } else {
                            _tmpYear = _cursor.getString(_cursorIndexOfYear);
                        }
                        final String _tmpGenre;
                        if (_cursor.isNull(_cursorIndexOfGenre)) {
                            _tmpGenre = null;
                        } else {
                            _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
                        }
                        final String _tmpDirector;
                        if (_cursor.isNull(_cursorIndexOfDirector)) {
                            _tmpDirector = null;
                        } else {
                            _tmpDirector = _cursor.getString(_cursorIndexOfDirector);
                        }
                        final String _tmpActors;
                        if (_cursor.isNull(_cursorIndexOfActors)) {
                            _tmpActors = null;
                        } else {
                            _tmpActors = _cursor.getString(_cursorIndexOfActors);
                        }
                        final String _tmpPlot;
                        if (_cursor.isNull(_cursorIndexOfPlot)) {
                            _tmpPlot = null;
                        } else {
                            _tmpPlot = _cursor.getString(_cursorIndexOfPlot);
                        }
                        final List<String> _tmpImages;
                        final String _tmp;
                        if (_cursor.isNull(_cursorIndexOfImages)) {
                            _tmp = null;
                        } else {
                            _tmp = _cursor.getString(_cursorIndexOfImages);
                        }
                        _tmpImages = __customConverters.stringToList(_tmp);
                        final float _tmpRating;
                        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
                        final boolean _tmpIsFavorite;
                        final int _tmp_1;
                        _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
                        _tmpIsFavorite = _tmp_1 != 0;
                        _result = new Movie(_tmpId,_tmpTitle,_tmpYear,_tmpGenre,_tmpDirector,_tmpActors,_tmpPlot,_tmpImages,_tmpRating,_tmpIsFavorite);
                    } else {
                        _result = null;
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
        });
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

}
