package com.dokkastudios.enkor.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gb.dokkastudios.enkor.R;

import Classes.Song;

/**
 * Created by BelialDaniel on 9/5/17.
 */

public class SongHolder extends RecyclerView.ViewHolder
{
    public final View _mView;
    public final TextView _mIdView;
    public final TextView _mContentView;
    public Song _mSong = null;

    public SongHolder(View view)
    {
        super(view);
        _mView = view;
        _mIdView = view.findViewById(R.id.id);
        _mContentView =  view.findViewById(R.id.content);
    }

    @Override
    public String toString()
    {
        return super.toString() + " '" + _mContentView.getText() + "'";
    }
}