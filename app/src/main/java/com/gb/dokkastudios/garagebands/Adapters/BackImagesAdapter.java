package com.gb.dokkastudios.garagebands.Adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gb.dokkastudios.garagebands.R;
import com.squareup.picasso.Picasso;

/**
 * Created by BelialDaniel on 9/7/17.
 */

public class BackImagesAdapter extends PagerAdapter
{
    private int[] mBackImages = null;
    private Context mContext = null;

    public BackImagesAdapter(Context _context, int[] _backImages)
    {
        mContext = _context;
        mBackImages = _backImages;
    }

    @Override
    public int getCount()
    {
        return mBackImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.back_img, container, false);

        ImageView _imgView = view.findViewById(R.id._imageVBackImage);
        Picasso.with(view.getContext())
                .load(mBackImages[position])
                .into(_imgView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        super.destroyItem(container, position, object);
        container.removeView((ConstraintLayout) object);
    }
}