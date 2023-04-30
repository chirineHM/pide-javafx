/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import entities.Produit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.Connect;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class CategorieBackController implements Initializable {

    @FXML
    private TableView<Categorie> tvCat;
    @FXML
    private TableColumn<?, ?> colNomCat;
    @FXML
    private TableColumn<?, ?> colDesCat;
    
        
    ObservableList<Categorie> cList = FXCollections.observableArrayList();
    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                ObservableList<Categorie> list = getcList();
                System.out.println("size "+list.size());
        colNomCat.setCellValueFactory(new PropertyValueFactory<>("nom_c"));
        colDesCat.setCellValueFactory(new PropertyValueFactory<>("description_c"));


        tvCat.setItems(list);
    }    
    
    
    @FXML
        public void refreshc() {
        // TODO
                ObservableList<Categorie> list = getcList();
                System.out.println("size "+list.size());

        colNomCat.setCellValueFactory(new PropertyValueFactory<>("nom_c"));
        colDesCat.setCellValueFactory(new PropertyValueFactory<>("description_c"));


        tvCat.setItems(list);
    }    
    
 

 public ObservableList<Categorie> getcList() {
        cnx = Connect.getInstance().getConnection();
        cList.clear();

        try {
            String query2 = "SELECT * FROM  CATEGORIE ";
            PreparedStatement smt = cnx.prepareStatement(query2);
            Categorie usr;
            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                usr = new Categorie(rs.getInt("id"), rs.getString("nom_c"), rs.getString("description_c"));
                cList.add(usr);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cList;
    }    
    
    
    @FXML
      private void Suppressioncat(ActionEvent event) {    
        Categorie usr = (Categorie) tvCat.getSelectionModel().getSelectedItem();
        if( usr!=null ) {
        //
                try {
            String requete="delete from Categorie where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,usr.getId());
            pst.executeUpdate();
            
            System.out.println("catego est supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        //
        refreshc();        
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("pi :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("catego supprimé");
                alert.showAndWait();  
    }
        else{
            System.out.println("catego not selected ");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("pi :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a catego from the table ");
                alert.showAndWait();  
        }
    }

 
    
}
