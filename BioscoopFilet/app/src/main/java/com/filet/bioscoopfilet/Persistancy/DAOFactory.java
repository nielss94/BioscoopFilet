package com.filet.bioscoopfilet.Persistancy;

import com.filet.bioscoopfilet.DomainModel.Feedback;

/**
 * Created by Niels on 3/28/2017.
 */

public interface DAOFactory {

    public FeedbackDAO createFeedbackDAO();
}
