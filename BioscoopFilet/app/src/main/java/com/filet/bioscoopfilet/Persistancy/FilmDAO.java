package com.filet.bioscoopfilet.Persistancy;

import com.filet.bioscoopfilet.DomainModel.Film;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public interface FilmDAO {

    ArrayList<Film> selectData();
    void insertData(Film film);
    void deleteData();
}
