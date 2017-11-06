package com.dokkastudios.enkore.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dokkastudios.enkore.ui.adapters.BandCVAdapter;
import com.gb.dokkastudios.enkor.R;

import com.dokkastudios.enkore.fragment.Fragments;
import com.dokkastudios.enkore.util.StoreResources;

public class FListBands extends Fragments
{
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

        if(StoreResources.getBands().getBands() != null)
            AsignBandsToList();
    }

    private void AsignBandsToList()
    {
        RecyclerView recyclerView = getRecyclerView(1);
        if(recyclerView != null)
            recyclerView.setAdapter(new BandCVAdapter(StoreResources.getBands().getBands(), mOnBandListClick));
    }
}