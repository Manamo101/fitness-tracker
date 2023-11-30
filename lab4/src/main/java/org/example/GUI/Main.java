package org.example.GUI;

import org.example.DataPersistence.DatabaseFunctionality;
import org.example.DataPersistence.SQLiteFunctionality;
import org.example.Logic.ProgramOperation;

public class Main {
    public static void main(String[] args) {
        String pathToDB = "C:\\Users\\Kamil\\Desktop\\studia\\3 sem\\Jezyki programowania\\lab4\\lab4\\src\\main\\resources\\userdata.db";
        DatabaseFunctionality database = new SQLiteFunctionality(pathToDB);
        ProgramOperation.setDatabase(database);
        new MainFrame("Fitness Tracker");
    }
}