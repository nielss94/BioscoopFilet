package com.filet.bioscoopfilet.Persistancy;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteVisitorDAO implements VisitorDAO {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "filet.db";
    private static final String DB_TABLE_NAME = "Visitor";
}