package product.dao;

import product.domein.Product;

import java.sql.SQLException;

public interface ProductDAO {
    boolean save(Product product) throws SQLException;

    boolean update(Product product) throws SQLException;

    boolean delete(Product product) throws SQLException;


}
