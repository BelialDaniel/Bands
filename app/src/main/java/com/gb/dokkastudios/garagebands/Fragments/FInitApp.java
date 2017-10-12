package com.gb.dokkastudios.garagebands.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gb.dokkastudios.garagebands.R;

import Classes.Fragments;

/**
 * A simple {@link Fragment} subclass.
 */
public class FInitApp extends Fragments implements View.OnClickListener
{
    public FInitApp() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mSView = inflater.inflate(R.layout.f_init_app, container, false);
        return mSView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        setReferences();
    }

    private void setReferences()
    {
        mSView.findViewById(R.id._btnMAppCreateAcount).setOnClickListener(this);
        mSView.findViewById(R.id._txtMAppLogIn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view instanceof Button)
           mCallbackMA.SingUp();
        else if (view instanceof TextView)
            mCallbackMA.LogIn();
    }
}