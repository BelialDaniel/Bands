package com.gb.dokkastudios.garagebands.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gb.dokkastudios.garagebands.R;

import Classes.Event;
import Interfaces.CallbackMainUser;

/**
 * Created by BelialDaniel on 8/28/17.
 */

public class EventCardVHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public View mEventCardView;
    public TextView mTxtCVEventTitle;
    public TextView mTxtCVEventDate;
    public ImageView mImageBackEvent;

    public Event mEvent;
    public CallbackMainUser onEventLisClickListener;

    public EventCardVHolder(View _view)
    {
        super(_view);
        mEventCardView = _view;
        mTxtCVEventTitle = mEventCardView.findViewById(R.id._txtCVEventTitle);
        mImageBackEvent = mEventCardView.findViewById(R.id._imageCVEventImage);
        mTxtCVEventDate = mEventCardView.findViewById(R.id._txtCVEventDate);
        mEventCardView.setOnClickListener(this);
    }

    @Override
    public String toString()
    {
        return super.toString() + " '" + mTxtCVEventTitle.getText() + "'";
    }

    @Override
    public void onClick(View view)
    {
        //Log.i("Dimenciones Image", "W : " + mImageBackEvent.getWidth() + " H : " + mImageBackEvent.getHeight());
        if(onEventLisClickListener != null)
            onEventLisClickListener.onEventListClicked(mEvent, mEventCardView);
    }
}