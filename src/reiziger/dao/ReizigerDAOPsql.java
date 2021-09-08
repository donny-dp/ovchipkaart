package reiziger.dao;

import reiziger.domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO{

    Connection connection;

    public ReizigerDAOPsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM reiziger;"
        );

        ResultSet result = statement.executeQuery();

        List<Reiziger> reizigers = new ArrayList<Reiziger>();

        while (result.next()) {
            reizigers.add(buildReiziger(result));
        }

        return reizigers;
    }

    @Override
    public Reiziger find(int id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM reiziger WHERE reiziger_id = ?;"
        );

        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();
        result.next();
        return buildReiziger(result);
    }

    @Override
    public void save(Reiziger reiziger) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO reiziger " +
                    "(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?);"
        );

        statement.setInt(1, reiziger.reiziger_id);
        statement.setString(2, reiziger.voorletters);
        statement.setString(3, reiziger.tussenvoegsel);
        statement.setString(4, reiziger.achternaam);
        statement.setDate(5, Date.valueOf(reiziger.getGeboortedatum()));

        statement.executeUpdate();
    }

    @Override
    public void update(Reiziger reiziger) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE reiziger SET " +
                    "voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? " +
                    "WHERE reiziger_id = ?"
        );

        statement.setString(1, reiziger.voorletters);
        statement.setString(2, reiziger.tussenvoegsel);
        statement.setString(3, reiziger.achternaam);
        statement.setDate(4, Date.valueOf(reiziger.getGeboortedatum()));
        statement.setInt(5, reiziger.reiziger_id);

        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM reiziger WHERE reiziger_id = ?;"
        );

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    private Reiziger buildReiziger(ResultSet result) throws SQLException {
        return new Reiziger(
                result.getInt("reiziger_id"),
                result.getString("voorletters"),
                result.getString("tussenvoegsel"),
                result.getString("achternaam"),
                result.getDate("geboortedatum")
        );
    }
}
