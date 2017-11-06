package com.dokkastudios.enkore.listeners;

import android.view.View;

import Classes.Band;

/**
 * Created by BelialDaniel on 11/5/17.
 */

public interface OnBandListClick
{
    /**
     *
     * @param band
     */
    void onBandListClicked(Band band, View cardView);
}
