package com.gb.dokkastudios.enkor.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.gb.dokkastudios.enkor.Adapters.CardVEventAdapter;
import com.gb.dokkastudios.enkor.R;

import Classes.Events;
import com.dokkastudios.enkor.fragment.Fragments;
import com.dokkastudios.enkor.services.util.RetrofitInstance;
import com.dokkastudios.enkor.util.StoreResources;
import com.dokkastudios.enkor.services.EnkorServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FListEvents extends Fragments implements Callback<Events>
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
        if(StoreResources.getEvents() == null)
            BandsEventsFromDB();
        else
            AsignEventsToList();
    }

    private void AsignEventsToList()
    {
        RecyclerView _reView = getRecyclerView();
        if(_reView != null)
            _reView.setAdapter(new CardVEventAdapter(StoreResources.getEvents().getEvents(), mCallBackMU));
    }

    private void BandsEventsFromDB()
    {
        Retrofit _retrofit = RetrofitInstance.getRetrofit();

        EnkorServices _serviceGB = _retrofit.create(EnkorServices.class);
        Call<Events> _eventsReq = _serviceGB.getEvents();
        _eventsReq.enqueue(this);
    }

    @Override
    public void onResponse(Call<Events> call, Response<Events> _response)
    {
        if(_response.isSuccessful())
        {
            if(_response.body() != null)
                if(_response.body().getEvents() != null) {
                    StoreResources.setEvents(_response.body());
                    AsignEventsToList();
                }
        }
        else
            Toast.makeText(getContext(), "[BandsEventsFromDB] Something went wrong : " + _response.code(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Call<Events> call, Throwable _t)
    {
        Toast.makeText(getContext(), "[BandsEventsFromDB] Something went wrong : " + _t.fillInStackTrace(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mCallBackMU = null;
    }
}