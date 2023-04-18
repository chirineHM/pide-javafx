/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.ProductEntity;
import Tools.MaConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServices {

    private Connection connection;

    public ProductServices() {
        connection = MaConnection.getInstance().getCnx();
    }

    public void addProduct(ProductEntity product) throws SQLException {
        String query = "INSERT INTO produits (nom, description, prix, quantite) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, product.getNom());
        statement.setString(2, product.getDescription());
        statement.setDouble(3, product.getPrix());
        statement.setInt(4, product.getQuantite());
        statement.executeUpdate();
    }

    public void updateProduct(ProductEntity product) throws SQLException {
        String query = "UPDATE produits SET nom=?, description=?, prix=?, quantite=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, product.getNom());
        statement.setString(2, product.getDescription());
        statement.setDouble(3, product.getPrix());
        statement.setInt(4, product.getQuantite());
        statement.setInt(5, product.getId());
        statement.executeUpdate();
    }

    public void deleteProduct(int id) throws SQLException {
        String query = "DELETE FROM produits WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public ProductEntity getProductById(int id) throws SQLException {
        String query = "SELECT * FROM produits WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            ProductEntity product = new ProductEntity();
            product.setId(resultSet.getInt("id"));
            product.setNom(resultSet.getString("nom"));
            product.setDescription(resultSet.getString("description"));
            product.setPrix(resultSet.getDouble("prix"));
            product.setQuantite(resultSet.getInt("quantite"));
            return product;
        }
        return null;
    }

    public List<ProductEntity> getAllProducts() throws SQLException {
        String query = "SELECT * FROM produits";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<ProductEntity> products = new ArrayList<>();
        while (resultSet.next()) {
            ProductEntity product = new ProductEntity();
            product.setId(resultSet.getInt("id"));
            product.setNom(resultSet.getString("nom"));
            product.setDescription(resultSet.getString("description"));
            product.setPrix(resultSet.getDouble("prix"));
            product.setQuantite(resultSet.getInt("quantite"));
            products.add(product);
        }
        return products;
    }
}

