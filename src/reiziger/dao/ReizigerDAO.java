package reiziger.dao;

import reiziger.domein.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {

    List<Reiziger> findAll() throws SQLException;

    Reiziger findById(int id) throws SQLException;

    List<Reiziger> findByGeboortedatum(String geboortedatum) throws SQLException;

    boolean save(Reiziger reiziger) throws SQLException;

    boolean update(Reiziger reiziger) throws SQLException;

    boolean delete(Reiziger reiziger) throws SQLException;
}
