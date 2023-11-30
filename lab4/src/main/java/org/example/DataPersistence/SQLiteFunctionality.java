package org.example.DataPersistence;

public class SQLiteFunctionality implements DatabaseFunctionality{
    private final SQLiteConnection sqliteConnection;
    private final SQLiteQuery sqliteQuery;
    public SQLiteFunctionality(String databaseName){
        sqliteConnection = new SQLiteConnection(databaseName);
        sqliteQuery = new SQLiteQuery(sqliteConnection.getConnection());
    }
    public String[] TrainingNames(){
        return sqliteQuery.trainingNames();
    }
}
