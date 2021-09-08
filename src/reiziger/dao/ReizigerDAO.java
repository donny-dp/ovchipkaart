package reiziger.dao;

import reiziger.domein.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {

    List<Reiziger> findAll() throws SQLException;

    Reiziger find(int id) throws SQLException;

    void save(Reiziger reiziger) throws SQLException;

    void update(Reiziger reiziger) throws SQLException;

    void delete(int id) throws SQLException;
}
