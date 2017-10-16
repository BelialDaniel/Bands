package com.dokkastudios.enkor.util;

import Classes.Band;
import Classes.Bands;
import Classes.Event;
import Classes.Events;
import Classes.Ticket;
import Classes.User;

/**
 * Created by BelialDaniel on 9/21/17.
 */

public final class StoreResources
{
    private static User mUser = null;

    private static Bands mBands = null;
    private static Events mEvents = null;

    private static Band mBand = null;
    private static Event mEvent = null;
    private static Ticket mTicket = null;

    private static final StoreResources INSTANCE = new StoreResources();

    private StoreResources() {}

    /**
     *
     * @param _user
     */
    public static void setUser(User _user)
    {
        INSTANCE.mUser = _user;
    }

    /**
     *
     * @return
     */
    public static User getUser()
    {
        return INSTANCE.mUser;
    }

    /**
     *
     * @param _bands
     */
    public static void setBands(Bands _bands)
    {
        INSTANCE.mBands = _bands;
    }

    /**
     *
     * @return
     */
    public static Bands getBands()
    {
        return INSTANCE.mBands;
    }

    /**
     *
     * @param _events
     */
    public static void setEvents(Events _events)
    {
        INSTANCE.mEvents = _events;
    }

    /**
     *
     * @return
     */
    public static Events getEvents()
    {
        return INSTANCE.mEvents;
    }

    /**
     *
     * @param _band
     */
    public static void setBand(Band _band)
    {
        INSTANCE.mBand = _band;
    }

    /**
     *
     * @return
     */
    public static Band getBand()
    {
        return INSTANCE.mBand;
    }

    /**
     *
     * @param _ticket
     */
    public static void setTicket(Ticket _ticket)
    {
        INSTANCE.mTicket = _ticket;
    }

    /**
     *
     * @return
     */
    public static Ticket getTicket()
    {
        return INSTANCE.mTicket;
    }

    /**
     *
     * @param _bandEvent
     */
    public static void setEvent(Event _bandEvent)
    {
        INSTANCE.mEvent = _bandEvent;
    }

    /**
     *
     * @return
     */
    public static Event getEvent()
    {
        return INSTANCE.mEvent;
    }

}