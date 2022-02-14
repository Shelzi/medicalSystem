package com.epam.medicalsystem.model.pool;

import com.epam.medicalsystem.exception.ConnectionPollException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance = null;
    private final BlockingQueue<ProxyConnection> idleConnections;
    private static final int POOL_SIZE = Integer.parseInt(ConnectionCreator.getPoolSize());
    private static final int MAX_CONNECTION_ERROR_NUMBER = 4;


    public static ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    private ConnectionPool() {
        int errorCounter = 0;
        idleConnections = new ArrayBlockingQueue<ProxyConnection>(POOL_SIZE);

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionCreator.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                idleConnections.add(proxyConnection);
            } catch (SQLException e) {
                //logger.log(Level.ERROR, "Connection hasn't been created" + e);
                errorCounter++;
            }
        }
        if (errorCounter >= MAX_CONNECTION_ERROR_NUMBER) {
            //logger.log(Level.FATAL, errorCounter + " connections haven't been created");
            throw new RuntimeException(errorCounter + " connections haven't been created");
        }

    }

    public void releaseConnection(ProxyConnection proxyConnection) {
            idleConnections.offer(proxyConnection);
        // TODO: 14.02.2022 need a refactor to instanceof and add log
    }

    public Connection takeConnection() throws ConnectionPollException {
        ProxyConnection connection;
        try {
            connection = idleConnections.take();
        } catch (InterruptedException e) {
            throw new ConnectionPollException(e);
            // TODO: 14.02.2022 add log
        }
        return connection;
    }
}