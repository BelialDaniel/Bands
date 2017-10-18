package com.dokkastudios.enkore.ui.holders;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dokkastudios.enkore.database.DB;
import com.dokkastudios.enkore.database.util.RequestsToTheDataBase;
import com.dokkastudios.enkore.util.RequestTo;
import com.gb.dokkastudios.enkor.R;

import Classes.Event;
import com.dokkastudios.enkore.listeners.CallbackMainUser;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by BelialDaniel on 8/28/17.
 */

public class EventCVHolder extends RecyclerView.ViewHolder implements View.OnClickListener, Callback
{
    public View mEventCardView;
    public TextView mTxtCVEventTitle;
    public TextView mTxtCVEventDate;
    public ImageView mImageBackEvent;

    public FloatingActionButton mFloatingABSaveEvent;

    public Event mEvent;
    public CallbackMainUser onEventLisClickListener;

    public EventCVHolder(View _view)
    {
        super(_view);
        mEventCardView = _view;
        mTxtCVEventTitle = mEventCardView.findViewById(R.id._txtCVEventTitle);
        mImageBackEvent = mEventCardView.findViewById(R.id._imageCVEventImage);
        mTxtCVEventDate = mEventCardView.findViewById(R.id._txtCVEventDate);
        mEventCardView.setOnClickListener(this);

        mFloatingABSaveEvent = mEventCardView.findViewById(R.id._floatingABSaveEvent);
        mFloatingABSaveEvent.setOnClickListener(this);
    }

    private void SaveEvent()
    {
        RequestsToTheDataBase.contextToGetDataBase(mEventCardView.getContext()).requestTo(new RequestTo<DB>()
        {
            @Override
            public void requestTo(DB request)
            {
                if(!request.existsEvent(mEvent.getID()))
                {
                    if(request.saveEvent(mEvent))
                        mFloatingABSaveEvent.setImageResource(R.drawable.ic_turned_in_black);
                }
                else
                {
                    if(request.removeEvent(mEvent.getID()))
                        mFloatingABSaveEvent.setImageResource(R.drawable.ic_turned_in_not_black);
                }
            }
        });
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
        if(view instanceof FloatingActionButton)
        {
            SaveEvent();
        }
        else
        {
            if (onEventLisClickListener != null)
                onEventLisClickListener.onEventListClicked(mEvent, mEventCardView);
        }
    }

    @Override
    public void onSuccess()
    {}

    @Override
    public void onError()
    {
        Picasso.with(mEventCardView.getContext())
                .load(R.drawable.no_image)
                .resize(688, 360)
                .centerCrop()
                .into(mImageBackEvent);
    }
}