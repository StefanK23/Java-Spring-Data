import java.sql.*;
import java.util.*;

public class _07_PrintAllMinionNames {

    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement statement = connection.prepareStatement(
                "SELECT name FROM minions_db.minions");
        ResultSet resultSet = statement.executeQuery();

        Deque<String> minionsNames = new ArrayDeque<>();

        while (resultSet.next()) {
            minionsNames.add(resultSet.getString(1));

        }
        printMinions(minionsNames);
        connection.close();
    }

    private static void printMinions(Deque<String> minionsNames) {
        int counter = 0;
        while (!minionsNames.isEmpty()) {
            counter++;
            if (counter % 2 == 0) {
                String minion = minionsNames.removeLast();
                System.out.println(minion);
            }else {
                String minion = minionsNames.removeFirst();
                System.out.println(minion);
            }
        }
    }
}
