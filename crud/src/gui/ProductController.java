/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import entities.ProductEntity;
import Services.ProductServices;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Tools.MaConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
public class ProductController {
    
    @FXML
    private TableView<ProductEntity> productTable;
    
    @FXML
    private TableColumn<ProductEntity, Integer> idColumn;
    
    @FXML
    private TableColumn<ProductEntity, String> nameColumn;
    
    @FXML
    private TableColumn<ProductEntity, String> descColumn;
    
    @FXML
    private TableColumn<ProductEntity, Double> priceColumn;
    
    @FXML
    private TableColumn<ProductEntity, Integer> quantityColumn;
    
     @FXML
    private TextField nameField;
    
    @FXML
    private TextField descField;
    
    @FXML
    private TextField priceField;
    
    @FXML
    private TextField quantityField;
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button editButton;
    
    @FXML
    private Button deleteButton;
    
    private ObservableList<ProductEntity> productList;
    private ProductServices produitService = new ProductServices();
    
    @FXML
    private void initialize() throws SQLException {
        /*idColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNom());
        descColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrix().asObject());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantite().asObject());*/
        
        productList = FXCollections.observableArrayList();
        productList.addAll(produitService.getAllProducts());
        productTable.setItems(productList);
        
        addButton.setOnAction(e -> {
            try {
                handleAddProduct();
            } catch (SQLException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        editButton.setOnAction(e -> {
            try {
                handleEditProduct();
            } catch (SQLException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //deleteButton.setOnAction(e -> handleDeleteProduct());
    }
    
    private void handleAddProduct() throws SQLException {
        String name = nameField.getText();
        String desc = descField.getText();
        Double price = Double.parseDouble(priceField.getText());
        Integer quantity = Integer.parseInt(quantityField.getText());
        
        ProductEntity newProduct = new ProductEntity(name, desc, price, quantity);
        produitService.addProduct(newProduct);
        productList.add(newProduct);
        
        clearFields();
    }
    
    private void handleEditProduct() throws SQLException {
        ProductEntity selectedProduct = productTable.getSelectionModel().getSelectedItem();
        
        if (selectedProduct != null) {
            String name = nameField.getText();
            String desc = descField.getText();
            Double price = Double.parseDouble(priceField.getText());
            Integer quantity = Integer.parseInt(quantityField.getText());
            
            selectedProduct.setNom(name);
            selectedProduct.setDescription(desc);
            selectedProduct.setPrix(price);
            selectedProduct.setQuantite(quantity);
            
            produitService.updateProduct(selectedProduct);
            productTable.refresh();
            
            clearFields();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Product Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a product in the table.");
            alert.showAndWait();
        }
    }

    private void clearFields() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}