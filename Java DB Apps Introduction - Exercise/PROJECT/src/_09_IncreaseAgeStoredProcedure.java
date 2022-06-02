import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _09_IncreaseAgeStoredProcedure {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db",properties);

        int id = Integer.parseInt(scanner.nextLine());
        printNameAndAgeMinion(id, connection);
        updateMinionAge(id, connection);

        connection.close();
    }



    private static void printNameAndAgeMinion(int id, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                " SELECT name, age " +
                        " FROM minions_db.minions WHERE id = ?");
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            System.out.printf("%s %d", resultSet.getString("name"), resultSet.getInt("age"));

        }
    }

    private static void updateMinionAge(int id, Connection connection)throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                " CALL minions_db.usp_get_older(?)");
        statement.setInt(1,id);
        statement.executeUpdate();
    }
}
