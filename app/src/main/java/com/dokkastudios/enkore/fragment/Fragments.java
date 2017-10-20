package com.dokkastudios.enkore.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dokkastudios.enkore.listeners.CallbackMainApp;
import com.dokkastudios.enkore.listeners.CallbackMainUser;
import com.dokkastudios.enkore.ui.activities.MainApp;
import com.dokkastudios.enkore.ui.activities.MainUser;

/**
 * Created by BelialDaniel on 8/30/17.
 * A simple {@link Fragment} subclass.
 */

public abstract class Fragments extends Fragment implements IFragment
{
    protected CallbackMainApp mCallbackMA = null;
    protected CallbackMainUser mCallBackMU = null;

    private String mFragmentTag = "";

    protected View mSView = null;
    protected Context mSContext = null;

    public Fragments() {}

    @Override
    public void setFragmentTag(String tag)
    {
        mFragmentTag = tag;
    }

    @Override
    public String getFragmentTag()
    {
        return mFragmentTag;
    }

    @Override
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

    @Override
    @Deprecated
    public void registerListener(Object _callback)
    {
        if(_callback instanceof MainApp)
            mCallbackMA = (CallbackMainApp) _callback;
        else if(_callback instanceof MainUser)
            mCallBackMU = (CallbackMainUser) _callback;

    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mSContext = context;

        if(context instanceof MainUser)
            mCallBackMU = (CallbackMainUser) context;
        else if(context instanceof MainApp)
            mCallbackMA = (CallbackMainApp) context;
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