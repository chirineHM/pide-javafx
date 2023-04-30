/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Commande;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
 import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import services.CommandeService;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *

 */
public class AffichageCommandeController implements Initializable {
	 
   
    @FXML
    private TableView<Commande> tableView;
    @FXML
    private TableColumn<String, String> date;
    @FXML
    private TableColumn<String, Double> colPrix;
    @FXML
    private TableColumn<Commande, ImageView> statut;
    
    
    private TableColumn<Commande, Void> colModifBtn;
    private TableColumn<Commande, Void> colSuppBtn;
    private TableColumn<Commande, Void> colExpBtn;
    CommandeService a = new CommandeService();
    public static Commande pr ; 
    @FXML
    private TableColumn<String, String> addr;
    @FXML
    private TextField filtre ;
 ObservableList<Commande> obList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     colSuppBtn = new TableColumn<>("Supprimer");
        tableView.getColumns().add(colSuppBtn);

        colModifBtn = new TableColumn<>("Modifier");
        tableView.getColumns().add(colModifBtn);

//
    a = new CommandeService();
        obList = a.afficherTous();
//        
         addButtonModifToTable();
        addButtonDeleteToTable();
        
       
      
        
         date.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
          statut.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
        addr.setCellValueFactory(new PropertyValueFactory<>("adresseLivraison"));

// Sort the list by price ascending
     // Load the data from the CompetitionService into the TableView
   
