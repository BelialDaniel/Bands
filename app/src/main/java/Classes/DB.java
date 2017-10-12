package Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Interfaces.DataBaseDefinition;

/**
 * Created by BelialDaniel on 8/11/17.
 */

public class DB extends SQLiteOpenHelper implements DataBaseDefinition
{
    public static final String DATABASE_NAME = "GBs.db";
    public static final int DATABASE_VERSION = 2;
    private static DB INSTANCE = null;

    private static final String mTableUser = "User";

    private static final String
            _tU_id       = "_id",
            _tU_name     = "_name",
            _tU_lastName = "_lastName",
            _tU_email    = "_email",
            _tU_city     = "_city",
            _tU_type     = "UserType";

    private static final String mTableTickets = "Tickets";

    private static final String
            _tT_id       = "_id",
            _tT_bandName = "_bandName",
            _tT_price    = "_price",
            _tT_date     = "_date",
            _tT_city     = "_city";

    private static final String mTableEvents = "Events";

    private static final String
            eID = "ID",
            eName = "Name",
            eInfo = "Info",
            eDate = "Date",
            eAddress = "Address",
            eCity = "City",
            eState = "State",
            eBandID = "BandID",
            eBandName = "BandName";

    /**
     *
     * @param context
     */
    private DB(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * @deprecated
     * Use contextToGetDataBase to instantiate once time
     * @param _cont
     * @param factory
     * @param version
     */
    @Deprecated
    public DB(Context _cont, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(_cont, DATABASE_NAME, factory, version);
    }

    /**
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     * @param errorHandler
     */
    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler)
    {
        super(context, name, factory, version, errorHandler);
    }

    /**
     *
     * @param context
     * @return
     */
    public static DB getInstance(Context context)
    {
        if (INSTANCE == null)
            INSTANCE = new DB(context.getApplicationContext());
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
                eID       + " int, " +
                eName     + " text, " +
                eInfo     + " text, " +
                eDate     + " date, " +
                eAddress  + " text, " +
                eCity     + " text, " +
                eState    + " text, " +
                eBandID   + " int, " +
                eBandName + " text "
                + ");";
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

    /**===========================================================================================*/

    @Override
    public boolean addUser(User user)
    {
        ContentValues _valuesUser = new ContentValues();
        _valuesUser.put(_tU_id,       user.getUserID());
        _valuesUser.put(_tU_name,     user.getFirsName());
        _valuesUser.put(_tU_lastName, user.getLastName());
        _valuesUser.put(_tU_email,    user.getEmail());
        _valuesUser.put(_tU_city,     user.getCity());
        _valuesUser.put(_tU_type,     user.getUserType());

        SQLiteDatabase _db = getWritableDatabase();
        long _row = _db.insert(mTableUser, null, _valuesUser);
        _db.close();

        if(_row != -1)
            return true;

        return false;
    }

    @Override
    public User getUser()
    {
        User _user = null;

        String _queryUser = "select * from " + mTableUser;
        SQLiteDatabase _db = getWritableDatabase();

        Cursor _cur = _db.rawQuery(_queryUser, null);
        _cur.moveToFirst();

        if(_cur.getCount() <= 0)
            return _user;

        _user = new User(_cur.getInt(_cur.getColumnIndex(_tU_id)),
                         _cur.getString(_cur.getColumnIndex(_tU_email)),
                         _cur.getString(_cur.getColumnIndex(_tU_name)),
                         _cur.getString(_cur.getColumnIndex(_tU_lastName)),
                         _cur.getString(_cur.getColumnIndex(_tU_city)),
                         _cur.getInt(_cur.getColumnIndex(_tU_type))
                        );

        _db.close();
        return _user;
    }

    @Override
    public boolean deleteUser()
    {
        SQLiteDatabase _db = getWritableDatabase();
        long _row = _db.delete(mTableUser, null, null);
        _db.close();

        if(_row > 0)
            return true;

        return false;
    }

    @Override
    public boolean addEvent(Event event)
    {
        ContentValues valuesEvent = new ContentValues();
        valuesEvent.put(eID       , event.getID());
        valuesEvent.put(eName     , event.getName());
        valuesEvent.put(eInfo     , event.getInfo());
        valuesEvent.put(eDate     , event.getDate());
        valuesEvent.put(eAddress  , event.getAddres());
        valuesEvent.put(eCity     , event.getCity());
        valuesEvent.put(eState    , event.getState());
        valuesEvent.put(eBandID   , event.getBandID());
        valuesEvent.put(eBandName , event.getBandName());

        SQLiteDatabase db = getWritableDatabase();
        long _row = db.insert(mTableEvents, null, valuesEvent);
        db.close();

        if(_row != -1)
            return true;
        return false;
    }

    @Override
    public boolean deleteEvent(int id)
    {
        return false;
    }

    @Override
    public Events getEvents()
    {
        return null;
    }


}