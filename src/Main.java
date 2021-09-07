import java.sql.*;
import java.util.Properties;

public class Main {

    public static void main(String args[]) {
        try {
            String url = "jdbc:postgresql://localhost:5432/ovchip";

            Connection connection = DriverManager.getConnection(url, "postgres", "secret");

            System.out.println("Connected to postgresql server successfully.");

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reiziger");
            ResultSet result = statement.executeQuery();

            System.out.println("Alle reizigers:");
            while(result.next()) {

                StringBuilder builder = new StringBuilder("#" + result.getString(1) + ": ");
                builder.append(result.getString(2) + " ");
                builder.append(result.getString(3) != null ? result.getString(3) + " " : "");
                builder.append(result.getString(4) + " ");
                builder.append("(" + result.getString(5) + ")");


                System.out.println(builder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
