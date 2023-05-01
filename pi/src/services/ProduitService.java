package services;
import entities.Panier;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitService {
	 
	private Connection connection = Connect.getInstance().getConnection();;


    public ProduitService( ) {
         
    }

    public void addProduit(Produit produit) throws SQLException {
        String query = "INSERT INTO produit (id, categorie_id, nom_p, description_p, prix_p, quantite_p, image, quantite_dans_panier,image_code_qr) VALUES (?, ?, ?, ?, ?,?,?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, produit.getId());
            statement.setInt(2, produit.getCategorieId());
            statement.setString(3, produit.getNom());
            statement.setString(4, produit.getDescription());
            statement.setDouble(5, produit.getPrix());
            statement.setInt(6, produit.getQuantite());
            statement.setString(7, produit.getImage());
            statement.setInt(8, 0);
            statement.setString(9, produit.getImage_code_qr());
            statement.executeUpdate();
        }
    }


    public Produit getProduitById(int id) throws SQLException {
        String query = "SELECT * FROM produit WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    double prix = resultSet.getDouble("prix_p");
                    int quantite = resultSet.getInt("quantite_p");
                    int quantiteDansPanier = resultSet.getInt("quantite_dans_panier");
                    int categorieId = resultSet.getInt("categorie_id");
                    String nom = resultSet.getString("nom_p");
                    String description = resultSet.getString("description_p");
                    String image = resultSet.getString("image");
                    return new Produit(id, categorieId, nom, description, prix, quantite, image, quantiteDansPanier);
                } else {
                    return null;
                }
            }
        }
    }


    public ObservableList<Produit> afficherTous() {
        ObservableList<Produit> produits = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM produit";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        double prix = resultSet.getDouble("prix_p");
                        int id = resultSet.getInt("id");
                        int quantite = resultSet.getInt("quantite_p");
                        Integer quantiteDansPanier = resultSet.getInt("quantite_dans_panier");
                        int categorieId = resultSet.getInt("categorie_id");
                        String nom = resultSet.getString("nom_p");
                        String description = resultSet.getString("description_p");
                        String image = resultSet.getString("image");
                        produits.add(new Produit(id,categorieId, nom, description, prix, quantite, image, quantiteDansPanier));
                    }
                }
            }
            return produits;
        } catch (SQLException e) {
            System.out.println("Error while fetching all products from database: " + e.getMessage());
            return null;
        }
    }

    public void updateProduit(Produit produit) throws SQLException {
        String query = "UPDATE produit SET prix_p = ?, quantite_p = ?, quantite_dans_panier = ?, categorie_id = ?, nom_p = ?, description_p = ?, image = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, produit.getPrix());
            statement.setInt(2, produit.getQuantite());
            statement.setInt(3, produit.getQuantiteDansPanier());
            statement.setInt(4, produit.getCategorieId());
            statement.setString(5, produit.getNom());
            statement.setString(6, produit.getDescription());
            statement.setString(7, produit.getImage());
            statement.setInt(8, produit.getId());
            statement.executeUpdate();
        }
    }
    
    
        public void updateProd(Produit produit) throws SQLException {
        String query = "UPDATE produit SET prix_p = ?, quantite_p = ?, nom_p = ?, description_p = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, produit.getPrix());
            statement.setInt(2, produit.getQuantite());
            statement.setString(3, produit.getNom());
            statement.setString(4, produit.getDescription());
            statement.setInt(5, produit.getId());
            statement.executeUpdate();
        }
    }

    
public void deleteProduit(int id) throws SQLException {
    String query = "DELETE FROM produit WHERE id_p = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, id);
        statement.executeUpdate();
    }
} 

}
