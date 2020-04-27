
package com.mycompany.minesweeper;

import java.sql.*;

public class DatabaseUser {
    private Connection connection;
    public void makeDatabase() throws ClassNotFoundException{
        String url = "jdbc:sqlite:Scores.db";
        try{
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(url);
            String createTable = "CREATE TABLE IF NOT EXISTS Scores " +
                                    "(id INTEGER PRIMARY KEY, player TEXT, score INTEGER);";
            Statement statement = this.connection.createStatement();
            statement.execute(createTable);
            this.connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void WriteDatabase(String playerName){
        
    }
}
