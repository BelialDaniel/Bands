package com.dokkastudios.enkore.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Classes.Band;
import Classes.Bands;
import Classes.Event;
import Classes.Events;
import Classes.User;

/**
 * Created by BelialDaniel on 8/11/17.
 */

public class DataBaseSQLite extends SQLiteOpenHelper implements DataBase
{
    private static DataBaseSQLite INSTANCE = null;

    public static final String DATABASE_NAME = "GBs.db";
    private static final int DATABASE_VERSION = 3;

    protected static final String mTableUser = "User";

    protected static final String
            _tU_id       = "_id",
            _tU_name     = "_name",
            _tU_lastName = "_lastName",
            _tU_email    = "_email",
            _tU_city     = "_city",
            _tU_type     = "UserType";

    protected static final String mTableTickets = "Tickets";

    protected static final String
            _tT_id       = "_id",
            _tT_bandName = "_bandName",
            _tT_price    = "_price",
            _tT_date     = "_date",
            _tT_city     = "_city";

    protected static final String mTableEvents = "Events";

    protected static final String
            eID       = "ID",
            eName     = "Name",
            eInfo     = "Info",
            eDate     = "Date",
            eAddress  = "Address",
            eCity     = "City",
            eState    = "State",
            eBandID   = "BandID",
            eBandName = "BandName";

    /**
     * @param context
     */
    //private
    public DataBaseSQLite(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);    }

    /**
     * @param context
     * @param name
     * @param factory
     * @param version
     * @param errorHandler
     */
    private DataBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler)
    {
        super(context, name, factory, version, errorHandler);
    }

    /**
     * @param context
     * @return
     */
    public static DataBaseSQLite getInstance(Context context)
    {
        if (INSTANCE == null)
            INSTANCE = new DataBaseSQLite(context.getApplicationContext());
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String tableUser
                = "create table if not exists " + mTableUser
                + "(" +
                _tU_id       + " integer not null, " +
                _tU_name     + " text, " +
                _tU_lastName + " text, " +
                _tU_email    + " text, " +
                _tU_city     + " text, " +
                _tU_type     + " integer not null"
                + ");";
        db.execSQL(tableUser);

        String tableTickets
                = "create table if not exists " + mTableTickets
                + "(" +
                _tT_id       + " integer not null, " +
                _tT_bandName + " text, " +
                _tT_price    + " decimal, " +
                _tT_date     + " date, " +
                _tT_city     + " text "
                + ");";
        db.execSQL(tableTickets);

        String tableEvents
                = "create table if not exists " + mTableEvents
                + "(" +
                eID       + " integer, " +
                eName     + " text, " +
                eInfo     + " text, " +
                eDate     + " date, " +
                eAddress  + " text, " +
                eCity     + " text, " +
                eState    + " text, " +
                eBandID   + " int, " +
                eBandName + " text, "
                + "CONSTRAINT id unique("+ eID +"));";
        db.execSQL(tableEvents);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV)
    {
        db.execSQL("drop table if exists " + mTableUser);
        db.execSQL("drop table if exists " + mTableTickets);
        db.execSQL("drop table if exists " + mTableEvents);
        onCreate(db);
    }

    /**
     * ===========================================================================================
     */

    @Override
    public boolean addUser(User user)
    {
        ContentValues _valuesUser = new ContentValues();
        _valuesUser.put(_tU_id, user.getUserID());
        _valuesUser.put(_tU_name, user.getFirsName());
        _valuesUser.put(_tU_lastName, user.getLastName());
        _valuesUser.put(_tU_email, user.getEmail());
        _valuesUser.put(_tU_city, user.getCity());
        _valuesUser.put(_tU_type, user.getUserType());

        SQLiteDatabase _db = getWritableDatabase();
        long row = _db.insert(mTableUser, null, _valuesUser);
        _db.close();

        return row > 0;
    }

    @Override
    public User getUser()
    {
        User _user = null;

        String _queryUser = "select * from " + mTableUser;

        SQLiteDatabase _db = getWritableDatabase();
        Cursor _cur = _db.rawQuery(_queryUser, null);
        _cur.moveToFirst();
        _db.close();

        if (_cur.getCount() <= 0) {
            _cur.close();
            return _user;
        }

        _user = new User(_cur.getInt(_cur.getColumnIndex(_tU_id)),
                _cur.getString(_cur.getColumnIndex(_tU_email)),
                _cur.getString(_cur.getColumnIndex(_tU_name)),
                _cur.getString(_cur.getColumnIndex(_tU_lastName)),
                _cur.getString(_cur.getColumnIndex(_tU_city)),
                _cur.getInt(_cur.getColumnIndex(_tU_type))
        );
        _cur.close();
        return _user;
    }

    @Override
    public boolean deleteUser()
    {
        SQLiteDatabase _db = getWritableDatabase();
        long row = _db.delete(mTableUser, null, null);
        _db.close();

        return row > 0;
    }

    @Override
    public boolean saveEvent(Event event)
    {
        ContentValues valuesEvent = new ContentValues();
        valuesEvent.put(eID, event.getID());
        valuesEvent.put(eName, event.getName());
        valuesEvent.put(eInfo, event.getInfo());
        valuesEvent.put(eDate, event.getDate());
        valuesEvent.put(eAddress, event.getAddres());
        valuesEvent.put(eCity, event.getCity());
        valuesEvent.put(eState, event.getState());
        valuesEvent.put(eBandID, event.getBandID());
        valuesEvent.put(eBandName, event.getBandName());

        SQLiteDatabase db = getWritableDatabase();
        long row = db.insert(mTableEvents, null, valuesEvent);
        db.close();

        return row > 0;
    }

    @Override
    public boolean existsEvent(int id)
    {
        String _query = "select * from " + mTableEvents + " where " + eID + " = " + id;
        SQLiteDatabase _db = getWritableDatabase();

        Cursor _cur = _db.rawQuery(_query, null);
        _cur.moveToFirst();
        int rows = _cur.getCount();
        _cur.close();
        _db.close();

        return rows > 0;
    }

    @Override
    public boolean removeEvent(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(mTableEvents, eID + " = " + id, null);
        db.close();

        return rows > 0;
    }

    @Override
    public Events getEvents()
    {
        return null;
    }

    @Override
    public boolean saveBand(Band band)
    {
        return false;
    }

    @Override
    public boolean removeBand(int id)
    {
        return false;
    }

    @Override
    public Bands getBands()
    {
        return null;
    }

    @Override
    public void close()
    {
        super.close();
    }
}