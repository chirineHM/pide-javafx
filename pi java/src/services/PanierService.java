package services;
import entities.Commande;
import entities.Panier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PanierService {
	private Connection connection = Connect.getInstance().getConnection();;

 
    public PanierService( ) {
         
    }

    public void createPanier(Panier panier) throws SQLException {
        String sql = "INSERT INTO panier (id, quantite, date_ajout) VALUES (?, ?, ?)";
        try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, panier.getId());
			statement.setInt(2, panier.getQuantite());
			statement.setDate(3, java.sql.Date.valueOf(panier.getDateAjout()));
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Panier getPanierById(int id) throws SQLException {
        String sql = "SELECT * FROM panier WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            LocalDate dateAjout = result.getDate("date_ajout").toLocalDate();
            int quantite = result.getInt("quantite");
            return new Panier(id, quantite, dateAjout);
        } else {
            return null;
        }
    }


	public ObservableList<Panier> afficherTous() {
		ObservableList<Panier> paniers = FXCollections.observableArrayList();
		 
        try {
			String sql = "SELECT * FROM panier";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			 while (result.next()) {
				 LocalDate date = result.getDate("date").toLocalDate();
			    int id = result.getInt("id");
			    int quantity = result.getInt("quantity");
			    paniers.add(new Panier(id, quantity,date));
			 }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paniers;
    }

	public void updatePanier(Panier panier) throws SQLException {
	    String sql = "UPDATE panier SET quantite = ?, date_ajout = ? WHERE id = ?";
	    PreparedStatement statement = connection.prepareStatement(sql);
	    statement.setInt(1, panier.getQuantite());
	    statement.setDate(2, java.sql.Date.valueOf(panier.getDateAjout()));
	    statement.setInt(3, panier.getId());
	    statement.executeUpdate();
	}

    public void deletePanier(int id) throws SQLException {
        try {
			String sql = "DELETE FROM panier WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
}
