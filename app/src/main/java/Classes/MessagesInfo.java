package Classes;

/**
 * Created by BelialDaniel on 8/16/17.
 */

public class MessagesInfo
{
    private boolean _error;
    private String _sMessage;
    private String _eMessage;
    private int    _errorCod;

    public int getErrorCode()
    {
        return _errorCod;
    }

    public boolean getError()
    {
        return _error;
    }

    public String getSucMessage()
    {
        return _sMessage;
    }

    public String getErrMessage()
    {
        return _eMessage;
    }
}