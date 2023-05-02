package ui;

import Service.PostService;
import entity.Posts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Controller implements Initializable {
Posts song = new Posts();
    @FXML
    private HBox recentlyPlayedContainer;

    @FXML
    private HBox favoriteContainer;
    @FXML
    private ImageView img;

    @FXML
    private Label songName;

    @FXML
    private Label artist;


    ObservableList <Posts> recentlyPlayed;

    @Override
    public void initialize(URL location, ResourceBundle resources) {     
   
    PostService service=new PostService();
    ObservableList <Posts> recentlyPlayed = service.getall();
    System.out.print(recentlyPlayed);
        for (Posts post : recentlyPlayed) {
               Pane postUI = service.createPostUI(post);
         /* songName.setText(song.getPost_title());
        song.setPost_image("/images/In_the_Name_of_Love.jpeg");
        song.setPost_title("post_title");
        song.setPost_content("post_content"); */
         recentlyPlayedContainer.getChildren().add(postUI);

      // Display the data from the first post in the UI
      // songName.setText(firstPost.getPost_title());
      //  artist.setText(firstPost.getPost_content()); 
      // Assuming you want to display post_content in the artist label
       
    
      }} 
    }     

