package ui;

import Service.PostService;
import entity.Posts;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SongController {
    @FXML
    private ImageView img;

    @FXML
    private Label songName;

    @FXML
    private Label artist;

    public void setData(Posts song){
       
        Image image = new Image(getClass().getResourceAsStream(song.getPost_image()));
        img.setImage(image);
        songName.setText(song.getPost_title());
        artist.setText(song.getPost_content());
    }
}
