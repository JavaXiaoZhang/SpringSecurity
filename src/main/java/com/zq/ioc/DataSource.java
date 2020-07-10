package com.zq.ioc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class DataSource {
    private static final LinkedList<Connection> connectionList = new LinkedList<>();

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/springsecurity?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC","root","1234");
    }

    private DataSource(){
        if (connectionList==null || connectionList.size()==0){
            for (int i = 0; i < 10; i++) {
                try {
                    connectionList.add(createConnection());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public Connection getConnection(){
        if (connectionList.size() > 0) {
            //return connectionList.remove();
            return new ConnectionProxy(connectionList.remove());
        }
        return null;
    }

    public void recoveryConnection(Connection connection){
        connectionList.add(connection);
    }

    public static DataSource getInstance(){
        return DataSourceInstance.dataSource;
    }

    private static class DataSourceInstance{
        private static DataSource dataSource = new DataSource();
    }
}
