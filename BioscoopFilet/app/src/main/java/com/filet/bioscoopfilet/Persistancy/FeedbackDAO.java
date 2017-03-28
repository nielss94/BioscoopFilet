package com.filet.bioscoopfilet.Persistancy;

import com.filet.bioscoopfilet.DomainModel.Feedback;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public interface FeedbackDAO {

    ArrayList<Feedback> selectData();
    void insertData(Feedback feedback);
}
