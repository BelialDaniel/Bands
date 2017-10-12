package com.gb.dokkastudios.garagebands.AvtivityClasses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.gb.dokkastudios.garagebands.R;
import com.squareup.picasso.Picasso;

public class InfoBand extends AppCompatActivity
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
        Picasso.with(getApplicationContext())
                .load(R.drawable.no_image)
                .into((ImageView) findViewById(R.id._imageViewInfoBandAct));
    }
}