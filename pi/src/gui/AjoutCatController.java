/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tools.Connect;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjoutCatController implements Initializable {

    @FXML
    private TextField tfnp;
    @FXML
    private TextField tfcp;

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
    }    
    
    
    @FXML
    private void createReclamation()
    {
             cnx = Connect.getInstance().getConnection();
            String query="INSERT INTO categorie ( nom_c, description_c ) "
                    + "VALUES (?, ?)";   
            
         //
                     
            try {
                if(tfnp.getText().isEmpty() | tfcp.getText().isEmpty() )
                {
                  //empty fields
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("pi :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Entrer all blank fields !!");
                alert.showAndWait();  
                }
                
                else if ( tfnp.getText().length() < 2 | tfcp.getText().length() < 2 ){
                //user bs
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("pi :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Titre et desc doivent etre remplie !!");
                alert.showAndWait();  
                }
                
                else{

                    PreparedStatement smt = cnx.prepareStatement(query);
                    
            smt.setString(1, tfnp.getText());
            smt.setString(2, tfcp.getText());            
            smt.executeUpdate();
             
                System.out.println("ajout avec succee");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("pi :: ajout");
                alert.setHeaderText(null);
                alert.setContentText("ajout ");
                alert.showAndWait();
                
                }
                
                
            }catch(SQLException ex){
         System.out.println(ex.getMessage());
            }

         //
            
    }
    
    
}
