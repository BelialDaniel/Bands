package com.dokkastudios.enkore.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dokkastudios.enkore.ui.holders.BandCVHolder;
import com.gb.dokkastudios.enkor.R;

import com.dokkastudios.enkore.listeners.CallbackMainUser;
import com.dokkastudios.enkore.services.EnkorServices;
import com.squareup.picasso.Picasso;

import java.util.List;

import Classes.Band;

/**
 * Created by BelialDaniel on 8/26/17.
 */
public class BandCVAdapter extends RecyclerView.Adapter<BandCVHolder>
{
    private final List<Band> mBands;
    private BandCVHolder mBandHolder = null;
    private CallbackMainUser onBandLisClickListener = null;

    private View _view = null;

    public BandCVAdapter(List<Band> _bands, CallbackMainUser _callbackItemClick)
    {
        this.mBands = _bands;
        this.onBandLisClickListener = _callbackItemClick;
    }

    @Override
    public BandCVHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_band, parent, false);
        return new BandCVHolder(_view);
    }

    @Override
    public void onBindViewHolder(final BandCVHolder holder, int position)
    {
        mBandHolder = holder;
        //ViewCompat.setTransitionName(mBandHolder.mImageBackBand, mBands.get(position).getBandName());

        mBandHolder.mBand = mBands.get(position);
        mBandHolder.mTxtCVBandTitle.setText(mBands.get(position).getBandName());
        mBandHolder.mTxtCVBandLocation.setText(mBands.get(position).getCity());

        Picasso.with(mBandHolder.mBandCardView.getContext())
                .load(EnkorServices.IMAGES_URL + mBands.get(position).getImage())
                .resize(688, 360)
                .centerCrop()
                .into(mBandHolder.mImageBackBand, mBandHolder);

        mBandHolder.onBandLisClickListener = onBandLisClickListener;
    }

    @Override
    public int getItemCount()
    {
        return mBands.size();
    }
}