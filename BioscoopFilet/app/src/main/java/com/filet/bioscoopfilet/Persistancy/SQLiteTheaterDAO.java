package com.filet.bioscoopfilet.Persistancy;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteTheaterDAO implements TheaterDAO {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "filet.db";
    private static final String DB_TABLE_NAME = "Theater";
}
