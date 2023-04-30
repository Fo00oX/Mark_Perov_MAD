package com.example.movieapp.data;

import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.example.movieapp.data.models.Movie;
import com.example.movieapp.utils.CustomConverters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"deprecation"})
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
            @NonNull
            @Override
            public String createQuery() {
                return "INSERT OR REPLACE INTO `Movie` (`id`,`title`,`year`,`genre`,`director`,`actors`,`plot`,`images`,`rating`,`isFavorite`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
            }

            @Override
            public void bind(SupportSQLiteStatement stmt, Movie value) {
                stmt.bindLong(1, value.getId());
                value.getTitle();
                stmt.bindString(2, value.getTitle());
                value.getYear();
                stmt.bindString(3, value.getYear());
                value.getGenre();
                stmt.bindString(4, value.getGenre());
                value.getDirector();
                stmt.bindString(5, value.getDirector());
                value.getActors();
                stmt.bindString(6, value.getActors());
                value.getPlot();
                stmt.bindString(7, value.getPlot());
                final String _tmp = __customConverters.listToString(value.getImages());
                stmt.bindString(8, _tmp);
                stmt.bindDouble(9, value.getRating());
                final int _tmp_1 = value.isFavorite() ? 1 : 0;
                stmt.bindLong(10, _tmp_1);
            }
        };
        this.__deletionAdapterOfMovie = new EntityDeletionOrUpdateAdapter<Movie>(__db) {
            @NonNull
            @Override
            public String createQuery() {
                return "DELETE FROM `Movie` WHERE `id` = ?";
            }

            @Override
            public void bind(@NonNull SupportSQLiteStatement stmt, Movie value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__updateAdapterOfMovie = new EntityDeletionOrUpdateAdapter<Movie>(__db) {
            @NonNull
            @Override
            public String createQuery() {
                return "UPDATE OR ABORT `Movie` SET `id` = ?,`title` = ?,`year` = ?,`genre` = ?,`director` = ?,`actors` = ?,`plot` = ?,`images` = ?,`rating` = ?,`isFavorite` = ? WHERE `id` = ?";
            }

            @Override
            public void bind(@NonNull SupportSQLiteStatement stmt, Movie value) {
                stmt.bindLong(1, value.getId());
                value.getTitle();
                stmt.bindString(2, value.getTitle());
                value.getYear();
                stmt.bindString(3, value.getYear());
                value.getGenre();
                stmt.bindString(4, value.getGenre());
                value.getDirector();
                stmt.bindString(5, value.getDirector());
                value.getActors();
                stmt.bindString(6, value.getActors());
                value.getPlot();
                stmt.bindString(7, value.getPlot());
                final String _tmp = __customConverters.listToString(value.getImages());
                stmt.bindString(8, _tmp);
                stmt.bindDouble(9, value.getRating());
                final int _tmp_1 = value.isFavorite() ? 1 : 0;
                stmt.bindLong(10, _tmp_1);
                stmt.bindLong(11, value.getId());
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
            @NonNull
            @Override
            public String createQuery() {
                return "DELETE FROM movie";
            }
        };
    }

    @Override
    public Object add(@NonNull final Movie movie, @NonNull final Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(__db, true, () -> {
            __db.beginTransaction();
            try {
                __insertionAdapterOfMovie.insert(movie);
                __db.setTransactionSuccessful();
                return Unit.INSTANCE;
            } finally {
                __db.endTransaction();
            }
        }, continuation);
    }

    @Override
    public Object delete(@NonNull final Movie movie, @NonNull final Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(__db, true, () -> {
            __db.beginTransaction();
            try {
                __deletionAdapterOfMovie.handle(movie);
                __db.setTransactionSuccessful();
                return Unit.INSTANCE;
            } finally {
                __db.endTransaction();
            }
        }, continuation);
    }

    @Override
    public Object update(@NonNull final Movie movie, @NonNull final Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(__db, true, () -> {
            __db.beginTransaction();
            try {
                __updateAdapterOfMovie.handle(movie);
                __db.setTransactionSuccessful();
                return Unit.INSTANCE;
            } finally {
                __db.endTransaction();
            }
        }, continuation);
    }

    @Override
    public Object deleteAll(@NonNull final Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(__db, true, () -> {
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
        }, continuation);
    }

    @NonNull
    @Override
    public Flow<List<Movie>> getAllMovies() {
        final String _sql = "SELECT * FROM movie";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
        return CoroutinesRoom.createFlow(__db, false, new String[]{"movie"}, new Callable<List<Movie>>() {
            @Override
            public List<Movie> call() {
                try (Cursor _cursor = DBUtil.query(__db, _statement, false, null)) {
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
                    final List<Movie> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
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
                        assert _tmp != null;
                        _tmpImages = __customConverters.stringToList(_tmp);
                        final float _tmpRating;
                        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
                        final boolean _tmpIsFavorite;
                        final int _tmp_1;
                        _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
                        _tmpIsFavorite = _tmp_1 != 0;
                        assert _tmpTitle != null;
                        assert _tmpYear != null;
                        assert _tmpGenre != null;
                        assert _tmpDirector != null;
                        assert _tmpActors != null;
                        assert _tmpPlot != null;
                        _item = new Movie(_tmpId, _tmpTitle, _tmpYear, _tmpGenre, _tmpDirector, _tmpActors, _tmpPlot, _tmpImages, _tmpRating, _tmpIsFavorite);
                        _result.add(_item);
                    }
                    return _result;
                }
            }

            @Override
            protected void finalize() {
                _statement.release();
            }
        });
    }

    @NonNull
    @Override
    public Flow<List<Movie>> getAllFavorites() {
        final String _sql = "SELECT * FROM movie WHERE isFavorite = true";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
        return CoroutinesRoom.createFlow(__db, false, new String[]{"movie"}, new Callable<List<Movie>>() {
            @Override
            public List<Movie> call() {
                try (Cursor _cursor = DBUtil.query(__db, _statement, false, null)) {
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
                    final List<Movie> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
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
                        assert _tmp != null;
                        _tmpImages = __customConverters.stringToList(_tmp);
                        final float _tmpRating;
                        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
                        final boolean _tmpIsFavorite;
                        final int _tmp_1;
                        _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
                        _tmpIsFavorite = _tmp_1 != 0;
                        assert _tmpTitle != null;
                        assert _tmpYear != null;
                        assert _tmpGenre != null;
                        assert _tmpDirector != null;
                        assert _tmpActors != null;
                        assert _tmpPlot != null;
                        _item = new Movie(_tmpId, _tmpTitle, _tmpYear, _tmpGenre, _tmpDirector, _tmpActors, _tmpPlot, _tmpImages, _tmpRating, _tmpIsFavorite);
                        _result.add(_item);
                    }
                    return _result;
                }
            }

            @Override
            protected void finalize() {
                _statement.release();
            }
        });
    }

    @NonNull
    @Override
    public Flow<Movie> getMovieById(final int movieId) {
        final String _sql = "SELECT * FROM movie WHERE id=?";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
        int _argIndex = 1;
        _statement.bindLong(_argIndex, movieId);
        return CoroutinesRoom.createFlow(__db, false, new String[]{"movie"}, new Callable<Movie>() {
            @Override
            public Movie call() {
                try (Cursor _cursor = DBUtil.query(__db, _statement, false, null)) {
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
                    if (_cursor.moveToFirst()) {
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
                        assert _tmp != null;
                        _tmpImages = __customConverters.stringToList(_tmp);
                        final float _tmpRating;
                        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
                        final boolean _tmpIsFavorite;
                        final int _tmp_1;
                        _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
                        _tmpIsFavorite = _tmp_1 != 0;
                        assert _tmpTitle != null;
                        assert _tmpYear != null;
                        assert _tmpGenre != null;
                        assert _tmpDirector != null;
                        assert _tmpActors != null;
                        assert _tmpPlot != null;
                        _result = new Movie(_tmpId, _tmpTitle, _tmpYear, _tmpGenre, _tmpDirector, _tmpActors, _tmpPlot, _tmpImages, _tmpRating, _tmpIsFavorite);
                    } else {
                        _result = null;
                    }
                    return _result;
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
