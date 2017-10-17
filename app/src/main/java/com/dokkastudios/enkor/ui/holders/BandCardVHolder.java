package com.dokkastudios.enkor.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gb.dokkastudios.enkor.R;

import Classes.Band;
import com.dokkastudios.enkor.listeners.CallbackMainUser;

/**
 * Created by BelialDaniel on 8/26/17.
 */

public class BandCardVHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public View mBandCardView;
    public TextView mTxtCVBandTitle;
    public TextView mTxtCVBandLocation;
    public ImageView mImageBackBand;

    public Band mBand;
    public CallbackMainUser onBandLisClickListener;

    public BandCardVHolder(View view)
    {
        super(view);
        mBandCardView = view;
        mTxtCVBandTitle = mBandCardView.findViewById(R.id._txtCVBandTitle);
        mTxtCVBandLocation = mBandCardView.findViewById(R.id._txtCVBandLocation);
        mImageBackBand = mBandCardView.findViewById(R.id._imageCVBandImage);
        mBandCardView.setOnClickListener(this);
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
        if(onBandLisClickListener != null)
            onBandLisClickListener.onBandListClicked(mBand, mBandCardView);
    }
}