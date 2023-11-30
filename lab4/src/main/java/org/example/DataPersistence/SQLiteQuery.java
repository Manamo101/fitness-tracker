package org.example.DataPersistence;

import java.sql.*;
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

    String[] SelectTrainingNames(){
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
    private boolean doesTrainingNameExist(String name){
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(training_name) FROM trainings WHERE training_name ='" + name.trim() + "'");
            return result.getInt(1) != 0;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    boolean insertNewTrainingName(String name){
        try{
            Statement statement = connection.createStatement();
            if(!doesTrainingNameExist(name)){
                PreparedStatement ps = connection.prepareStatement("INSERT INTO trainings VALUES (?)");
                ps.setString(1, name.trim());
                ps.executeUpdate();
                ps.close();
                //result.close();
                statement.close();
                return true;
            }
            else{
                return false;
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}