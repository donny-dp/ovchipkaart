package reiziger.dao;

import adres.dao.AdresDAO;
import adres.dao.AdresDAOPsql;
import ovchipkaart.domein.OVChipkaart;
import reiziger.domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO{

    private Connection connection;
    private AdresDAO adresDAO;

    public ReizigerDAOPsql(Connection connection, AdresDAO adresDAO) {
        this.connection = connection;
        this.adresDAO = adresDAO;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM reiziger;"
        );

        ResultSet result = statement.executeQuery();

        List<Reiziger> reizigers = new ArrayList<>();

        while (result.next()) {
            reizigers.add(buildReiziger(result));
        }

        return reizigers;
    }

    @Override
    public Reiziger findById(int id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT r.reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum, kaart_nummer, geldig_tot, klasse, saldo " +
                    "FROM reiziger r " +
                    "JOIN ov_chipkaart oc on r.reiziger_id = oc.reiziger_id " +
                    "WHERE r.reiziger_id = ?;"
        );

        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();


        Reiziger reiziger = new Reiziger();
        while(result.next()) {
            reiziger.setReiziger_id(result.getInt("reiziger_id"));
            reiziger.setVoorletters(result.getString("voorletters"));
            reiziger.setVoorletters(result.getString("tussenvoegsel"));
            reiziger.setAchternaam(result.getString("achternaam"));
            reiziger.setGeboortedatum(result.getDate("geboortedatum"));

            OVChipkaart kaart = new OVChipkaart();
            kaart.setReiziger(reiziger);
            kaart.setGeldig_tot(result.getDate("geldig_tot"));
            kaart.setKlasse(result.getInt("klasse"));
            kaart.setSaldo(result.getDouble("saldo"));

            reiziger.ovChipkaarten.add(kaart);
        }

        return reiziger;
    }

    @Override
    public List<Reiziger> findByGeboortedatum(String geboortedatum) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM reiziger WHERE  geboortedatum = ?;"
        );

        statement.setDate(1, Date.valueOf(geboortedatum));

        ResultSet result = statement.executeQuery();

        List<Reiziger> reizigers = new ArrayList<>();

        while (result.next()) {
            reizigers.add(buildReiziger(result));
        }

        return reizigers;

    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO reiziger " +
                    "(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?);"
        );

        statement.setInt(1, reiziger.getReiziger_id());
        statement.setString(2, reiziger.getVoorletters());
        statement.setString(3, reiziger.getTussenvoegsel());
        statement.setString(4, reiziger.getAchternaam());
        statement.setDate(5, Date.valueOf(reiziger.getGeboortedatum()));

        int count = statement.executeUpdate();

        return count > 0;
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE reiziger SET " +
                    "voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? " +
                    "WHERE reiziger_id = ?"
        );

        statement.setString(1, reiziger.getVoorletters());
        statement.setString(2, reiziger.getTussenvoegsel());
        statement.setString(3, reiziger.getAchternaam());
        statement.setDate(4, Date.valueOf(reiziger.getGeboortedatum()));
        statement.setInt(5, reiziger.getReiziger_id());

        int count = statement.executeUpdate();

        return count > 0;
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM reiziger WHERE reiziger_id = ?;"
        );

        statement.setInt(1, reiziger.getReiziger_id());

        int count = statement.executeUpdate();

        return count > 0;
    }

    private Reiziger buildReiziger(ResultSet result) throws SQLException {
        Reiziger reiziger = new Reiziger(
                result.getInt("reiziger_id"),
                result.getString("voorletters"),
                result.getString("tussenvoegsel"),
                result.getString("achternaam"),
                result.getDate("geboortedatum")
        );

        reiziger.setAdres(adresDAO.findByReiziger(reiziger));

        return reiziger;
    }
}
