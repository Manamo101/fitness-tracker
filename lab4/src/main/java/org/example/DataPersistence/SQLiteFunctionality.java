package org.example.DataPersistence;

import java.util.ArrayList;

public class SQLiteFunctionality implements DatabaseFunctionality{
    private final SQLiteQuery sqliteQuery;
    public SQLiteFunctionality(String databaseName){
        SQLiteConnection sqliteConnection = new SQLiteConnection(databaseName);
        sqliteQuery = new SQLiteQuery(sqliteConnection.getConnection());
    }
    public String[] trainingNames(){
        return sqliteQuery.SelectTrainingNames();
    }
    public boolean addNewTraining(String name){
            return sqliteQuery.insertNewTrainingName(name);
    }
    public boolean deleteTrainingName(String name){
        return sqliteQuery.deleteTrainingName(name);
    }
    public boolean doesTrainingNameExist(String name){
        return sqliteQuery.doesTrainingNameExist(name);
    }
    public boolean changeTrainingName(String oldName, String newName){
        return sqliteQuery.updateTrainingName(oldName, newName);
    }
    public ArrayList<ArrayList<String>> listExercises(String trainingName){
        return sqliteQuery.selectExercises(trainingName);
    }
}
