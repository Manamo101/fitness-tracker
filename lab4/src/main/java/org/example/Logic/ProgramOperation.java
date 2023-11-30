package org.example.Logic;

import org.example.DataPersistence.DatabaseFunctionality;

public class ProgramOperation {
    private static DatabaseFunctionality database;

    public static void setDatabase(DatabaseFunctionality db){
        database = db;
    }
    public static String[] trainingNames(){
        return database.trainingNames();
    }
    public static boolean addNewTraining(String name){
        if(name.isBlank()){
            return false;
        }
        else {
            return database.addNewTraining(name);
        }
    }

}
