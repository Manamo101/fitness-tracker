package org.example.DataPersistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLiteQuery {
    private final Connection connection;
    SQLiteQuery(Connection connection){
        this.connection = connection;
    }

    static boolean doesExist(Connection connection){
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(name) FROM sqlite_master WHERE name='trainings'");
            boolean exists = result.getInt("COUNT(name)") == 1;
            result.close();
            statement.close();
            return exists;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    String[] trainingNames(){
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT training_name FROM trainings");
            List<String> list = new ArrayList<>();
            while (result.next()){
                list.add(result.getString("training_name"));
            }
            result.close();
            statement.close();
            return list.toArray(new String[list.size()]);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
