package com.filet.bioscoopfilet.Persistancy;

import com.filet.bioscoopfilet.DomainModel.Show;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public interface ShowDAO {

    ArrayList<Show> selectData();
    void insertData(Show show);
}
