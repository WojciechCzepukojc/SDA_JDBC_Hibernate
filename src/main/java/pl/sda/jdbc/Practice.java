package pl.sda.jdbc;


import java.sql.*;

public class Practice {
    public static void main(String[] args) {
        String mysqlURL = "jdbc:mysql://localhost:3306/ćwiczenia?serverTimezone=Europe/Warsaw";
        String mysqlUser = "root";
        String mysqlPassword = "8";

        try {
            Connection connection = DriverManager.getConnection(mysqlURL, mysqlUser, mysqlPassword);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM straznik");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String imie = resultSet.getString("imie");
                String nazwisko = resultSet.getString("nazwisko");
                String stopien = resultSet.getString("stopien");
                System.out.println(id + " " + imie + " " + nazwisko +" " + stopien);
            }





            connection.close();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
