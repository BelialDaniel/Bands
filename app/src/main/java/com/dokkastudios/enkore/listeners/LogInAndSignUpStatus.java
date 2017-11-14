package com.dokkastudios.enkore.listeners;

import Classes.User;

/**
 * Created by BelialDaniel on 8/30/17.
 */

public interface LogInAndSignUpStatus
{
    /**
     *
     * @param _user
     */
    void onLogInSuccess(User _user);

    /**
     *
     */
    void onSignUpSuccess();

    /**
     *
     */
    void onSignUpClicked();

    /**
     *
     */
    void onLogInClicked();
}