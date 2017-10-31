package com.dokkastudios.enkore.database.util;

import android.content.Context;

import com.dokkastudios.enkore.database.DataBaseSQLite;
import com.dokkastudios.enkore.database.DataBase;
import com.dokkastudios.enkore.util.RequestTo;

/**
 * Created by BelialDaniel on 10/10/17.
 */

public class RequestsToTheDataBase
{
    private static Context mContext = null;
    private static RequestsToTheDataBase INSTANCE = null;
    private static DataBase mDB = null;

    /**
     *
     * @param context
     */
    private RequestsToTheDataBase(Context context, DataBase database)
    {
        mContext = context;
        mDB = database;
    }

    /**
     *
     * @param context
     */
    private RequestsToTheDataBase(Context context)
    {
        mContext = context;
        mDB = DataBaseSQLite.getInstance(mContext);
    }

    /**
     *
     * @param context
     * @return
     */
    public static RequestsToTheDataBase withContext(Context context)
    {
        if(INSTANCE == null)
            INSTANCE = new RequestsToTheDataBase(context);
        return INSTANCE;
    }

    /**
     *
     * @param request
     */
    public void requestTo(RequestTo<DataBase> request)
    {
        request.requestTo(mDB);
        mDB.close();
    }

    /**
     *
     * @return
     */
    public boolean dropDataBase()
    {
        return mContext.deleteDatabase(DataBaseSQLite.DATABASE_NAME);
    }
}