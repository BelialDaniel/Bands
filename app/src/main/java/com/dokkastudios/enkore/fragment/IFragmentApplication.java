package com.dokkastudios.enkore.fragment;

import android.support.v4.app.FragmentManager;

import com.dokkastudios.enkore.fragment.Fragments;

/**
 * Created by BelialDaniel on 10/20/17.
 */

@Deprecated
public interface IFragmentApplication
{
    /**
     *
     * @param contenLayoutID
     * @param _class
     * @return
     */
    void commit(int contenLayoutID, Class _class);

    /**
     *
     * @param contenLayoutID
     * @param _class
     * @param enterAnimation
     * @param exitAnimation
     * @return
     */
    void commit(int contenLayoutID, Class _class, int enterAnimation, int exitAnimation);

    /**
     *
     * @param contenLayoutID
     * @param _class
     * @param enterAnimation
     * @param exitAnimation
     * @param popEnter
     * @param popExit
     * @return
     */
    void commit(int contenLayoutID, Class _class, int enterAnimation, int exitAnimation, int popEnter, int popExit);

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