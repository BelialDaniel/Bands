package com.dokkastudios.enkore.listeners;

import android.view.View;

import Classes.Band;
import Classes.Event;

/**
 * Created by BelialDaniel on 8/17/17.
 */

public interface OnMapEventClick
{
    /**
     * @param event
     */
    void onMarkerClicked(Event event);

    /**
     *
     */
    void onMapClicked();
}