package org.example.DataPersistence;

import java.util.ArrayList;

public interface DatabaseFunctionality {
    String[] trainingNames();
    boolean addNewTraining(String name);
    boolean deleteTrainingName(String name);
    boolean doesTrainingNameExist(String name);
    boolean changeTrainingName(String oldName, String newName);
    ArrayList<ArrayList<String>> listExercises(String trainingName);
}
