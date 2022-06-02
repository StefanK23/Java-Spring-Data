import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _02_GetVillainsNames {

    public static void main(String[] args) throws SQLException {

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db",props);

        PreparedStatement statement = connection.prepareStatement("SELECT " +
                "    v.`name`, COUNT(DISTINCT mv.`minion_id`) AS `count`" +
                " FROM" +
                "    villains AS v" +
                "        JOIN" +
                "    minions_villains AS mv ON v.id = mv.villain_id" +
                " GROUP BY v.name" +
                " HAVING count > 15" +
                " ORDER BY count DESC LIMIT 1;");


        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            String villainName = resultSet.getString("name");
            int minionCount = resultSet.getInt("count");
            System.out.println(villainName + " " + minionCount);
        }
        connection.close();

    }
}
