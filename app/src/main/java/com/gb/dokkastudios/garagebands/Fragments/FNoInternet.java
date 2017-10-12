package com.gb.dokkastudios.garagebands.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gb.dokkastudios.garagebands.R;

import Classes.Fragments;

/**
 * A simple {@link Fragment} subclass.
 */
public class FNoInternet extends Fragments implements View.OnClickListener
{
    public FNoInternet() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mSView = inflater.inflate(R.layout.f_no_internet, container, false);
        return mSView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        mSView.findViewById(R.id._btnMAppNoInternet).setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        mCallbackMA.CheckInternet();
    }
}