package com.filet.bioscoopfilet.Persistancy;

import com.filet.bioscoopfilet.DomainModel.Actor;
import com.filet.bioscoopfilet.DomainModel.Film;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public interface ActorDAO {

    ArrayList<Actor> selectData();
    void insertData(Actor actor);
}
