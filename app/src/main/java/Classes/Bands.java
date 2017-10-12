package Classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BelialDaniel on 8/10/17.
 */

public class Bands
{
    @SerializedName("Bands")
    private List<Band> mBands = null;

    public List<Band> getBands()
    {
        return mBands;
    }
}