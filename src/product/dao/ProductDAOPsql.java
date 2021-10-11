package product.dao;

import product.domein.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAOPsql implements ProductDAO {

    private Connection connection;

    public ProductDAOPsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Product product) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO product (product_nummer, naam, beschrijving, prijs) VALUES (?, ?, ?, ?);"
        );

        statement.setInt(1, product.getProduct_nummer());
        statement.setString(2, product.getNaam());
        statement.setString(3, product.getBeschrijving());
        statement.setDouble(4, product.getPrijs());

        int count = statement.executeUpdate();

        return count > 0;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
            "UPDATE product SET naam = ?, beschrijving = ?, prijs = ? " +
                "WHERE product_nummer = ?;"
        );

        statement.setString(1, product.getNaam());
        statement.setString(2, product.getBeschrijving());
        statement.setDouble(3, product.getPrijs());
        statement.setInt(4, product.getProduct_nummer());

        int count = statement.executeUpdate();

        return count > 0;
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
            "DELETE FROM product WHERE product_nummer = ?;"
        );

        statement.setInt(1, product.getProduct_nummer());

        int count = statement.executeUpdate();

        return count > 0;
    }

    private Product buildProduct(ResultSet result) throws SQLException {
        return new Product(
            result.getInt("product_nummer"),
            result.getString("naam"),
            result.getString("beschrijving"),
            result.getDouble("prijs")
        );
    }
}
