package com.gb.dokkastudios.garagebands.Adapters;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gb.dokkastudios.garagebands.Holders.BandCardVHolder;
import com.gb.dokkastudios.garagebands.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Classes.Band;
import Interfaces.CallbackMainUser;

/**
 * Created by BelialDaniel on 8/26/17.
 */
public class CardVBandAdapter extends RecyclerView.Adapter<BandCardVHolder>
{
    private final List<Band> mBands;
    private BandCardVHolder mBandHolder = null;
    private CallbackMainUser onBandLisClickListener = null;

    private View _view = null;

    public CardVBandAdapter(List<Band> _bands, CallbackMainUser _callbackItemClick)
    {
        this.mBands = _bands;
        this.onBandLisClickListener = _callbackItemClick;
    }

    @Override
    public BandCardVHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_band, parent, false);
        return new BandCardVHolder(_view);
    }

    @Override
    public void onBindViewHolder(final BandCardVHolder holder, int position)
    {
        mBandHolder = holder;

        Picasso.with(mBandHolder.mBandCardView.getContext())
                .load(R.drawable.no_image)
                .resize(688, 360)
                .centerCrop()
                .into(mBandHolder.mImageBackBand);

        //ViewCompat.setTransitionName(mBandHolder.mImageBackBand, mBands.get(position).getBandName());

        mBandHolder.mBand = mBands.get(position);
        mBandHolder.mTxtCVBandTitle.setText(mBands.get(position).getBandName());
        mBandHolder.mTxtCVBandLocation.setText(mBands.get(position).getCity());

        mBandHolder.onBandLisClickListener = onBandLisClickListener;
    }

    @Override
    public int getItemCount()
    {
        return mBands.size();
    }
}