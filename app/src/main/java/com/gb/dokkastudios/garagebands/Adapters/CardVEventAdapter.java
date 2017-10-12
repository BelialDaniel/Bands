package com.gb.dokkastudios.garagebands.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gb.dokkastudios.garagebands.Holders.EventCardVHolder;
import com.gb.dokkastudios.garagebands.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Classes.Event;
import Interfaces.CallbackMainUser;

/**
 * Created by BelialDaniel on 8/28/17.
 */

public class CardVEventAdapter extends RecyclerView.Adapter<EventCardVHolder>
{
    private final List<Event> mEvents;
    private EventCardVHolder mEventHolder = null;
    private CallbackMainUser onEventListClickListener = null;

    public CardVEventAdapter(List<Event> _events, CallbackMainUser _callbackItemClick)
    {
        this.mEvents = _events;
        this.onEventListClickListener = _callbackItemClick;
    }

    @Override
    public EventCardVHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_event, parent, false);
        return new EventCardVHolder(_view);
    }

    @Override
    public void onBindViewHolder(final EventCardVHolder holder, int position)
    {
        mEventHolder = holder;

        Picasso.with(mEventHolder.mEventCardView.getContext())
                .load(R.drawable.no_image)
                .resize(688, 360)
                .centerCrop()
                .into(mEventHolder.mImageBackEvent);

        mEventHolder.mEvent = mEvents.get(position);
        mEventHolder.mTxtCVEventTitle.setText(mEvents.get(position).getName());

        mEventHolder.onEventLisClickListener = onEventListClickListener;
    }

    @Override
    public int getItemCount()
    {
        return mEvents.size();
    }
}