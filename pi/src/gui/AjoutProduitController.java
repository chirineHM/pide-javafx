/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

 import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import services.ProduitService;
import services.ServiceCat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import entities.Categorie;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.image.Image;
 

/**
 * FXML Controller class
 *

 */
public class AjoutProduitController implements Initializable {
	@FXML
	private TextField urlTF;
 
	@FXML
	private Stage stage;

	
	private File file;

    @FXML
    private TextField nametv;
    @FXML
    private TextField stocktv;
    @FXML
    private TextField prixtv;
    @FXML
    private ImageView imgview;
    @FXML
    private Button ajoutbtn;
    
     String filePath="";
    
    
    ProduitService sp = new ProduitService();
    @FXML
    private AnchorPane anchorePaneEl;
    @FXML
    private TextArea descriptiontv;
    @FXML
    private ComboBox<Categorie> combo;

    /**
     * Initializes the controller class.
     */
    ServiceCat sc = new ServiceCat();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           //GET CATREGORIES LISTE DEROULANTE FOR JOIN !
        ServiceCat sc = new ServiceCat();

		setComboBoxItems();
 

    } 
       
    
    private void setComboBoxItems() {
		// Retrieve all reclamations and their corresponding users
		ObservableList<Categorie> users = sc.afficherTous();

		// Add the user names to the list

		// Set the items of the combo box
		combo.setItems(users);
		
combo.setConverter(new StringConverter<Categorie>() {
            @Override
            public String toString(Categorie object) {
                if (object != null) {
                    return object.getNom_c();
                } else return "";
            }

            @Override
            public Categorie fromString(String string) {
                return combo.getSelectionModel().getSelectedItem();
            }
        });
	}
     int stock;
    double prix;
    @FXML
    private void AjouterProduitHandle(ActionEvent event) {
        
          String nom = nametv.getText();
String description = descriptiontv.getText();
int stock;
double prix;

try {
    stock = Integer.parseInt(stocktv.getText());
    prix = Double.parseDouble(prixtv.getText());
    
    if (nom.isEmpty()) {
        showAlert("Nom obligatoire", "Nom doit être non vide");
    } else if (description.isEmpty()) {
        showAlert("Description obligatoire", "Description doit être non vide");
    } else {
        String regex = "^[0-9]\\.{0,1}[0-9]{0,2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(prixtv.getText());
        
        if (matcher.matches()) {
        	// G�n�rer l'image du code QR pour le produit
            String text = "Code QR pour le produit " + nom;
            ByteArrayOutputStream out = QRCode.from(text).to(ImageType.PNG).stream();

            // Convertir l'image en une cha�ne de caract�res encod�e en base64
            String base64Image = Base64.getEncoder().encodeToString(out.toByteArray());
            System.out.println("Base64 avant : " + base64Image);

            // Enregistrer la cha�ne de caract�res encod�e en base64 dans l'attribut image_qr_code de l'objet Produit
         // Afficher l'image dans une alerte
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Code QR pour le produit " + nom);
            alert.setHeaderText(null);

            // Cr�er un ImageView avec l'image du code QR encod�e en base64
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode(base64Image))));

            // Ajouter l'ImageView � la bo�te de dialogue de l'alerte
            alert.getDialogPane().setContent(imageView);
        
            // Afficher l'alerte
            alert.showAndWait();
            Produit produit = new Produit(combo.getSelectionModel().getSelectedItem().getId(),nom,description,prix,stock,file.getAbsolutePath() ,base64Image );
           
            try {
				sp.addProduit(produit);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Base64 apr�s : " + produit.getImage_code_qr());

            showAlert("Produit ajouté", "Produit ajouté avec succès");
        } else {
            showAlert("Prix non valide", "Prix doit être valide");
        } 
    } 
} catch (NumberFormatException e) {
    showAlert("Erreur de saisie", "Veuillez saisir des nombres valides pour le prix et le stock");
}
    }
// ...

private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
@FXML
private void Importer(ActionEvent event) {
	
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("S�lectionnez un fichier PNG");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg"));
    File fichierSelectionne = fileChooser.showOpenDialog(stage);

    if (fichierSelectionne != null) {
    	urlTF.setText(fichierSelectionne.getName());
        file = fichierSelectionne;
    }
	
}


   

    String nameCt="";
    int idCat=0;
    @FXML
    private void handleCat(ActionEvent event) {
        
        

    }

  
}

