package Interfaces;

import android.view.View;

import Classes.Band;
import Classes.Event;

/**
 * Created by BelialDaniel on 8/17/17.
 */

public interface CallbackMainUser
{
    /**
     *
     * @param band
     */
    //void onBandListClicked(Band band);

    /**
     *
     * @param band
     * @param cardView
     */
    void onBandListClicked(Band band, View cardView);

    /**
     *
     * @param event
     * @param cardView
     */
    void onEventListClicked(Event event, View cardView);

    /**
     *
     * @param event
     */
    //void onEventListClicked(Event event);

    /**
     *
     * @param event
     */
    void onClickMarker(Event event);

    /**
     *
     */
    void onClickMap();
}