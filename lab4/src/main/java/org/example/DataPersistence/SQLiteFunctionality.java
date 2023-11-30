package org.example.DataPersistence;

public class SQLiteFunctionality implements DatabaseFunctionality{
    private final SQLiteConnection sqliteConnection;
    private final SQLiteQuery sqliteQuery;
    //public void setDatabase()
    public SQLiteFunctionality(String databaseName){
        sqliteConnection = new SQLiteConnection(databaseName);
        sqliteQuery = new SQLiteQuery(sqliteConnection.getConnection());
    }
    public String[] trainingNames(){
        return sqliteQuery.SelectTrainingNames();
    }
    public boolean addNewTraining(String name){
            return sqliteQuery.insertNewTrainingName(name);
    }
}
