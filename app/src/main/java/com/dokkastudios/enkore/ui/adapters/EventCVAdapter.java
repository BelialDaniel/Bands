package com.dokkastudios.enkore.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dokkastudios.enkore.database.DataBase;
import com.dokkastudios.enkore.database.util.RequestsToTheDataBase;
import com.dokkastudios.enkore.listeners.OnEventListClick;
import com.dokkastudios.enkore.ui.holders.EventCVHolder;
import com.dokkastudios.enkore.util.RequestTo;
import com.gb.dokkastudios.enkor.R;

import com.squareup.picasso.Picasso;

import java.util.List;

import Classes.Event;

/**
 * Created by BelialDaniel on 8/28/17.
 */

public class EventCVAdapter extends RecyclerView.Adapter<EventCVHolder>
{
    private final List<Event> mEvents;
    private EventCVHolder mEventHolder = null;
    private OnEventListClick mOnEventListClickListener = null;

    public EventCVAdapter(List<Event> _events, OnEventListClick onEventListClickListener)
    {
        this.mEvents = _events;
        this.mOnEventListClickListener = onEventListClickListener;
    }

    @Override
    public EventCVHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_event, parent, false);
        return new EventCVHolder(_view);
    }

    @Override
    public void onBindViewHolder(final EventCVHolder holder, int position)
    {
        mEventHolder = holder;

        Picasso.with(mEventHolder.mEventCardView.getContext())
                .load(R.drawable.no_image)
                .resize(688, 360)
                .centerCrop()
                .into(mEventHolder.mImageBackEvent, mEventHolder);

        mEventHolder.mEvent = mEvents.get(position);
        mEventHolder.mTxtCVEventTitle.setText(mEvents.get(position).getName());

        RequestsToTheDataBase.withContext(mEventHolder.mEventCardView.getContext()).requestTo(new RequestTo<DataBase>()
        {
            @Override
            public void requestTo(DataBase request)
            {
                if(request.existsEvent(mEventHolder.mEvent.getID()))
                    mEventHolder.mFloatingABSaveEvent.setImageResource(R.drawable.ic_turned_in_black);
            }
        });

        mEventHolder.mOnEventListClickListener = mOnEventListClickListener;
    }

    @Override
    public int getItemCount()
    {
        return mEvents.size();
    }
}