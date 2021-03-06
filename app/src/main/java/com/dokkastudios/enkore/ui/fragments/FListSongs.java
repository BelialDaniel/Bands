package com.dokkastudios.enkore.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dokkastudios.enkore.ui.adapters.ListSongsAdapter;

import com.gb.dokkastudios.enkor.R;

import java.util.List;

import com.dokkastudios.enkore.fragment.Fragments;
import Classes.Song;
import com.dokkastudios.enkore.util.StoreResources;

public class FListSongs extends Fragments
{
    private List<Song> _mSongs = null;

    public FListSongs() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(StoreResources.getBands() != null)
            _mSongs = StoreResources.getBands().getBands().get(0).getSongs();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mSView = inflater.inflate(R.layout.f_list_songs, container, false);
        return mSView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        if(StoreResources.getBands() != null)
            AsignSongsToList();
    }

    private void AsignSongsToList()
    {
        RecyclerView _reView = getRecyclerView(1);
        if(_reView != null)
            _reView.setAdapter(new ListSongsAdapter(_mSongs, new Object()));
    }
}