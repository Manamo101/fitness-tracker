package org.example.Logic;

import org.example.DataPersistence.DatabaseFunctionality;

public class ProgramOperation {
    DatabaseFunctionality db;

    public ProgramOperation(DatabaseFunctionality db){
        this.db = db;
    }

    public String[] trainingNames(){
        return db.TrainingNames();
    }

}
