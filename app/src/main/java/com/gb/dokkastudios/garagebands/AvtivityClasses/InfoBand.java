package com.gb.dokkastudios.garagebands.AvtivityClasses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.gb.dokkastudios.garagebands.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import Classes.Band;
import Classes.StoreResources;
import Interfaces.OnStageServices;

public class InfoBand extends AppCompatActivity implements Callback
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_info_band);

        setReferences();
    }

    private void setReferences()
    {
        Band band = StoreResources.getBand();
        if(band == null)
        {
            noImage();
        }
        else
        {
            Picasso.with(getApplicationContext())
                    .load(OnStageServices.IMAGES_URL + band.getImage())
                    .into((ImageView) findViewById(R.id._imageViewInfoBandAct), this);
        }
    }

    private void noImage()
    {
        Picasso.with(getApplicationContext())
                .load(R.drawable.no_image)
                .into((ImageView) findViewById(R.id._imageViewInfoBandAct));
    }

    @Override
    public void onSuccess()
    {

    }

    @Override
    public void onError()
    {
        noImage();
    }
}