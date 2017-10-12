package Classes;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by BelialDaniel on 8/10/17.
 */

public class Ticket
{
    @SerializedName("ID")
    private int     _ticketID = -1;
    @SerializedName("Price")
    private double  _price = 0.0;
    @SerializedName("Date")
    private String  _dateTicket = "";

    @SerializedName("EventName")
    private String  _bandName = "";
    @SerializedName("Info")
    private String  _info = "";
    @SerializedName("EventDate")
    private String  _eventDate = "";
    @SerializedName("Address")
    private String  _address = "";
    @SerializedName("City")
    private String  _city = "";
    @SerializedName("State")
    private String  _state = "";

    public String getBandName()
    {
        return _bandName;
    }

    public int getTicketID()
    {
        return _ticketID;
    }

    public double getPrice()
    {
        return _price;
    }

    public String getDateTicket()
    {
        return _dateTicket;
    }

    public String getCity()
    {
        return _city;
    }

    public String getAddres()
    {
        return _address;
    }
}
