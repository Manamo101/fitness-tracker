package org.example.DataPersistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLiteQuery {
    private final String databaseName;

    SQLiteQuery(String databaseName) {
        this.databaseName = databaseName;
    }

    static boolean doesExist(String databaseName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(name) FROM sqlite_master WHERE name = 'trainings' OR name = 'exercises'");
            boolean exists = result.getInt("COUNT(name)") == 2;
            result.close();
            statement.close();
            connection.close();
            return exists;
        } catch (SQLException e) {
            return false;
        }
    }

    String[] SelectTrainingNames() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT training_name FROM trainings");
            List<String> list = new ArrayList<>();
            while (result.next()) {
                list.add(result.getString("training_name"));
            }
            result.close();
            statement.close();
            connection.close();
            return list.toArray(new String[0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    boolean doesTrainingNameExist(String name) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(training_name) FROM trainings WHERE training_name = ?");
            ps.setString(1, name);
            ResultSet result = ps.executeQuery();
            boolean bool = result.getInt(1) == 1;
            result.close();
            ps.close();
            connection.close();
            return bool;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    boolean doesExerciseNameExist(String exerciseName, String trainingName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(training_name) FROM exercises WHERE name = ? AND training_name = ?");
            ps.setString(1, exerciseName);
            ps.setString(2, trainingName);
            ResultSet result = ps.executeQuery();
            boolean bool = result.getInt(1) != 0;
            ps.close();
            result.close();
            connection.close();
            return bool;
        } catch (SQLException e) {
            return false;
        }
    }

    boolean insertNewTrainingName(String name) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            Statement statement = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO trainings VALUES (?)");
            ps.setString(1, name.trim());
            ps.executeUpdate();
            ps.close();
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            System.out.println("inserting training went wrong");
            return false;
        }
    }

    void deleteTrainingName(String name) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM trainings WHERE training_name = ?");
            ps1.setString(1, name);
            ps1.execute();
            ps1.close();

            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM exercises WHERE training_name = ?");
            ps2.setString(1, name);
            ps2.execute();
            ps2.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("removing training went wrong");
        }
    }

    boolean updateTrainingName(String oldName, String newName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);

            PreparedStatement ps1 = connection.prepareStatement("UPDATE trainings SET training_name = ? WHERE training_name = ?");
            ps1.setString(1,newName);
            ps1.setString(2,oldName);
            ps1.executeUpdate();
            ps1.close();

            PreparedStatement ps2 = connection.prepareStatement("UPDATE exercises SET training_name = ? WHERE training_name = ?");
            ps2.setString(1, newName);
            ps2.setString(2, oldName);
            ps2.executeUpdate();
            ps2.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("updateTrainingName() went wrong");
            return false;
        }
        return true;
    }

    ArrayList<ArrayList<String>> selectExercises(String training) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM exercises WHERE training_name = ?");
            ps.setString(1, training);
            ResultSet result = ps.executeQuery();
            ArrayList<ArrayList<String>> list = new ArrayList<>();
            while (result.next()) {
                list.add(new ArrayList<>());
                list.get(list.size() - 1).add(result.getString(1));
                list.get(list.size() - 1).add(result.getString(2));
                list.get(list.size() - 1).add(result.getString(3));
                list.get(list.size() - 1).add(result.getString(4));
            }
            result.close();
            ps.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void addExercise(HashMap<String, String> hashMap, String trainingName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO exercises VALUES (?,?,?,?,?)");
            ps.setString(1, hashMap.get("exercise"));
            int index = hashMap.get("timeRep").equals("X") ? 2 : 3;
            ps.setInt(index, Integer.parseInt(hashMap.get("amount")));
            if (hashMap.get("loading") != null) {
                ps.setInt(4, Integer.parseInt(hashMap.get("loading")));
            }
            ps.setString(5, trainingName);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void modifyExercise(HashMap<String, String> hashMap, String trainingName, String oldName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
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
            if (hashMap.get("loading") != null) {
                ps.setInt(4, Integer.parseInt(hashMap.get("loading")));
            }
            ps.setString(5, trainingName);
            ps.setString(6, oldName);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void deleteExercise(String trainingName, String exerciseName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM exercises WHERE name = ? AND training_name = ?");
            ps.setString(1, exerciseName);
            ps.setString(2, trainingName);
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    ArrayList<ArrayList<String>> selectSessions(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM sessions");
            ResultSet result = ps.executeQuery();
            ArrayList<ArrayList<String>> list = new ArrayList<>();
            while (result.next()) {
                list.add(new ArrayList<>());
                list.get(list.size() - 1).add(result.getString(1));
                list.get(list.size() - 1).add(result.getString(2));
                list.get(list.size() - 1).add(result.getString(3));
            }
            result.close();
            ps.close();
            connection.close();
            return list;
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
    }

    boolean addSession(String trainingName, String date, String time){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO sessions VALUES (?,?,?)");
            ps.setString(1, trainingName);
            ps.setString(2, date);
            ps.setString(3,time);
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    boolean modifySession(String newValue, String column, String date){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            String query;
            if (column.equals("training name")){
                query ="UPDATE sessions SET training_name = ? WHERE session_date = ?";
            }
            else if (column.equals("date")){
                query = "UPDATE sessions SET session_date = ? WHERE session_date = ?";
            }
            else {
                query = "UPDATE sessions SET time = ? WHERE session_date = ?";
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newValue);
            ps.setString(2,date);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    void deleteSession(String date){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM sessions WHERE session_date = ?");
            ps.setString(1, date);
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ArrayList<ArrayList<String>> selectGoals() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM goals");
            ResultSet result = ps.executeQuery();
            ArrayList<ArrayList<String>> list = new ArrayList<>();
            while (result.next()) {
                list.add(new ArrayList<>());
                list.get(list.size() - 1).add(result.getString(1));
                list.get(list.size() - 1).add(result.getString(2));
                list.get(list.size() - 1).add(result.getString(3));
                list.get(list.size() - 1).add(result.getString(4));
                list.get(list.size() - 1).add(result.getString(5));
            }
            result.close();
            ps.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void deleteGoal(String exerciseName){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM goals WHERE exercise_name = ?");
            ps.setString(1, exerciseName);
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    String[] listAllExercises(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT DISTINCT name FROM exercises");
            List<String> list = new ArrayList<>();
            while (result.next()) {
                list.add(result.getString("name"));
            }
            result.close();
            statement.close();
            connection.close();
            return list.toArray(new String[0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    boolean addGoal(String[] data){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO goals VALUES (?,?,?,?,?)");
            ps.setString(1, data[0]);
            if (data[1] != null){
                ps.setInt(2,Integer.parseInt(data[1]));
            }
            if (data[2] != null){
                ps.setInt(3,Integer.parseInt(data[2]));
            }
            if (data[3] != null){
                ps.setInt(4,Integer.parseInt(data[3]));
            }
            ps.setString(5, data[4]);
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    void updateGoal(String[] data, String exerciseName){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            PreparedStatement ps = connection.prepareStatement("UPDATE goals SET " +
                    "repetitions = ?," +
                    "time = ?," +
                    "loading = ?," +
                    "deadline = ?" +
                    "WHERE exercise_name = ?");
            if (data[1] != null){
                ps.setInt(1,Integer.parseInt(data[1]));
            }
            if (data[2] != null){
                ps.setInt(2,Integer.parseInt(data[2]));
            }
            if (data[3] != null){
                ps.setInt(3,Integer.parseInt(data[3]));
            }
            ps.setString(4, data[4]);
            ps.setString(5, exerciseName);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}