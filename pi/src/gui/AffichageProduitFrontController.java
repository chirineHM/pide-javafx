/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

 
import entities.Commande;
import entities.Panier;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.CommandeService;
import services.ProduitService;
 
/**
 * FXML Controller class
 *
  */
public class AffichageProduitFrontController implements Initializable {

    @FXML
    private TilePane produitsTilePane;
   ProduitService a = new ProduitService();
    public static Produit pr ; 
    @FXML
    private Button panierButton;
  
 ObservableList<Produit> obList;

    /**
     * Initializes the controller class.
     */
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
         a = new ProduitService();
        obList = a.afficherTous();
        Panier panier = new Panier();
      
        for (Produit produit : obList) {
            Card card = new Card(produit, panier);
            produitsTilePane.getChildren().add(card);
        }
        
 
    }

    private class Card extends VBox {
    	private Produit produit;
        private Panier panier;

        public Card(Produit produit, Panier panier) {
             
            this.panier = panier;
    	 
    	    File file = new File(produit.getImage());
    	    Image image = new Image(file.toURI().toString());

    	    // Cr�er une vue d'image et l'ajouter � un conteneur ou � la sc�ne principale
    	    ImageView imageView = new ImageView(image);
    	    imageView.setFitWidth(150);
    	    imageView.setFitHeight(150);
    	    Label nomLabel = new Label(produit.getNom());
    	    Label descriptionLabel = new Label(produit.getDescription());
    	    Label prixLabel = new Label("Prix: " + produit.getPrix());
    	    prixLabel.setTextFill(Color.RED);
    	    VBox.setMargin(imageView, new Insets(10, 0, 0, 0));
    	    VBox.setMargin(nomLabel, new Insets(10, 0, 0, 0));
    	    VBox.setMargin(descriptionLabel, new Insets(10, 0, 0, 0));
    	    VBox.setMargin(prixLabel, new Insets(10, 0, 0, 0));

    	    // Create heart icons
    	    FontAwesomeIconView panierIcon = new FontAwesomeIconView(FontAwesomeIcon.SHOPPING_CART);
    	 // D�finir la taille de l'ic�ne
    	    panierIcon.setSize("2em");

    	    // D�finir la couleur de l'ic�ne
    	    panierIcon.setFill(Color.GREEN);
    	    
    	    panierIcon.setOnMouseClicked(arg0 -> {      
    	    	System.out.println("Produit ajout� au panier");
            panier.ajouterProduit(produit);
            PanierPopup panierPopup = new PanierPopup(panier);
            panierPopup.show(panierIcon.getScene().getWindow());
	    	System.out.println("afficher");

    	    	});
     
    	    // Add heart button to card
    	    HBox heartBox = new HBox();
    	    heartBox.setAlignment(Pos.CENTER_RIGHT);
    	    heartBox.getChildren().addAll(panierIcon);
    	    VBox.setMargin(heartBox, new Insets(0, 10, 0, 0));

    	    // Add all nodes to the card
    	    getChildren().addAll(imageView, nomLabel, descriptionLabel, prixLabel, heartBox);
    	    setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10;");
    	    setPrefWidth(200);
    	    setPrefHeight(250);
    	    getStyleClass().add("product-card");

        }

		
    }
      public class PanierPopup extends Popup {
     	    private VBox vbox;
    	    private Panier panier;
    	    private GridPane gridPane;

    	    public PanierPopup(Panier panier) {
    	        this.panier = panier;
    	        gridPane = new GridPane();
    	        gridPane.setHgap(10);
    	        gridPane.setVgap(5);
    	        gridPane.setPadding(new Insets(10));
    	        //Create close button
    	        Button closeButton = new Button("Fermer");
    	        closeButton.setOnAction(event -> {
     
    	        	   Window window = closeButton.getScene().getWindow();
    	        	    if (window instanceof Stage) {
    	        	        Stage stage = (Stage) window;
    	        	        stage.hide();
    	        	    } else {
    	        	        window.hide();
    	        	    }   	        });
    	        GridPane.setConstraints(closeButton, 1, 2); // Place le bouton en dessous des autres boutons
    	        gridPane.getChildren().add(closeButton);
    	        // Create list view
    	        ListView<Produit> listView = new ListView<>();
    	        listView.setCellFactory(param -> new ListCell<Produit>() {
    	            @Override
    	            protected void updateItem(Produit produit, boolean empty) {
    	                super.updateItem(produit, empty);
    	                if (empty || produit == null) {
    	                    setText(null);
    	                } else {
    	                    HBox hbox = new HBox();
    	                    hbox.setAlignment(Pos.CENTER_LEFT);
    	                    Label label = new Label(produit.getNom() + " - Quantit�: " + produit.getQuantite() + " - Prix: " + produit.getPrix());
    	  
    	                    hbox.getChildren().addAll(label );
    	                    setText(null);
    	                    setGraphic(hbox);
    	                }
    	            }
    	        });
    	         
    	         
    	        listView.setItems(FXCollections.observableArrayList(panier.getProduits()));
     	        GridPane.setConstraints(listView, 0, 0, 2, 1);
    	     // Create close button
    	     
    	        double prixTotal = calculerPrixTotal();
    	        Label labelPrixTotal = new Label();
    	        labelPrixTotal.setText("Total:" + prixTotal);
 
    	        labelPrixTotal.setTextFill(Color.RED);
    	        GridPane.setConstraints(labelPrixTotal, 0, 2);

    	        // Calculate initial total price
    	     // Calculate initial total price
    	        
    	        labelPrixTotal.setText("Total:" + prixTotal);
    	        System.out.println(prixTotal);
    	       System.out.println( prixTotal);
    	         // Create button to remove product from cart
    	        Button supprimerButton = new Button("Supprimer");
    	        supprimerButton.setOnAction(event -> {
    	            Produit produit = listView.getSelectionModel().getSelectedItem();

    	            if (produit != null) {
    	            	System.out.println("supp");
    	            	ObservableList<Produit> produits = listView.getItems();
    	            	produits.remove(produit);
        	            listView.refresh();

    	            	listView.setItems(produits);
    	                
    	                updatePrixTotalLabel(labelPrixTotal);
     	                
    	            }
    	            listView.refresh();
    	        });
    	        GridPane.setConstraints(supprimerButton, 0, 1);
    	      

    	        // Create passer commande button
    	        Button passerCommandeButton = new Button("Passer Commande");
    	        passerCommandeButton.setOnAction(event -> {
    	        	CommandeService sc=new CommandeService();
    	        	Commande c =new Commande();
  
    	        	c.setPanierId(4);
     	        	c.setAdresseLivraison("Tunis");
    	        	c.setPrixTotal(calculerPrixTotal());
    	        	c.setStatus("en cours");
    	        	try {
						sc.createCommande(c);
						 Window window = passerCommandeButton.getScene().getWindow();
	    	        	    if (window instanceof Stage) {
	    	        	        Stage stage = (Stage) window;
	    	        	        stage.hide();
	    	        	    } else {
	    	        	        window.hide();
	    	        	    } 
						Alert alert = new Alert(AlertType.INFORMATION);
					    alert.setHeaderText(null);
					    alert.setContentText("Commande ajout�e avec succ�s et mail envoyee avec succes");
					    alert.showAndWait();
					   

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	        });
    	        GridPane.setConstraints(passerCommandeButton, 1, 1);
    	     
    	        // Add all nodes to the grid pane
    	     // Create label for total price
    	     

    	        // Add label to grid pane
    	        gridPane.getChildren().addAll(listView, supprimerButton,passerCommandeButton, labelPrixTotal);

    	        // Set grid pane as the content of the popup
    	        this.getContent().add(new BorderPane(gridPane));
    	    }
    	    private void updatePrixTotalLabel(Label labelPrixTotal) {
    	        double prixTotal = calculerPrixTotal();
    	        labelPrixTotal.setText("Total:" + prixTotal);
    	    }

    public void addPanierButton(Button button) {
    if (gridPane == null) {
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10));
        this.getContent().add(new BorderPane(gridPane));
    }
  //  int numRows = gridPane.getRowCount();
  //  int numColumns = (int) Math.ceil(gridPane.getChildren().size() / (double) numRows);
    //GridPane.setConstraints(button, numColumns, numRows);
    gridPane.getChildren().add(button);
}

    	    private double calculerPrixTotal() {
    	        double prixTotal = 0.0;
    	        for (Produit produit : panier.getProduits()) {
    	            prixTotal += produit.getPrix();
    	        }
    	        return prixTotal;
    	    }
    	}
    	        
    	  
} 