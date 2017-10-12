package Classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BelialDaniel on 8/10/17.
 */

public class Events
{
    @SerializedName("Events")
    private List<Event> mEvents = null;

    public List<Event> getEvents()
    {
        return mEvents;
    }
}