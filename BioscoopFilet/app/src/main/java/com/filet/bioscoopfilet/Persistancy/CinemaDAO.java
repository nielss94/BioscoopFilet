package com.filet.bioscoopfilet.Persistancy;

import com.filet.bioscoopfilet.DomainModel.Cinema;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public interface CinemaDAO {

    ArrayList<Cinema> selectData();
    void insertData(Cinema cinema);
}
