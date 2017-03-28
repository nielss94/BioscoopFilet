package com.filet.bioscoopfilet.Persistancy;

import com.filet.bioscoopfilet.DomainModel.Theater;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public interface TheaterDAO {

    ArrayList<Theater> selectData();
    void insertData(Theater theater);
}
