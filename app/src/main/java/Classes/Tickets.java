package Classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BelialDaniel on 8/10/17.
 */

public class Tickets
{
    @SerializedName("Tickets")
    private List<Ticket> _tickets = null;

    public List<Ticket> getTickets()
    {
        return _tickets;
    }
}