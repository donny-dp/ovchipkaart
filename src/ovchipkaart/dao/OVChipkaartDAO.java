package ovchipkaart.dao;

import ovchipkaart.domein.OVChipkaart;
import reiziger.domein.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;

    public boolean save(OVChipkaart ovChipkaart) throws SQLException;

    public boolean update(OVChipkaart ovChipkaart) throws SQLException;

    public boolean delete(OVChipkaart ovChipkaart) throws SQLException;

    public List<OVChipkaart> findAll() throws SQLException;
}
