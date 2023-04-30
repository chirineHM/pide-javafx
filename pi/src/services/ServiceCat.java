package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Categorie;
 import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.Connect;

public class ServiceCat {
	private Connection connection = Connect.getInstance().getConnection();

	public ServiceCat() {
		 
	};

    public ObservableList<Categorie> afficherTous() {
        ObservableList<Categorie> produits = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM categorie";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        
                        int id = resultSet.getInt("id");
                      
                        String nom = resultSet.getString("nom_c");
                        String description = resultSet.getString("description_c");
           
                        produits.add(new Categorie(id,  nom, description ));
                    }
                }
            }
            return produits;
        } catch (SQLException e) {
            System.out.println("Error while fetching all products from database: " + e.getMessage());
            return null;
        }
    }

}
