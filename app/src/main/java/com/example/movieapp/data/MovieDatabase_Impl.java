package com.example.movieapp.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MovieDatabase_Impl extends MovieDatabase {
    private volatile MovieDao _movieDao;

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        final RoomOpenHelper _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
            @Override
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `Movie` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `year` TEXT NOT NULL, `genre` TEXT NOT NULL, `director` TEXT NOT NULL, `actors` TEXT NOT NULL, `plot` TEXT NOT NULL, `images` TEXT NOT NULL, `rating` REAL NOT NULL, `isFavorite` INTEGER NOT NULL)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '251360329b26f4fe1443a2e0b75e3f97')");
            }

            @Override
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `Movie`");
                if (mCallbacks != null) {
                    for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
                        mCallbacks.get(_i).onDestructiveMigration(_db);
                    }
                }
            }

            @Override
            public void onCreate(SupportSQLiteDatabase _db) {
                if (mCallbacks != null) {
                    for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
                        mCallbacks.get(_i).onCreate(_db);
                    }
                }
            }

            @Override
            public void onOpen(SupportSQLiteDatabase _db) {
                mDatabase = _db;
                internalInitInvalidationTracker(_db);
                if (mCallbacks != null) {
                    for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
                        mCallbacks.get(_i).onOpen(_db);
                    }
                }
            }

            @Override
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override
            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                final HashMap<String, TableInfo.Column> _columnsMovie = new HashMap<String, TableInfo.Column>(10);
                _columnsMovie.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMovie.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMovie.put("year", new TableInfo.Column("year", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMovie.put("genre", new TableInfo.Column("genre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMovie.put("director", new TableInfo.Column("director", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMovie.put("actors", new TableInfo.Column("actors", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMovie.put("plot", new TableInfo.Column("plot", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMovie.put("images", new TableInfo.Column("images", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMovie.put("rating", new TableInfo.Column("rating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMovie.put("isFavorite", new TableInfo.Column("isFavorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysMovie = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesMovie = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoMovie = new TableInfo("Movie", _columnsMovie, _foreignKeysMovie, _indicesMovie);
                final TableInfo _existingMovie = TableInfo.read(_db, "Movie");
                if (! _infoMovie.equals(_existingMovie)) {
                    return new RoomOpenHelper.ValidationResult(false, "Movie(com.example.movie.models.Movie).\n"
                            + " Expected:\n" + _infoMovie + "\n"
                            + " Found:\n" + _existingMovie);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "251360329b26f4fe1443a2e0b75e3f97", "73fada93ebfbda4247499efdfe88a9c3");
        final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
                .name(configuration.name)
                .callback(_openCallback)
                .build();
        final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
        return _helper;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
        HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
        return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Movie");
    }

    @Override
    public void clearAllTables() {
        super.assertNotMainThread();
        final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            _db.execSQL("DELETE FROM `Movie`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            _db.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!_db.inTransaction()) {
                _db.execSQL("VACUUM");
            }
        }
    }

    @Override
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
        _typeConvertersMap.put(MovieDao.class, MovieDao_Impl.getRequiredConverters());
        return _typeConvertersMap;
    }

    @Override
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
        return _autoMigrationSpecsSet;
    }

    @Override
    public List<Migration> getAutoMigrations(
            @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList();
    }

    @Override
    public MovieDao MovieDao() {
        if (_movieDao != null) {
            return _movieDao;
        } else {
            synchronized(this) {
                if(_movieDao == null) {
                    _movieDao = new MovieDao_Impl(this);
                }
                return _movieDao;
            }
        }
    }
}
