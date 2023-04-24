package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.typeSub;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.SubService;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;


public class SubscriptionController implements Initializable {

    // Inject the FXML elements

    @FXML
    private Button tfadd;
     @FXML
    private Button tfmod;

    @FXML
private TextField nameTextField;
@FXML
private TextField descriptionTextField;
@FXML
private TextField etatTextField;


    @FXML
    private TableView<typeSub> subscriptionTable;
  @FXML
private TableColumn<typeSub, Integer> idTableColumn;

    @FXML
    private TableColumn<typeSub, String> nameColumn;
    @FXML
    private TableColumn<typeSub, String> descriptionColumn;
    @FXML
    private TableColumn<typeSub, String> etatColumn;
    @FXML
     private Button tfremove;
    private SubService subscriptionService;

@FXML
private Label abonnementsLabel;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private Button printButton;
@FXML
private Button payerButton;
@FXML
private Button googleButton;

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        subscriptionService = new SubService();

        
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));

        
        refreshSubscriptionTable();

        // Debugging messages
        System.out.println("Initialized subscription table");
        System.out.println("Subscription table items: " + subscriptionTable.getItems());
        System.out.println("Subscription table columns: " + subscriptionTable.getColumns());
        searchButton.setOnAction(this::search);

        
         abonnementsLabel.setOnMouseClicked(event -> {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Abonnements.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
         googleButton.setOnAction(event -> {
    String url1 = "https://www.google.com/";
    try {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url1));
    } catch (IOException e) {
        e.printStackTrace();
    }
});

        
    }
    @FXML
private void search(ActionEvent event) {
    String searchTerm = searchField.getText();
    if (!searchTerm.isEmpty()) {
        List<typeSub> searchResults = subscriptionService.search(searchTerm);
        subscriptionTable.getItems().clear();
        subscriptionTable.getItems().addAll(searchResults);
    } else {
        refreshSubscriptionTable();
    }
}


    // Method to refresh the subscription table with the latest data
    private void refreshSubscriptionTable() {
        if (subscriptionService != null) {
            // Get the list of subscriptions from the service
            List<typeSub> subscriptions = subscriptionService.getall();

            // Clear the table and add the new data
            subscriptionTable.getItems().clear();
            subscriptionTable.getItems().addAll(subscriptions);
        }
    }
  @FXML
private void remove(ActionEvent event) {
    typeSub p = subscriptionTable.getSelectionModel().getSelectedItem();
    SubService service = new SubService();
    service.supprimer(p.getId());
    refreshSubscriptionTable();
}

private void tableClicked(MouseEvent event) {
    typeSub p = subscriptionTable.getSelectionModel().getSelectedItem();
    if (p != null) {
        nameTextField.setText(p.getName());
        descriptionTextField.setText(p.getDescription());
        etatTextField.setText(p.getEtat());
    }
}

@FXML
private void Add(ActionEvent event) {
    SubService service = new SubService();

    String name = nameTextField.getText();
String description = descriptionTextField.getText();
String etat = etatTextField.getText();

if (name != null && !name.isEmpty() && !name.matches(".*\\d.*") 
    && description != null && !description.isEmpty() && !description.matches(".*\\d.*") 
    && etat != null && !etat.isEmpty() && !etat.matches(".*\\d.*")) {
    
    typeSub newSubscription = new typeSub(name, description, etat);
    service.ajouter(newSubscription);
    refreshSubscriptionTable();
} else {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Champs obligatoires");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez remplir tous les champs obligatoires avec des valeurs valides.");
    alert.showAndWait();
}

}
@FXML
private void update(ActionEvent event) {
    // Get the selected row
    typeSub selectedSubscription = subscriptionTable.getSelectionModel().getSelectedItem();
    if (selectedSubscription != null) {
        // Update the selected row with the new data
        selectedSubscription.setName(nameTextField.getText());
        selectedSubscription.setDescription(descriptionTextField.getText());
        selectedSubscription.setEtat(etatTextField.getText());

        // Update the data in the database using SubService
        SubService subService = new SubService();
        subService.modifier(selectedSubscription);

        // Refresh the table to show the updated data
        refreshSubscriptionTable();
    } else {
        // Show an alert if no row is selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune sélection");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une ligne à modifier.");
        alert.showAndWait();
    }
}

@FXML
void handleTableClick(MouseEvent event) {
    // Get the selected row
    typeSub selectedSubscription = subscriptionTable.getSelectionModel().getSelectedItem();
    if (selectedSubscription != null) {
        // Populate the text fields with the data from the selected row
        nameTextField.setText(selectedSubscription.getName());
        descriptionTextField.setText(selectedSubscription.getDescription());
        etatTextField.setText(selectedSubscription.getEtat());
    }
}
@FXML
private void printTable(TableView<?> table, String filename) throws IOException {
    File file = new File(filename);
    FileWriter writer = new FileWriter(file);

    // Write table headers
    for (TableColumn<?, ?> col : table.getColumns()) {
        writer.write(col.getText() + ",");
    }
    writer.write("\n");

    // Write table data
    for (Object row : table.getItems()) {
        for (TableColumn<?, ?> col : table.getColumns()) {
            TableColumn<Object, ?> column = (TableColumn<Object, ?>) col;
            Object cellData = column.getCellData(row);
            writer.write(cellData + ",");
        }
        writer.write("\n");
    }

    writer.close();
}




}
