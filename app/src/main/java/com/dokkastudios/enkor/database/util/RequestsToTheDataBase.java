package com.dokkastudios.enkor.database.util;

import android.content.Context;

import com.dokkastudios.enkor.database.DB;
import com.dokkastudios.enkor.util.RequestTo;

/**
 * Created by BelialDaniel on 10/10/17.
 */

public class RequestsToTheDataBase
{
    private static Context mContext = null;
    private static RequestsToTheDataBase INSTANCE = null;

    /**
     *
     * @param context
     */
    private RequestsToTheDataBase(Context context)
    {
        mContext = context;
    }

    /**
     *
     * @param context
     * @return
     */
    public static RequestsToTheDataBase contextToGetDataBase(Context context)
    {
        if(INSTANCE == null)
            INSTANCE = new RequestsToTheDataBase(context);
        return INSTANCE;
    }

    /**
     *
     * @param request
     */
    public void requestTo(RequestTo<DB> request)
    {
        DB db = DB.getInstance(mContext);
        request.requestTo(db);
        db.close();
    }

    /**
     *
     * @return
     */
    public boolean dropDataBase()
    {
        return mContext.deleteDatabase(DB.DATABASE_NAME);
    }
}