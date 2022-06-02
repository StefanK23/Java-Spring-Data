import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _03_GetMinionNames {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);


        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db",props);
        PreparedStatement villainStatement = connection.prepareStatement(
                "SELECT name FROM villains WHERE id = ?");

        int villainId = Integer.parseInt(scanner.nextLine());
        villainStatement.setInt(1, villainId );

        ResultSet villainSET = villainStatement.executeQuery();
        if(!villainSET.next()){
            System.out.printf("No villain with ID %d exist in the database", villainId);
            return;
        }

        String villainName = villainSET.getString("name");
        System.out.println("Villain: " + villainName);

        PreparedStatement minionStatement = connection.prepareStatement(
                "SELECT m.`name`, m.`age`  from minions  as m " +
                        " JOIN minions_villains as mv ON m.id = mv.minion_id " +
                        " WHERE mv.villain_id = ?;");
        minionStatement.setInt(1,villainId);

        ResultSet minionSET = minionStatement.executeQuery();
        for (int i = 1;  minionSET.next(); i++) {
            String name = minionSET.getString("name");
            int age = minionSET.getInt("age");
            System.out.printf("%d. %s %d%n",i ,name, age);
        }

    }
}
