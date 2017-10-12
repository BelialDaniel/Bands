package Classes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by BelialDaniel on 9/8/17.
 */

public class PagerIndicators implements ViewPager.OnPageChangeListener
{
    private Context mContext = null;

    private int mCountIndicators = 0;

    private LinearLayout mLayoutPagerIndicator = null;
    private ImageView[] mIndicators = null;

    private Drawable mUnselectedIndicator = null;
    private Drawable mSelectedIndicator = null;

    /**
     *
     * @param _context
     * @param _layoutPagerDotIndicator
     * @param _countIndicators
     */
    public PagerIndicators(Context _context, LinearLayout _layoutPagerDotIndicator, int _countIndicators)
    {
        mContext = _context;
        mLayoutPagerIndicator = _layoutPagerDotIndicator;
        mCountIndicators = _countIndicators;
    }

    /**
     *
     * @param _defaultIndicator
     * @param _selectedIndicator
     * @param _theme
     */
    public void setIndicators(int _defaultIndicator, int _selectedIndicator, @Nullable Resources.Theme _theme)
    {
        mIndicators = new ImageView[mCountIndicators];

        mUnselectedIndicator = mContext.getResources().getDrawable(_defaultIndicator, _theme);
        mSelectedIndicator = mContext.getResources().getDrawable(_selectedIndicator, _theme);

        for(int i = 0; i < mCountIndicators; i++)
        {
            mIndicators[i] = new ImageView(mContext);
            mIndicators[i].setImageDrawable(mUnselectedIndicator);

            LinearLayout.LayoutParams _params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            _params.setMargins(4, 0, 4, 0);

            mLayoutPagerIndicator.addView(mIndicators[i], _params);
        }

        mIndicators[0].setImageDrawable(mSelectedIndicator);
    }

    private void setIndicatorSelected(int _position)
    {
        for (int i = 0; i < mCountIndicators; i++)
            mIndicators[i].setImageDrawable(mUnselectedIndicator);
        mIndicators[_position].setImageDrawable(mSelectedIndicator);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }

    @Override
    public void onPageSelected(int position)
    {
        setIndicatorSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }
}