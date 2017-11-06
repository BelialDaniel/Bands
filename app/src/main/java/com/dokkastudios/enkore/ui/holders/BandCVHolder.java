package com.dokkastudios.enkore.ui.holders;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dokkastudios.enkore.listeners.OnBandListClick;
import com.gb.dokkastudios.enkor.R;

import Classes.Band;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by BelialDaniel on 8/26/17.
 */

public class BandCVHolder extends RecyclerView.ViewHolder implements View.OnClickListener, Callback
{
    public View mBandCardView;
    public TextView mTxtCVBandTitle;
    public TextView mTxtCVBandLocation;
    public ImageView mImageBackBand;

    private FloatingActionButton mFloatinABLikeBand;

    public Band mBand;
    public OnBandListClick mOnBandListClickListener;

    public BandCVHolder(View view)
    {
        super(view);
        mBandCardView = view;
        mTxtCVBandTitle = mBandCardView.findViewById(R.id._txtCVBandTitle);
        mTxtCVBandLocation = mBandCardView.findViewById(R.id._txtCVBandLocation);
        mImageBackBand = mBandCardView.findViewById(R.id._imageCVBandImage);
        mBandCardView.setOnClickListener(this);

        mFloatinABLikeBand = view.findViewById(R.id._floatingABLikeBand);
        mFloatinABLikeBand.setOnClickListener(this);
    }

    @Override
    public String toString()
    {
        return super.toString() + " '" + mTxtCVBandTitle.getText() + "'";
    }

    @Override
    public void onClick(View view)
    {
        //Log.i("Dimenciones Image", "W : " + mImageBackBand.getWidth() + " H : " + mImageBackBand.getHeight());
        if(view instanceof FloatingActionButton)
        {
            Toast.makeText(mBandCardView.getContext(), "Liked ", Toast.LENGTH_SHORT).show();
        }
        else //if (view.equals(mBandCardView))
        {
            if (mOnBandListClickListener != null)
                mOnBandListClickListener.onBandListClicked(mBand, mBandCardView);
        }
    }

    @Override
    public void onSuccess()
    {}

    @Override
    public void onError()
    {
        Picasso.with(mBandCardView.getContext())
                .load(R.drawable.no_image)
                .resize(688, 360)
                .centerCrop()
                .into(mImageBackBand);
    }
}