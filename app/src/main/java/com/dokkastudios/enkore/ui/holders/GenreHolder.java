package com.dokkastudios.enkore.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dokkastudios.enkore.ui.fragments.dummy.DummyContent;
import com.gb.dokkastudios.enkor.R;

/**
 * Created by BelialDaniel on 10/18/17.
 */

public class GenreHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final View mView;
    public final TextView mIdView;
    public final TextView mContentView;
    public DummyContent.DummyItem mItem;

    public GenreHolder(View view)
    {
        super(view);
        mView = view;
        mIdView =  view.findViewById(R.id.id);
        mContentView =  view.findViewById(R.id.content);
        mView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {

    }

    @Override
    public String toString()
    {
        return super.toString() + " '" + mContentView.getText() + "'";
    }
}