package com.gb.dokkastudios.garagebands.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gb.dokkastudios.garagebands.Adapters.CardVBandAdapter;
import com.gb.dokkastudios.garagebands.R;

import Classes.Bands;
import Classes.Fragments;
import Classes.RetrofitInstance;
import Classes.StoreResources;
import Interfaces.GarageBandsServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FListBands extends Fragments implements Callback<Bands>
{
    //private CardVBandAdapter _mCardVBandAdapter = null;
    public FListBands() {}

    // TODO: Customize parameter initialization
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mSView = inflater.inflate(R.layout.f_list_bands, container, false);
        return mSView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        if(StoreResources.getBands() == null)
            BandsFromDB();
        else
            AsignBandsToList();
    }

    private void BandsFromDB()
    {
        Retrofit _retrofit = RetrofitInstance.getRetrofit();
        GarageBandsServices _serviceGB = _retrofit.create(GarageBandsServices.class);
        Call<Bands> _bandReq = _serviceGB.getBands();
        _bandReq.enqueue(this);
    }

    private void AsignBandsToList()
    {
        RecyclerView _reView = getRecyclerView();
        if(_reView != null)
            _reView.setAdapter(new CardVBandAdapter(StoreResources.getBands().getBands(), mCallBackMU));
    }

    @Override
    public void onResponse(Call<Bands> call, Response<Bands> _response)
    {
        if(_response.isSuccessful())
        {
            if(_response.body() != null)
                if(_response.body().getBands() != null)
                {
                    StoreResources.setBands(_response.body());
                    AsignBandsToList();
                }
        }
        else
            Toast.makeText(getContext(), "[BandsFromDB] Something went wrong : " + _response.code(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Call<Bands> call, Throwable _t)
    {
        Toast.makeText(getContext(), "[BandsFromDB] Something went wrong : " + _t.fillInStackTrace(), Toast.LENGTH_LONG).show();
    }
}