package com.management.project.connections;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Вадим on 07.03.2017.
 */
public class ConnectionMySqlTest {

    ConnectionMySql connectionMySql = ConnectionMySql.getInstance();

    @Test
    public void getInstance() throws Exception {
        ConnectionMySql connectionMySql1 = ConnectionMySql.getInstance();
        assertTrue(connectionMySql.hashCode()==connectionMySql1.hashCode());
        assertSame(connectionMySql1,connectionMySql);
    }

    @Test
    public void getConnection() throws Exception {
        assertFalse(connectionMySql.getConnection().isClosed());
    }

}