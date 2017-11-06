package com.dokkastudios.enkore.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dokkastudios.enkore.listeners.CallbackMainApp;
import com.dokkastudios.enkore.listeners.OnMapEventClick;
import com.dokkastudios.enkore.listeners.OnBandListClick;
import com.dokkastudios.enkore.listeners.OnEventListClick;
import com.dokkastudios.enkore.ui.activities.MainApp;
import com.dokkastudios.enkore.ui.activities.MainUser;

/**
 * Created by BelialDaniel on 8/30/17.
 * A simple {@link Fragment} subclass.
 */

public abstract class Fragments extends Fragment
{
    protected CallbackMainApp mCallbackMA = null;

    protected OnMapEventClick mOnMapEventClick = null;
    protected OnEventListClick mOnEventListClick = null;
    protected OnBandListClick  mOnBandListClick  = null;

    protected View mSView = null;
    protected Context mSContext = null;

    public Fragments() {}

    /**
     *
     * @param mColumnCount
     * @return
     */
    public RecyclerView getRecyclerView(int mColumnCount)
    {
        RecyclerView recyclerView = null;
        if (mSView instanceof RecyclerView)
        {
            Context context = mSView.getContext();
            recyclerView = (RecyclerView) mSView;
            recyclerView.setHasFixedSize(true);

            if (mColumnCount <= 1)
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            else
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        return recyclerView;
    }

    /**
     *
     * @param context
     */
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mSContext = context;

        if(context instanceof MainUser)
        {
            mOnMapEventClick = (OnMapEventClick) context;

            mOnEventListClick = (OnEventListClick) context;
            mOnBandListClick = (OnBandListClick)  context;
        }
        else if(context instanceof MainApp)
        {
            mCallbackMA = (CallbackMainApp) context;
        }
        else
            throw new RuntimeException(context.toString() + " must implement CallbackMainApp or OnMapEventClick");
    }

    /**
     *
     */
    @Override
    public void onDetach()
    {
        super.onDetach();
        if(mOnMapEventClick != null)
            mOnMapEventClick = null;

        if(mCallbackMA != null)
            mCallbackMA = null;
    }
}