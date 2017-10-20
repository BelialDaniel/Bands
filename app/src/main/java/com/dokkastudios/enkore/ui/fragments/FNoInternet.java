package com.dokkastudios.enkore.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gb.dokkastudios.enkor.R;

import com.dokkastudios.enkore.fragment.Fragments;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
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
        mCallbackMA.onCheckInternet();
    }
}