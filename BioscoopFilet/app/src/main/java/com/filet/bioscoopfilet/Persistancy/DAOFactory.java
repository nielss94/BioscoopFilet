package com.filet.bioscoopfilet.Persistancy;


/**
 * Created by Niels on 3/28/2017.
 */

public interface DAOFactory {

    FeedbackDAO createFeedbackDAO();
//    ReviewDAO createReviewDAO();
//    ShowDAO createShowDAO();
//    TheaterDAO createTheaterDAO();
//    TicketDAO createTicketDAO();
    VisitorDAO createVisitorDAO();
//    FilmDAO createFilmDAO();
    ActorDAO createActorDAO();
}
