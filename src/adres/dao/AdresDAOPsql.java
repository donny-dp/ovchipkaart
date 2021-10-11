package adres.dao;

import adres.domein.Adres;
import reiziger.dao.ReizigerDAO;
import reiziger.dao.ReizigerDAOPsql;
import reiziger.domein.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {

    private final Connection connection;

    public AdresDAOPsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Adres adres) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?);"
        );

        statement.setInt(1, adres.getId());
        statement.setString(2, adres.getPostcode());
        statement.setString(3, adres.getHuisnummer());
        statement.setString(4, adres.getStraat());
        statement.setString(5, adres.getWoonplaats());
        statement.setInt(6, adres.getReiziger_id());

        int count = statement.executeUpdate();

        return count > 0;
    }

    @Override
    public boolean update(Adres adres) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE adres SET postcode = ?, huisnummer = ?, straat= ?, woonplaats = ? WHERE adres_id = ?;"
        );

        statement.setString(1, adres.getPostcode());
        statement.setString(2, adres.getHuisnummer());
        statement.setString(3, adres.getStraat());
        statement.setString(4, adres.getWoonplaats());
        statement.setInt(5, adres.getId());

        int count = statement.executeUpdate();

        return count > 0;
    }

    @Override
    public boolean delete(Adres adres) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM adres WHERE adres_id = ?;"
        );

        statement.setInt(1, adres.getId());

        int count = statement.executeUpdate();

        return count > 0;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM adres WHERE reiziger_id = ?;"
        );

        statement.setInt(1, reiziger.getId());

        ResultSet result = statement.executeQuery();

        if(result.next()) {
            return buildAdres(result);
        }

        return null;
    }

    @Override
    public Adres findById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM adres WHERE adres_id = ?;"
        );

        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        result.next();

        return buildAdres(result);
    }

    @Override
    public List<Adres> findAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM adres;"
        );

        ResultSet result = statement.executeQuery();

        List<Adres> adressen = new ArrayList<>();

        while(result.next()) {
            adressen.add(buildAdres(result));
        }

        return adressen;
    }

    private Adres buildAdres(ResultSet result) throws SQLException {

        Adres adres = new Adres(
            result.getInt("adres_id"),
            result.getString("postcode"),
            result.getString("huisnummer"),
            result.getString("straat"),
            result.getString("woonplaats"),
            result.getInt("reiziger_id")
        );

        return adres;
    }
}
