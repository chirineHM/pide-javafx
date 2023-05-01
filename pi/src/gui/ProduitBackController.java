/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tools.Connect;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class ProduitBackController implements Initializable {

    @FXML
    private TableView<Produit> tvProdback;
    
    
    ObservableList<Produit> pList = FXCollections.observableArrayList();
    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    
    @FXML
    private TableColumn<?, ?> colNom;
    @FXML
    private TableColumn<?, ?> colDesc;
    @FXML
    private TableColumn<?, ?> colPrix;
    @FXML
    private TableColumn<?, ?> colQ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Produit> list = getpList();

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colQ.setCellValueFactory(new PropertyValueFactory<>("quantite"));


        tvProdback.setItems(list);
        
    }
    
    @FXML
   public void refresh() {
        ObservableList<Produit> list = getpList();

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colQ.setCellValueFactory(new PropertyValueFactory<>("quantite"));


        tvProdback.setItems(list);
    }


    
    
    public ObservableList<Produit> getpList() {
        cnx = Connect.getInstance().getConnection();
        pList.clear();

        try {
            String query2 = "SELECT * FROM  produit ";
            PreparedStatement smt = cnx.prepareStatement(query2);
            Produit usr;
            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                usr = new Produit(rs.getInt("id"), rs.getString("nom_p"), rs.getString("description_p"), rs.getDouble("prix_p"), rs.getInt("quantite_p"));
                pList.add(usr);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pList;
    }
    
    
    
    @FXML
      private void Suppression(ActionEvent event) {    
        Produit usr = (Produit) tvProdback.getSelectionModel().getSelectedItem();
        if( usr!=null ) {
        //
                try {
            String requete="delete from produit where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,usr.getId());
            pst.executeUpdate();
            
            System.out.println("produit est supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        //
        refresh();        
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("pi :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("produit supprimé");
                alert.showAndWait();  
    }
        else{
            System.out.println("produit not selected ");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("pi :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a user from the table ");
                alert.showAndWait();  
        }
    }
      
      
    @FXML
      public void modification()
      {
          
                  Produit usr = (Produit) tvProdback.getSelectionModel().getSelectedItem();
        if( usr!=null ) {
        //

        
                   try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/modifProd.fxml"));
                Parent root77 = loader.load();
                ModifProdController controllerB = loader.getController();
                controllerB.recevoir(usr);
                Scene scene77 = new Scene(root77);
                Stage newStage77 = new Stage();
                newStage77.setScene(scene77);
                newStage77.show();
                //
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        
        
        
        //
        refresh();        
  
    }
        else{
            System.out.println("produit not selected ");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("pi :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a user from the table ");
                alert.showAndWait();  
        }
          
          
      }
      
      public void exportpdf()
      {
       
          
    // Create content list
List<String> content = new ArrayList<>();

// Add header row to the content list
content.add("liste produit");

// Populate table content from userList TableView
for (Produit user : tvProdback.getItems()) {
    String row = user.toString();
    content.add(row);
}

// Create a new PDF document
PDDocument document = new PDDocument();

// Add a new page to the document
PDPage page = new PDPage();
document.addPage(page);

// Create a new font for the header
PDType1Font font = PDType1Font.HELVETICA;

// Add content to the PDF document
try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
    // Add the title
    contentStream.beginText();
    contentStream.setFont(font, 36);
    contentStream.newLineAtOffset(50, 650);
    contentStream.showText("prod list");
    contentStream.endText();

    // Add the table data
    contentStream.beginText();
    contentStream.setFont(font, 12);
    contentStream.newLineAtOffset(50, 600);

    for (int i = 0; i < content.size(); i++) {
        contentStream.showText(content.get(i));
        contentStream.newLineAtOffset(0, -20);
    }

    contentStream.endText();
    contentStream.close();
}catch(Exception e) {
        e.printStackTrace();
}

// Save the PDF document to a file or stream
    try {
        document.save("prod_list.pdf");
        document.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    
          
          
      }        

    
    
}
