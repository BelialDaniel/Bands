package com.dokkastudios.enkore.database;

import Classes.Band;
import Classes.Bands;
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
    boolean saveEvent(Event event);

    /**
     *
     * @param id
     * @return
     */
    boolean existsEvent(int id);

    /**
     *
     * @param id
     * @return
     */
    boolean removeEvent(int id);

    /**
     *
     * @return
     */
    Events getEvents();

    /**
     *
     * @param band
     * @return
     */
    boolean saveBand(Band band);

    /**
     *
     * @param id
     * @return
     */
    boolean removeBand(int id);

    /**
     *
     * @return
     */
    Bands getBands();
}