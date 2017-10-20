package com.dokkastudios.enkore.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dokkastudios.enkore.listeners.CallbackMainApp;
import com.dokkastudios.enkore.listeners.CallbackMainUser;
import com.dokkastudios.enkore.ui.fragments.dummy.DummyContent.DummyItem;
import com.dokkastudios.enkore.ui.holders.GenreHolder;
import com.gb.dokkastudios.enkor.R;

import java.util.List;

public class GenreMarkerAdapter extends RecyclerView.Adapter<GenreHolder>
{
    private final List<DummyItem> mValues;
    private final CallbackMainApp mListener;

    private View view = null;

    public GenreMarkerAdapter(List<DummyItem> items, CallbackMainApp listener)
    {
        mValues = items;
        mListener = listener;
    }

    @Override
    public GenreHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_genre, parent, false);
        return new GenreHolder(view);
    }

    @Override
    public void onBindViewHolder(final GenreHolder holder, int position)
    {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount()
    {
        return mValues.size();
    }
}