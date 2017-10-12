package Interfaces;

import Classes.DB;

/**
 * Created by BelialDaniel on 10/10/17.
 */

public interface RequestTo<T>
{
    /**
     *
     * @param request
     */
    void requestTo(T request);
}