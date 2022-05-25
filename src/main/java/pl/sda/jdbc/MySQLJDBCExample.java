package pl.sda.jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class MySQLJDBCExample {

    public static void main(String[] args) {

        String mySQLUrl = "jdbc:mysql://localhost:3306/jdbcexamplearpjava2?serverTimezone=Europe/Warsaw";
        String mysqlUser = "root";
        String mysqlPassword = "88..W";

        try {
//            MysqlDataSource dataSource = new MysqlDataSource();       alternatywny sposób na pozyskiwanie obiektu typu Connection
//            dataSource.setUrl(mySQLUrl);
//            dataSource.setUser(mysqlUser);
//            dataSource.setPassword(mysqlPassword
//            );
//            Connection connectionFromDataSource =dataSource.getConnection();

            Connection connection = DriverManager.getConnection(mySQLUrl, mysqlUser, mysqlPassword);


            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET FIRST_NAME = ? WHERE ID = ?");
            preparedStatement.setString(1, "Marian");
            preparedStatement.setInt(2, 2);

            int i = preparedStatement.executeUpdate();
            System.out.println(i);

            Statement statement = connection.createStatement();

//            statement.executeUpdate("UPDATE person SET FIRST_NAME = 'Anna' WHERE ID = 2"); //nie powinno się sklejać stringów

             ResultSet resultSet = statement.executeQuery("SELECT * FROM PERSON");

            CallableStatement callableStatement = connection.prepareCall("{call select_all_persons()}");
            resultSet = callableStatement.executeQuery();


            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                String pesel = resultSet.getString("PESEL");
                System.out.println(id + " " + firstName + " " + lastName + " " + pesel);

            }

            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