     tableView.setItems(obList);

    
    }    

    
    Button btn;
    Commande A = new Commande();
    public void addButtonModifToTable() {
        Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>> cellFactory = new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(final TableColumn<Commande, Void> param) {

                final TableCell<Commande, Void> cell = new TableCell<Commande, Void>() {

                    {
                    	btn = new Button("Modifier");
                    	btn.setOnAction((event) -> {
                    	    try {
                    	    	A = tableView.getSelectionModel().getSelectedItem();
                    	    	if (A != null) {

                         	        System.out.println("hello");
                        	        System.out.println("DATA =" + A);
                         	        // Créer une boîte de dialogue
                        	        Dialog<Commande> dialog = new Dialog<>();
                        	        dialog.setTitle("Modifier la commande");

                        	        // Créer un bouton pour valider la modification
                        	        ButtonType validerButtonType = new ButtonType("Valider", ButtonData.OK_DONE);
                        	        dialog.getDialogPane().getButtonTypes().addAll(validerButtonType, ButtonType.CANCEL);

                        	        // Créer les champs de saisie pour la modification
                        	        TextField adresseLivraisonTextField = new TextField();

                        	        TextField prixTotalTextField = new TextField();
                        	        prixTotalTextField.setText(Double.toString(A.getPrixTotal()));
                        	        // Ajouter les champs à la boîte de dialogue
                        	        GridPane grid = new GridPane();
                        	        grid.add(new Label("Adresse de livraison:"), 0, 0);
                        	        grid.add(adresseLivraisonTextField, 1, 0);
                        	        grid.add(new Label("Prix total:"), 0, 1);
                        	        grid.add(prixTotalTextField, 1, 1);
                        	        dialog.getDialogPane().setContent(grid);

                        	        // Valider la modification lorsque le bouton "Valider" est cliqué
                        	        dialog.setResultConverter(dialogButton -> {
                        	            if (dialogButton == validerButtonType) {
                        	                Commande modifiedCommande = new Commande(A.getId(), A.getPanierId(), A.getDateCommande(), adresseLivraisonTextField.getText(), Double.parseDouble(prixTotalTextField.getText()), A.getStatus());
                        	                return modifiedCommande;
                        	            }
                        	            return null;
                        	        });

                        	        // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
                        	        Optional<Commande> result = dialog.showAndWait();

                        	        // Si l'utilisateur a cliqué sur "Valider", mettre à jour la commande dans le TableView
                        	        result.ifPresent(modifiedCommande -> {
                        	            int index = tableView.getSelectionModel().getSelectedIndex();
                        	            tableView.getItems().set(index, modifiedCommande); // Update the Commande object in the TableView
                        	            tableView.refresh(); // Refresh the TableView to show the updated Commande object                        	            // Appeler le service de mise à jour de commande pour enregistrer les modifications
                        	            try {
    										a.updateCommande(modifiedCommande);
    									} catch (SQLException e) {
    										// TODO Auto-generated catch block
    										e.printStackTrace();
    									}
                        	        });
                    	    	}


                    	    } catch (Exception e) {
                    	        e.printStackTrace();
                    	    }
                    	});
                    }
 
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colModifBtn.setCellFactory(cellFactory);
    }

    Button btnSupprimer;
      private Label label;

    private void showConfirmation(Commande pr) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce commande ?");
        alert.setContentText("");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("pas selection!");
        } else if (option.get() == ButtonType.OK) {
            System.out.println(" iddd=" + pr.getId());
            a.supprimer(pr.getId());
            obList.clear();
            
        } else if (option.get() == ButtonType.CANCEL) {
            this.label.setText("Exit!");
        } else {
            this.label.setText("-");
        }
    }

    public void addButtonDeleteToTable() {
        Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>> cellFactory = new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(final TableColumn<Commande, Void> param) {

                final TableCell<Commande, Void> cell = new TableCell<Commande, Void>() {

                    {
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer.setOnAction((ActionEvent event) -> {

                            A = tableView.getSelectionModel().getSelectedItem();
                         pr=A;
                          showConfirmation(pr);
                           try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageCommande.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnSupprimer);
                        }
                    }
                };
                return cell;
            }
        };
        colSuppBtn.setCellFactory(cellFactory);

    }

    @FXML
    private void ajouterP(ActionEvent event) {
          try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AjoutCommande.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }

    @FXML
    private void boutiqueShow(ActionEvent event) {
         try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageCommandeFront.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }
    @FXML
    public void handleSearch(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String searchText = filtre.getText().trim();
            if (searchText.isEmpty()) {
                tableView.setItems(obList);
            } else {
                ObservableList<Commande> filteredList = FXCollections.observableArrayList();
                boolean productFound = false;
                for (Commande b : obList) {
                    // search for name or description
                    if ((b.getAdresseLivraison().toLowerCase().contains(searchText.toLowerCase())) 
                            || (b.getStatus().toLowerCase().contains(searchText.toLowerCase()))) {
                        filteredList.add(b);
                        productFound = true;
                    }
                }
                if (!productFound) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Commande non trouvé");
                    alert.setHeaderText("Aucun commande ne correspond à votre recherche");
                    alert.setContentText("Veuillez essayer une autre recherche.");
                    alert.showAndWait();
                }
                tableView.setItems(filteredList);
            }
        }
    }
    @FXML
    public void handleSortByPrice() {
        // Get the current list of products from the table view
        ObservableList<Commande> productList = tableView.getItems();

        boolean isSorted = true; // Flag to indicate if the list is already sorted
        for (int i = 0; i < productList.size() - 1; i++) {
            if (productList.get(i).getPrixTotal() > productList.get(i+1).getPrixTotal()) {
                isSorted = false;
                break;
            }
        }

        // Check if the list is already sorted by price ascending
        if (isSorted) {
            // If it is, sort by price descending instead
            productList.sort(Comparator.comparing(Commande::getPrixTotal).reversed());
        } else {
            // Otherwise, sort by price ascending
            productList.sort(Comparator.comparing(Commande::getPrixTotal));
        }

        // Update the table view with the sorted list
        tableView.setItems(productList);
    }



  
    @FXML
    public void Stat(  ActionEvent event ) {
    	try {
  	  Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.close();
  FXMLLoader loader = new FXMLLoader(getClass().getResource("Chart.fxml"));
  Parent root = loader.load();

  // Show the Modif.fxml interface
  Scene scene = new Scene(root);
  Stage stage1 = new Stage();
  stage1.setScene(scene);
  stage1.show();
  } 
    catch (IOException ex) {
      ex.printStackTrace();

   }
    }
}
    
    

