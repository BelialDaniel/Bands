package com.dokkastudios.enkore.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.dokkastudios.enkore.fragment.Fragments;
import com.dokkastudios.enkore.services.EnkorServices;
import com.dokkastudios.enkore.services.util.RetrofitInstance;
import com.dokkastudios.enkore.ui.adapters.EventCVAdapter;

import com.gb.dokkastudios.enkor.R;

import Classes.Events;

import com.dokkastudios.enkore.util.StoreResources;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FListEvents extends Fragments
{
    public FListEvents() { }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mSView = inflater.inflate(R.layout.f_list_events, container, false);
        return mSView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        if(StoreResources.getEvents().getEvents() != null)
            AsignEventsToList();
    }

    private void AsignEventsToList()
    {
        RecyclerView _reView = getRecyclerView(1);
        if(_reView != null)
            _reView.setAdapter(new EventCVAdapter(StoreResources.getEvents().getEvents(), mCallBackMU));
    }
    @Override
    public void onDetach()
    {
        super.onDetach();
        mCallBackMU = null;
    }
}