package com.dokkastudios.enkor.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gb.dokkastudios.enkor.Avtivities.MainApp;
import com.gb.dokkastudios.enkor.Avtivities.MainUser;

import com.dokkastudios.enkor.listeners.CallbackMainApp;
import com.dokkastudios.enkor.listeners.CallbackMainUser;

/**
 * Created by BelialDaniel on 8/30/17.
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */

public class Fragments extends android.support.v4.app.Fragment
{
    private int mColumnCount = 1;

    protected CallbackMainApp mCallbackMA = null;
    protected CallbackMainUser mCallBackMU = null;

    public String FRAGMENT_TAG = "";

    protected View mSView = null;
    protected Context mSContext = null;

    public Fragments() {}

    /**
     *
     * @param _name
     */
    @Deprecated
    public void setName(String _name)
    {
        Bundle args = new Bundle();
        this.setArguments(args);
    }

    /**
     *
     * @return
     */
    public RecyclerView getRecyclerView()
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
     * @param _callback
     */
    @Deprecated
    public void RegisterListener(Object _callback)
    {
        if(_callback instanceof MainApp)
            this.mCallbackMA = (CallbackMainApp) _callback;
        else if(_callback instanceof MainUser)
            this.mCallBackMU = (CallbackMainUser) _callback;

    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mSContext = context;

        if(context instanceof MainUser)
            this.mCallBackMU = (CallbackMainUser) context;
        else if(context instanceof MainApp)
            this.mCallbackMA = (CallbackMainApp) context;
        else
            throw new RuntimeException(context.toString() + " must implement CallbackMainApp or CallbackMainUser");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        if(mCallBackMU != null)
            mCallBackMU = null;

        if(mCallbackMA != null)
            mCallbackMA = null;
    }
}