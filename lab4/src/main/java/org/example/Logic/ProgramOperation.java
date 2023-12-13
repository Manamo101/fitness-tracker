package org.example.Logic;

import org.example.DataPersistence.DatabaseFunctionality;
import org.example.DataPersistence.SQLiteFunctionality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ProgramOperation {
    private static DatabaseFunctionality database;

    public static HashMap<String, String> getListedExercises(String str){
        HashMap<String,String> hashMap = new HashMap<>();
        Scanner scanner = new Scanner(str);
        String exercise = "";
        String name;
        while (scanner.hasNext()){
            name = scanner.next();
            if (name.endsWith("X") || name.endsWith("sec")){
                if (name.endsWith("X")){
                    hashMap.put("timeRep", "X");
                }
                else{
                    hashMap.put("timeRep", "sec");
                }
                hashMap.put("amount", name.replace("sec","").replace("X",""));
                break;
            }
            else {
                exercise =exercise.concat(" ").concat(name);
            }
        }
        hashMap.put("exercise", exercise.trim());
        hashMap.put("loading", scanner.hasNext() ? scanner.next().replace("kg","") : null);
        return hashMap;
    }

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
    public static void deleteTraining(String name){
        database.deleteTrainingName(name);
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
                repetitions = record.get(1).concat("X   ");
            }
            if(record.get(2) != null){
                time = record.get(2).concat("sec   ");
            }
            if(record.get(3) != null){
                loading = record.get(3).concat("kg   ");
            }
            output.add(name + repetitions + time + loading);
        }
        return output;
    }
    public static boolean addExercise(HashMap<String, String> hashMap, String trainingName){
        if (!database.doesExerciseNameExist(hashMap.get("exercise"), trainingName)){
            database.addExercise(hashMap, trainingName);
            return true;
        }
        else{
            return false;
        }
    }
    public static void modifyExercise(HashMap<String, String> hashMap, String trainingName, String oldName){
        database.modifyExercise(hashMap, trainingName, oldName);
    }
    public static void deleteExercise(String trainingName, String exerciseName){
        database.deleteExercise(trainingName, new Scanner(exerciseName).next());
    }
    public static ArrayList<ArrayList<String>> selectSessions(){
        return database.selectSessions();
    }
}
