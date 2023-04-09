/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import entity.Posts;
import Service.PostService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author MSI GF63
 */
public class FXMLController implements Initializable {
      Posts p = new Posts();
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfhashtag;
    @FXML
    private Button tfadd;
    @FXML
    private Button tfupdate;
    @FXML
    private Button tfremove;
    @FXML
    private TableView<Posts> tftableview;
    @FXML
    private TableColumn<?, ?> tcclient;
    @FXML
    private TableColumn<?, ?> tcdescription;
    @FXML
    private TableColumn<?, ?> tchashtag;
    @FXML
    private TableColumn<?, ?> tcdate;
    @FXML
    private TableColumn<?, ?> tcvisibility;
    @FXML
    private TableColumn<?, ?> images;
    @FXML
    private ImageView imagep;

    
    @FXML
    private void addphoto(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Ajouter une Image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"));
        File f = fc.showOpenDialog(null);
        String DBPath = "C:\\\\\\\\xampp\\\\\\\\htdocs\\\\\\\\Version-Integre\\\\\\\\public\\\\\\\\uploads\\\\\\\\"+f.getName();
        String i = f.getName();
        p.setPost_image(i);
        if (f != null){
        BufferedImage bufferedImage = ImageIO.read(f);
        WritableImage image = SwingFXUtils.toFXImage(bufferedImage,null);
        ImageIO.write(bufferedImage, "png", new File(DBPath));
        imagep.setImage(image);
        FileInputStream fin =new FileInputStream(f);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte [1024];
        for (int readNum ;(readNum= fin.read(buf)) != -1 ;){
            bos.write(buf,0,readNum);
            byte[] post_image = bos.toByteArray();
}
        } 
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
      PostService service=new PostService();
          ObservableList<Posts> list = service.getall();
          System.out.print(list);
          tcclient.setCellValueFactory(new PropertyValueFactory<>("id"));
          tcdescription.setCellValueFactory(new PropertyValueFactory<>("post_title"));
          tchashtag.setCellValueFactory(new PropertyValueFactory<>("post_content"));
          tcdate.setCellValueFactory(new PropertyValueFactory<>("post_date"));
          tcvisibility.setCellValueFactory(new PropertyValueFactory<>("visibilite"));
          tftableview.setItems(list);
         
          /*images.setCellFactory(param -> {
       //Set up the ImageView
       final ImageView imageview = new ImageView();
       imageview.setFitHeight(70);
       imageview.setFitWidth(100);

       //Set up the Table
       TableCell<Post, Image> cell = new TableCell<Post, Image>() {
           public void updateItem(Image item, boolean empty) {
             if (item != null) {
                 final ImageView imageview = new ImageView();
       imageview.setFitHeight(70);
       imageview.setFitWidth(100);
                  imageview.setImage(item);
             }
           }
        };
        // Attach the imageview to the cell
        cell.setGraphic(imageview);
        return cell;
   });
          images.setCellValueFactory(new PropertyValueFactory<Post, Image>("imageP"));*/
    }    

    

    
    
}
