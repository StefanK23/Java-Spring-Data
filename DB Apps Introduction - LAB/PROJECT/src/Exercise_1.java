import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Exercise_1 {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);



        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234") ;

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement statement = connection.prepareStatement("SELECT u.user_name, u.first_name, u.last_name, " +
                "COUNT(ug.id)" +
                "FROM users AS u " +
                "JOIN users_games AS ug ON u.id = ug.user_id " +
                "WHERE u.user_name = ? " +
                "GROUP BY u.id;");

        String username= scanner.nextLine();
        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            String dbUsername = resultSet.getString("user_name");
            String dbFirstName = resultSet.getString("first_name");
            String dbLastName = resultSet.getString("last_name");
            Integer dbGamesCount = resultSet.getInt("count(ug.id)");

            System.out.printf("User: %s\n%s %s has played %d games\n",
                    dbUsername,dbFirstName,dbLastName,dbGamesCount);
        }else {
            System.out.println("No such user exist");
        }
    }
}
