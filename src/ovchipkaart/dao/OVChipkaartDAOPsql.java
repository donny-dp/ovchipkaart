package ovchipkaart.dao;

import ovchipkaart.domein.OVChipkaart;
import reiziger.domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {

    private final Connection connection;

    public OVChipkaartDAOPsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM ov_chipkaart WHERE reiziger_id = ?;"
        );

        statement.setInt(1, reiziger.getId());

        ResultSet result =  statement.executeQuery();

        List<OVChipkaart> ovChipkaarten = new ArrayList<>();

        while(result.next()) {
            ovChipkaarten.add(buildOvChipkaart(result));
        }

        return ovChipkaarten;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO ov_chipkaart " +
                    "(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?);"
        );

        statement.setInt(1, ovChipkaart.getKaart_nummer());
        statement.setDate(2, Date.valueOf(ovChipkaart.getGeldig_tot()));
        statement.setInt(3, ovChipkaart.getKlasse());
        statement.setDouble(4, ovChipkaart.getKlasse());
        statement.setInt(5, ovChipkaart.getReiziger_id());

        int count = statement.executeUpdate();

        return count > 0;
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE ov_chipkaart SET " +
                    "geldig_tot = ?, klasse = ?, saldo = ?, reiziger_id = ? " +
                    "WHERE kaart_nummer = ?;"
        );

        statement.setDate(1, Date.valueOf(ovChipkaart.getGeldig_tot()));
        statement.setInt(2, ovChipkaart.getKlasse());
        statement.setDouble(3, ovChipkaart.getKlasse());
        statement.setInt(4, ovChipkaart.getReiziger_id());
        statement.setInt(5, ovChipkaart.getKaart_nummer());

        int count = statement.executeUpdate();

        return count > 0;
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM ov_chipkaart WHERE kaart_nummer = ?;"
        );

        statement.setInt(1, ovChipkaart.getKaart_nummer());

        int count = statement.executeUpdate();

        return count > 0;
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT kaart_nummer, geldig_tot, klasse, saldo, reiziger_id FROM ov_chipkaart;"
        );

        ResultSet result = statement.executeQuery();

        List<OVChipkaart> ovChipkaarten = new ArrayList<>();

        while (result.next()) {
            ovChipkaarten.add(buildOvChipkaart(result));
        }

        return ovChipkaarten;
    }

    private OVChipkaart buildOvChipkaart(ResultSet result) throws SQLException {
        return new OVChipkaart(
                result.getInt("kaart_nummer"),
                result.getDate("geldig_tot"),
                result.getInt("klasse"),
                result.getDouble("saldo"),
                result.getInt("reiziger_id")
        );
    }
}
