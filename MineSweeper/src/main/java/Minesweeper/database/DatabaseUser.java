
package Minesweeper.database;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseUser {
    private Connection connection;
    
    public void makeDatabase() throws ClassNotFoundException {
        String url = "jdbc:sqlite:Scores.db";
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(url);
            String createTable = "CREATE TABLE IF NOT EXISTS Scores " +
                                    "(id INTEGER PRIMARY KEY, player TEXT, score INTEGER);";
            Statement statement = this.connection.createStatement();
            statement.execute(createTable);
            this.connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void writeDatabase(String playerName, int score) throws ClassNotFoundException {
        String url = "jdbc:sqlite:Scores.db";
        
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(url);
            String insert = "INSERT INTO Scores (player, score) VALUES (?, ?);";
            PreparedStatement prStatement = this.connection.prepareStatement(insert);
            prStatement.setString(1, playerName);
            prStatement.setInt(2, score);
            prStatement.execute();
            this.connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList getHighScores() throws ClassNotFoundException {
        String url = "jdbc:sqlite:Scores.db";
        ArrayList<String> results = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(url);
            String getScores = "SELECT player, score FROM Scores ORDER BY score DESC LIMIT 5;";
            Statement statement = this.connection.createStatement();
            ResultSet resultset = statement.executeQuery(getScores);
            while (resultset.next()) {
                results.add(resultset.getString("player") + ": " + String.valueOf(resultset.getInt("score")));
            }
            this.connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
}
