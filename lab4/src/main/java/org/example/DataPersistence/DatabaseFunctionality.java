package org.example.DataPersistence;

import java.util.ArrayList;
import java.util.HashMap;

public interface DatabaseFunctionality {
//    boolean doesDatabaseExist(String databaseName);
    String[] trainingNames();
    boolean addNewTraining(String name);
    void deleteTrainingName(String name);
    boolean doesTrainingNameExist(String name);
    boolean doesExerciseNameExist(String exerciseName, String trainingName);
    boolean changeTrainingName(String oldName, String newName);
    ArrayList<ArrayList<String>> listExercises(String trainingName);
    void addExercise(HashMap<String, String> hashMap, String trainingName);
    void modifyExercise(HashMap<String, String> hashMap, String trainingName, String oldName);
    void deleteExercise(String trainingName, String exerciseName);
    ArrayList<ArrayList<String>> selectSessions();
    boolean addSession(String trainingName, String date, String time);
    boolean modifySession(String newValue, String column, String date);
    void deleteSession(String date);
    ArrayList<ArrayList<String>> selectGoals();
    void deleteGoal(String exerciseName);
    String[] listAllExercises();
    boolean addGoal(String[] data);
    void updateGoal(String[] data, String exercise);
}
