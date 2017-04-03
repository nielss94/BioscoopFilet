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

    @Override
    public ReviewDAO createReviewDAO() {
        return new SQLiteReviewDAO(context);
    }

    @Override
    public ShowDAO createShowDAO() {
        return new SQLiteShowDAO(context);
    }

    @Override
    public TheaterDAO createTheaterDAO() {
        return new SQLiteTheaterDAO(context);
    }
    @Override
    public TicketDAO createTicketDAO() {
        return new SQLiteTicketDAO(context);
    }

    @Override
    public VisitorDAO createVisitorDAO() {
        return new SQLiteVisitorDAO(context);
    }

    @Override
    public FilmDAO createFilmDAO() {
        return new SQLiteFilmDAO(context);
    }

    @Override
    public ActorDAO createActorDAO() {
        return new SQLiteActorDAO(context);
    }

    @Override
    public CinemaDAO createCinemaDAO() {
        return new SQLiteCinemaDAO(context);
    }


}
