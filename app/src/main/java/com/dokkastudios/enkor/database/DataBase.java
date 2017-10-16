package com.dokkastudios.enkor.database;

import android.database.sqlite.SQLiteDatabase;

import Classes.Event;
import Classes.Events;
import Classes.User;

/**
 * Created by BelialDaniel on 10/10/17.
 */

public interface DataBase
{
    /**
     *
     * @return
     */
    User getUser();

    /**
     *
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     *
     * @return
     */
    boolean deleteUser();

    /**
     *
     * @return
     */
    boolean addEvent(Event event);

    /**
     *
     * @param id
     * @return
     */
    boolean deleteEvent(int id);

    /**
     *
     * @return
     */
    Events getEvents();
}