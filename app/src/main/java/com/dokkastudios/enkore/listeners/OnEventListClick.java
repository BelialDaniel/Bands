package com.dokkastudios.enkore.listeners;

import android.view.View;

import Classes.Event;

/**
 * Created by BelialDaniel on 11/5/17.
 */

public interface OnEventListClick
{
    /**
     *
     * @param event
     */
    void onEventListClicked(Event event, View cardView);
}
