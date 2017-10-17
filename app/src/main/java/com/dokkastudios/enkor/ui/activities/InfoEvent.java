package com.dokkastudios.enkor.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.gb.dokkastudios.enkor.R;
import com.squareup.picasso.Picasso;

public class InfoEvent extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_info_event);
        setReferences();
    }

    private void setReferences()
    {
        Picasso.with(getApplicationContext())
                .load(R.drawable.no_image)
                .into((ImageView) findViewById(R.id._imageViewInfoEventAct));
    }
}