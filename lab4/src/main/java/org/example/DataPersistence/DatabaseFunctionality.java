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
}
