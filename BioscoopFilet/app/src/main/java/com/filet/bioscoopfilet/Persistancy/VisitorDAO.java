package com.filet.bioscoopfilet.Persistancy;

import com.filet.bioscoopfilet.DomainModel.Visitor;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public interface VisitorDAO {

    ArrayList<Visitor> selectData();
    void insertData(Visitor visitor);
}
