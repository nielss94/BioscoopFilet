package com.filet.bioscoopfilet.Persistancy;

import com.filet.bioscoopfilet.DomainModel.Ticket;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public interface TicketDAO {

    ArrayList<Ticket> selectData();
    void insertData(Ticket ticket);
}
