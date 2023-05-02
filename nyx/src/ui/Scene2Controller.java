/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import Service.CommentService;
import entity.Comment;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hmida
 */
public class Scene2Controller implements Initializable {

   
    @FXML
    private TableView<Comment> tbview;
    @FXML
    private TableColumn<?, ?> ci;
    @FXML
    private TableColumn<?, ?> cc;
        private Stage stage ;
        private Scene scene;
        private Parent  root;  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
 @FXML
public void displayComments() {


         CommentService service=new CommentService();
    ObservableList<Comment> commentList = service.getall();
             System.out.print(commentList);
    ci.setCellValueFactory(new PropertyValueFactory<>("comment_id"));
    cc.setCellValueFactory(new PropertyValueFactory<>("comment_content"));
    tbview.setItems(commentList);
    
    
    
    
}
 public void switchtoscenehello(ActionEvent event) throws IOException
    {
      Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      
      stage.setScene(scene);
      stage.show();
      
    }


    
    }  
    

