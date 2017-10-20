package com.dokkastudios.enkore.listeners;

import Classes.User;

/**
 * Created by BelialDaniel on 8/30/17.
 */

public interface CallbackMainApp
{
    /**
     *
     * @param _user
     */
    void onLogInSuccess(User _user);

    /**
     *
     */
    void onSingUpSuccess();

    /**
     *
     */
    void onCheckInternet();

    /**
     *
     */
    void onSingUpClicked();

    /**
     *
     */
    void onLogInClicked();
}