package com.management.project.connections;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface with one method, that returns instance of Connection class. Classes, that return Connection's instances
 * should implement that interface
 *
 * @author Вадим
 */
public interface ConnectionDB {

    /**
     * Method for getting connection. Needs to be implemented.
     *
     * @return an instance of Connection class, the connection to database
     * @throws SQLException in case of connection problems
     */
    Connection getConnection() throws SQLException;
}
