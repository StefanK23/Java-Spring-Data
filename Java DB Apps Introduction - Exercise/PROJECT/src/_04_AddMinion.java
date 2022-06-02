import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _04_AddMinion {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        String[] minionInfo = scanner.nextLine().split(" ");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String minionTown = minionInfo[3];

        String villainName = scanner.nextLine().split(" ")[1];

        int townId = getOrInsertTown(connection, minionTown);
        int villainId = getOrInsertVillain(connection, villainName);


        PreparedStatement insertMinion = connection.prepareStatement(" INSERT INTO minions(name,age,town_id ) VALUES(?,?,?)");
        insertMinion.setString(1, minionName);
        insertMinion.setInt(2, minionAge);
        insertMinion.setInt(3, townId);
        insertMinion.executeUpdate();

        PreparedStatement getLastMinion = connection.prepareStatement(
                "SELECT id FROM minions ORDER BY id DESC");
        ResultSet lastMinionSet = getLastMinion.executeQuery();
        lastMinionSet.next();
        int lastMinionID= lastMinionSet.getInt("id");


        PreparedStatement insertMinionsVillains = connection.prepareStatement(
                "INSERT INTO minions_villains VALUES(?,?)");
        insertMinionsVillains.setInt(1,lastMinionID);
        insertMinionsVillains.setInt(2,villainId);
        insertMinionsVillains.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);
    }

    private static int getOrInsertVillain(Connection connection, String villainName) throws SQLException {

        PreparedStatement selectVillainStm = connection.prepareStatement(
                " SElECT id FROM villains WHERE name = ?");
        selectVillainStm.setString(1, villainName);

        ResultSet villainSet = selectVillainStm.executeQuery();

        int villainId = 0;
        if (!villainSet.next()) {
            PreparedStatement insertVillain = connection.prepareStatement(
                    " INSERT INTO villains (name, evilness_factor)" +
                            " VALUES (?, ?)");

            insertVillain.setString(1, villainName);
            insertVillain.setString(2, "evil");
            insertVillain.executeUpdate();

            ResultSet newVillainSet = selectVillainStm.executeQuery();
            newVillainSet.next();
            villainId = newVillainSet.getInt("id");
            System.out.printf("Villain %s was added to the database.%n", villainName);

        } else {
            villainId = villainSet.getInt("id");
        }
        return villainId;
    }

    private static int getOrInsertTown(Connection connection, String minionTown) throws SQLException {
        PreparedStatement selectTownStm = connection.prepareStatement(
                " SElECT id FROM towns WHERE name = ?");
        selectTownStm.setString(1, minionTown);

        ResultSet townSet = selectTownStm.executeQuery();
        int townId = 0;
        if (!townSet.next()) {
            PreparedStatement insertTownStm = connection.prepareStatement(
                    " INSERT INTO towns(name) " +
                            "VALUES (?);");
            insertTownStm.setString(1, minionTown);
            insertTownStm.executeUpdate();

            ResultSet newTownSet = selectTownStm.executeQuery();
            newTownSet.next();
            townId = newTownSet.getInt("id");
            System.out.printf("Town %s was added to the database.%n", minionTown);
        } else {
            townId = townSet.getInt("id");
        }
        return townId;

    }
}
