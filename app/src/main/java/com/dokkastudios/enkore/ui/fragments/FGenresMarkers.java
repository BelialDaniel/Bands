package com.dokkastudios.enkore.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dokkastudios.enkore.fragment.Fragments;
import com.dokkastudios.enkore.ui.adapters.GenreMarkerAdapter;
import com.dokkastudios.enkore.ui.fragments.dummy.DummyContent;
import com.gb.dokkastudios.enkor.R;

public class FGenresMarkers extends Fragments
{
    public FGenresMarkers() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mSView = inflater.inflate(R.layout.f_genre_list, container, false);
        return mSView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        RecyclerView _reView = getRecyclerView(2);
        if(_reView != null)
            _reView.setAdapter(new GenreMarkerAdapter(DummyContent.ITEMS, mCallbackMA));
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }
}