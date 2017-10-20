package com.dokkastudios.enkore.fragment;

import android.support.v7.widget.RecyclerView;

/**
 * Created by BelialDaniel on 10/20/17.
 */

public interface IFragment
{
    /**
     *
     * @param tag
     */
    void setFragmentTag(String tag);

    /**
     *
     * @return
     */
    String getFragmentTag();

    /**
     *
     * @param mColumnCount
     * @return
     */
    RecyclerView getRecyclerView(int mColumnCount);

    /**
     *
     * @param _callback
     */
    void registerListener(Object _callback);
}