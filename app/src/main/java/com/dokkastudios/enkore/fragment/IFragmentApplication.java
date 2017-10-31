package com.dokkastudios.enkore.fragment;

import android.support.v4.app.FragmentManager;

import com.dokkastudios.enkore.fragment.Fragments;

/**
 * Created by BelialDaniel on 10/20/17.
 */

public interface IFragmentApplication
{
    /**
     *
     * @param _contenLayoutID
     * @param _class
     * @return
     */
    void commit(int _contenLayoutID, Class _class);

    /**
     *
     * @param _contenLayoutID
     * @param _class
     * @param _enterAnimation
     * @param _exitAnimation
     * @return
     */
    void commit(int _contenLayoutID, Class _class, int _enterAnimation, int _exitAnimation);

    /**
     *
     * @param _contenLayoutID
     * @param _class
     * @param _enterAnimation
     * @param _exitAnimation
     * @param _popEnter
     * @param _popExit
     * @return
     */
    void commit(int _contenLayoutID, Class _class, int _enterAnimation, int _exitAnimation, int _popEnter, int _popExit);

    /**
     *
     * @param _class
     */
    void removeFragment(Class _class);

    /**
     *
     */
    void popBackStack();

}