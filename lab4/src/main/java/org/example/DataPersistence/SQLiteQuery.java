package org.example.DataPersistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
    boolean doesTrainingNameExist(String name){
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(training_name) FROM trainings WHERE training_name ='" + name + "'");
            boolean bool = result.getInt(1) != 0;
            result.close();
            statement.close();
            return bool;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    boolean doesExerciseNameExist(String exerciseName, String trainingName){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(training_name) FROM exercises WHERE name = ? AND training_name = ?");
            ps.setString(1, exerciseName);
            ps.setString(2, trainingName);
            ResultSet result = ps.executeQuery();
            boolean bool = result.getInt(1) != 0;
            ps.close();
            result.close();
            return bool;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    boolean insertNewTrainingName(String name){
        try{
            Statement statement = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO trainings VALUES (?)");
                ps.setString(1, name.trim());
                ps.executeUpdate();
                ps.close();
                statement.close();
                return true;
        }
        catch (SQLException e){
            System.out.println("inserting training went wrong");
            return false;
        }
    }
    void deleteTrainingName(String name){
        try{
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM trainings WHERE training_name = ?");
            ps1.setString(1, name);
            ps1.execute();
            ps1.close();

//            Statement statement = connection.createStatement();
//            statement.execute("DELETE FROM trainings WHERE training_name ='" + name + "'");

            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM exercises WHERE training_name = ?");
            ps2.setString(1, name);
            ps2.execute();
            ps2.close();
//          statement.close();
        }
        catch (SQLException e){
            System.out.println("removing training went wrong");
        }
    }
    boolean updateTrainingName(String oldName, String newName){
        try{
            String query = "UPDATE trainings SET training_name = '" + newName + "' WHERE training_name = '" + oldName + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();

            PreparedStatement ps2 = connection.prepareStatement("UPDATE exercises SET training_name = ? WHERE training_name = ?");


            ps2.setString(1, newName);
            ps2.setString(2,oldName);
            ps2.executeUpdate();
            ps2.close();
        }
        catch (SQLException e){
            System.out.println("updateTrainingName() went wrong");
            return false;
        }
        return true;
    }
    ArrayList<ArrayList<String>> selectExercises(String training){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM exercises WHERE training_name = ?");
            ps.setString(1,training);
            ResultSet result = ps.executeQuery();
            ArrayList<ArrayList<String>> list = new ArrayList<>();
            while (result.next()){
                list.add(new ArrayList<>());
                list.get(list.size()-1).add(result.getString(1));
                list.get(list.size()-1).add(result.getString(2));
                list.get(list.size()-1).add(result.getString(3));
                list.get(list.size()-1).add(result.getString(4));
            }
            result.close();
            ps.close();
            return list;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    void addExercise(HashMap<String, String> hashMap, String trainingName){
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO exercises VALUES (?,?,?,?,?)");
            ps.setString(1, hashMap.get("exercise"));
            int index = hashMap.get("timeRep").equals("X") ? 2 : 3;
            ps.setInt(index, Integer.parseInt(hashMap.get("amount")));
            if (hashMap.get("loading") != null){
                ps.setInt(4, Integer.parseInt(hashMap.get("loading")));
            }
            ps.setString(5, trainingName);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    void modifyExercise(HashMap<String, String> hashMap, String trainingName, String oldName){
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE exercises SET " +
                                                                    "name = ?, " +
                                                                    "repetitions = ?," +
                                                                    "time = ?," +
                                                                    "loading = ?," +
                                                                    "training_name = ?" +
                                                                    "WHERE name = ?");
            ps.setString(1, hashMap.get("exercise"));
            int index = hashMap.get("timeRep").equals("X") ? 2 : 3;
            ps.setInt(index, Integer.parseInt(hashMap.get("amount")));
            if (hashMap.get("loading") != null){
                ps.setInt(4, Integer.parseInt(hashMap.get("loading")));
            }
            ps.setString(5, trainingName);
            ps.setString(6, oldName);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    void deleteExercise(String trainingName, String exerciseName){
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM exercises WHERE name = ? AND training_name = ?");
            ps.setString(1, exerciseName);
            ps.setString(2, trainingName);
            ps.execute();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}