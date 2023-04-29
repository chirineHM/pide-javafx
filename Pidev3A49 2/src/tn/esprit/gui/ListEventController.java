/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tn.esprit.entities.Evenements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.services.EvenementServices;

/**
 * FXML Controller class
 *
 * @author medfaroukbenbelgacem
 */
public class ListEventController implements Initializable {

   @FXML
    private TableView<Evenements> xtable;
   
   @FXML
    private TableColumn<Evenements, Integer> xnb_part; 
    @FXML
    private TableColumn <Evenements, String>xnom_even;
    @FXML
    private TableColumn<Evenements, String> xdesc_event;
    @FXML
    private TableColumn <Evenements, String> xcategorie_even;
    @FXML
    private TableColumn<Evenements, Date> xdate;
    @FXML
    private TableColumn <Evenements, String> ximage_even;
   
    @FXML
    private TextField Mnom;
    @FXML
    private TextField Mdescription;
    @FXML
    private TextField Mcategorie;
    @FXML
    private TextField Mdate;
    @FXML
    private TextField Mimage;
    @FXML
    private TextField MnbPart;
    
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @FXML
    private TextField filterField;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       table();
        
    }  
    public void table(){
     xnom_even.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom_even()));
        xdesc_event.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDesc_event()));
        xcategorie_even.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategorie_even()));
//        xdate.setCellValueFactory(cellData -> {
//    Date date = cellData.getValue().getDate();
//    String formattedDate = dateFormat.format(date);
//    return new SimpleStringProperty(formattedDate);
//    });
        xdate.setCellFactory(tc -> new TableCell<Evenements, Date>() {
              private SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy") ;
         
              @Override
           protected void updateItem (Date item,boolean empty) {
              super.updateItem(item,empty);
                if (empty) {
                setText(null);
                }else {
              setText(dateFormat.format(item));
            }
          }
       });
       xdate.setCellValueFactory((new Callback<TableColumn.CellDataFeatures<Evenements, Date>, ObservableValue<Date>>() {
           @Override  
           public ObservableValue<Date> call(TableColumn.CellDataFeatures<Evenements, Date> p) {
               return new SimpleObjectProperty<>(p.getValue().getDate());
           }
       }));     

        ximage_even.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImage_even()));
        xnb_part.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNb_part()).asObject());
        
        
        EvenementServices crud = new EvenementServices();
        // Populate the table with data
        List<Evenements> data;

            data = crud.afficher();
            xtable.getItems().setAll(data);
            xtable.setRowFactory(tv -> {
		     TableRow<Evenements> myRow = new TableRow<>();
		     myRow.setOnMouseClicked ((event) -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            int myIndex =  xtable.getSelectionModel().getSelectedIndex();

		           int id = Integer.parseInt(String.valueOf(xtable.getItems().get(myIndex).getId()));
		            Mnom.setText(xtable.getItems().get(myIndex).getNom_even());
                            Mdescription.setText(xtable.getItems().get(myIndex).getDesc_event());
                            Mcategorie.setText(xtable.getItems().get(myIndex).getCategorie_even());
                            Mdate.setText(dateFormat.format(xtable.getItems().get(myIndex).getDate()));
                            Mimage.setText(xtable.getItems().get(myIndex).getImage_even());
                            MnbPart.setText(String.valueOf(xtable.getItems().get(myIndex).getNb_part()));
                           
                         
                           
		        }
		     });
		        return myRow;
                   });
    }
    @FXML
    private void supprimer(ActionEvent event) {
       EvenementServices crud = new EvenementServices();
    Evenements Evenement = xtable.getSelectionModel().getSelectedItem();

    if (Evenement != null) {
        int id = Evenement.getId();
        crud.supprimerEvenement(id);
        xtable.getItems().remove(Evenement);
    }}
     
 
    @FXML
    private void modifier_User(ActionEvent event) {
         EvenementServices crud = new EvenementServices();
        
        int myIndex = xtable.getSelectionModel().getSelectedIndex();
        int id = Integer.parseInt(String.valueOf(xtable.getItems().get(myIndex).getId()));
            String nom_even = Mnom.getText();
            String desc_event = Mdescription.getText();
            String categorie_even = Mcategorie.getText();
            String dateText = Mdate.getText();
            Date t = Date.valueOf(dateText);
            // Convertir le texte en type Date si nécessaire
            Date date = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Format de la date à adapter selon votre besoin
                date = (Date) dateFormat.parse(dateText);
            } catch (ParseException e) {
            // Gérer l'exception en cas d'erreur de conversion
            e.printStackTrace();
            }
            String image_even = Mimage.getText();
            int nb_part = Integer.valueOf(MnbPart.getText());
            
            crud.modifierEvenement( id, nb_part, nom_even, desc_event, categorie_even, image_even,t );
            
    }
      @FXML
    private void filter(KeyEvent event) {
        EvenementServices crud = new EvenementServices();
         ObservableList<Evenements> filteredPeople = FXCollections.observableArrayList(crud.afficher());
        //    ObservableList<Person> filteredPeople = people.filtered(p -> p.getAge() >= 30 && p.getAge() < 40);  

        ObservableList<Evenements> newdata = filteredPeople.stream()
                .filter(n -> n.getNom_even().toLowerCase().contains(filterField.getText().toLowerCase())
                || n.getDesc_event().toLowerCase().equals(filterField.getText())
                || n.getDesc_event().toLowerCase().contains(filterField.getText())
                || n.getNom_even().toLowerCase().contains(filterField.getText().toLowerCase())
                || n.getNom_even().toLowerCase().equals(filterField.getText()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        xtable.setItems(newdata);
    }
      @FXML
    private void participer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Interface_client.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
//                stage.setFullScreen(true);
                stage.show();

    }
     @FXML
    private void stat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Stats.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
//                stage.setFullScreen(true);
                stage.show();

    }

    @FXML
    private void gotoaccueil(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
//                stage.setFullScreen(true);
                stage.show();
    }
    
}
