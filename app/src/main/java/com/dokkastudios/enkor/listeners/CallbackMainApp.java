package com.dokkastudios.enkor.listeners;

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
    void LogInSuccess(User _user);

    /**
     *
     */
    void SingUpSuccess();

    /**
     *
     */
    void CheckInternet();

    /**
     *
     */
    void SingUp();

    /**
     *
     */
    void LogIn();
}