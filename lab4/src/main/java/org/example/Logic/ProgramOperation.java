package org.example.Logic;

import org.example.DataPersistence.DatabaseFunctionality;

import java.util.ArrayList;

public class ProgramOperation {
    private static DatabaseFunctionality database;

    public static void setDatabase(DatabaseFunctionality db){
        database = db;
    }
    public static String[] trainingNames(){
        return database.trainingNames();
    }
    public static boolean addNewTraining(String name){
        if(name.isBlank() || database.doesTrainingNameExist(name)){
            return false;
        }
        else {
            return database.addNewTraining(name);
        }
    }
    public static boolean deleteTraining(String name){
        return database.deleteTrainingName(name);
    }
    public static boolean changeTrainingName(String oldName, String newName){
        if(newName.isBlank() || database.doesTrainingNameExist(newName)){
            return false;
        }
        else {
            return database.changeTrainingName(oldName, newName);
        }
    }
    public static ArrayList<String> listExercises(String trainingName){
        ArrayList<ArrayList<String>> list = database.listExercises(trainingName);
        ArrayList<String> output = new ArrayList<>();
        for (ArrayList<String> record : list){
            String name, repetitions = "", time = "", loading = "";
            name= record.get(0).concat("  ");
            if (record.get(1) != null){
                repetitions = record.get(1).concat("x   ");
            }
            if(record.get(2) != null){
                time = record.get(2).concat("s   ");
            }
            if(record.get(3) != null){
                loading = record.get(3).concat("kg   ");
            }
            output.add(name + repetitions + time + loading);
        }
        return output;
    }
}
