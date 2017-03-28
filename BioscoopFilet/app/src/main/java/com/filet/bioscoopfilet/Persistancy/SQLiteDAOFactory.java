package com.filet.bioscoopfilet.Persistancy;

import android.content.Context;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteDAOFactory implements DAOFactory {

    private Context context;

    public SQLiteDAOFactory(Context context)
    {
        this.context = context;
    }

    @Override
    public FeedbackDAO createFeedbackDAO() {
        return new SQLiteFeedbackDAO(context);
    }
}
