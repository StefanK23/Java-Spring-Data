
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class _08_IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db",properties);

        int [] minionId = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        modifyMinionNamesAndAges(minionId, connection);

    }

    private static void modifyMinionNamesAndAges(int[] minionId , Connection connection) throws SQLException {
        for (int i : minionId) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE minions_db.minions as m " +
                            " SET m.name = LOWER(m.name)," +
                            " m.age = m.age + 1 " +
                            "WHERE m.id = ? ");
            statement.setInt(1,i);
            statement.executeUpdate();
        }
        printMinionsAndAges(connection);
    }

    private static void printMinionsAndAges(Connection connection ) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT name, age " +
                        " FROM minions_db.minions");
        ResultSet resultSet = statement.executeQuery();

        while  (resultSet.next()){
            System.out.printf("%s %d%n" ,resultSet.getString("name"), resultSet.getInt("age"));

        }
    }
}
