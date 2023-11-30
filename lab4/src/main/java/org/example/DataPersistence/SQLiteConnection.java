package org.example.DataPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    private final Connection connection;
    SQLiteConnection(String databaseName){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            if (!SQLiteQuery.doesExist(connection)){
                System.out.println("Database doesn't exist");
                System.exit(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Connection getConnection() {
        return connection;
    }
    void closeConnection(){
        try{
            connection.close();
        }
        catch (SQLException e){
            System.err.println("Problem with closing SQLite Database");
            throw new RuntimeException(e);
        }
    }
}
