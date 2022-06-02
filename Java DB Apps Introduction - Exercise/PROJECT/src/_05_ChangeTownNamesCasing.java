import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _05_ChangeTownNamesCasing {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();

        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        String countryName = scanner.nextLine();

        PreparedStatement updateStm = connection.prepareStatement(" UPDATE minions_db.towns as t " +
                " SET t.name = UPPER(t.name)" +
                " WHERE country = ?;");
        updateStm.setString(1,countryName);
        updateStm.executeUpdate();


        printCountOfAffectedTowns(connection, countryName);
        printAllTownsByGivenCountryName(connection, countryName);



        connection.close();
    }

    private static void printCountOfAffectedTowns(Connection connection, String countryName) throws SQLException {
        PreparedStatement townsCountStm = connection.prepareStatement(" SELECT COUNT(name) as town_count" +
                " FROM minions_db.towns WHERE country = ? " +
                " GROUp BY country");
        townsCountStm.setString(1, countryName);
        ResultSet townsCountSET = townsCountStm.executeQuery();

        if(townsCountSET.next()){
            System.out.printf("%d towns names were affected.%n ", townsCountSET.getInt("town_count"));
        }
    }

    private static void printAllTownsByGivenCountryName(Connection connection, String countryName) throws SQLException {
        PreparedStatement countryStm = connection.prepareStatement(" SELECT t.name" +
                "  from towns as t" +
                "  WHERE country = ?;");
        countryStm.setString(1, countryName);
        ResultSet countrySet = countryStm.executeQuery();


        List<String> towns = new ArrayList<>();
        while (countrySet.next()) {
            towns.add(countrySet.getString("name"));
        }

        if (towns.size() > 0) {
            System.out.print("[" + String.join(", ", towns) + "]");
        } else {
            System.out.println("No town names were affected.");
        }
    }



}
