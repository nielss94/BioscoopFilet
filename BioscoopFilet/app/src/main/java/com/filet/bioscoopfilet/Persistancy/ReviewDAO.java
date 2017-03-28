package com.filet.bioscoopfilet.Persistancy;

import com.filet.bioscoopfilet.DomainModel.Review;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public interface ReviewDAO {

    ArrayList<Review> selectData();
    void insertData(Review review);
}

