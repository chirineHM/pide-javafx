/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import entity.Posts;
import tools.Myconnection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 *
 * @author hmida
 */
public class PostService implements NewInterface <Posts> {
 Connection cnx;

  public PostService() {
        cnx = Myconnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Posts t) {
        
        String sql ="insert into posts(post_title,post_content,post_date) values (?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getPost_title());
            ste.setString(2, t.getPost_content());
              ste.setString(3, t.getPost_date());
            ste.executeUpdate();
            System.out.println("Personne Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
         
        }
        }
        
        
        
       

    @Override
    public List<Posts> afficher() {
 
        List<Posts> posts = new ArrayList<>();
         String sql="select * from posts";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Posts p = new Posts( rs.getString("post_title"), rs.getString("post_content"));
                posts.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return posts;
    
    }

    @Override
    public void supprimer(int id) {
      
    try
    { 
      Statement st = cnx.createStatement();
      String req = "DELETE FROM posts WHERE id = "+id+"";
                st.executeUpdate(req);      
      System.out.println("post supprimer avec succès...");
    } catch (SQLException ex) {
                System.out.println(ex.getMessage());        
              }
    }
    
    
    
    
    
    
    
    
        public ObservableList<Posts> getall() {
        ObservableList<Posts> posts = FXCollections.observableArrayList();
        try {
            String req = "select * from posts";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
           Posts p = new Posts(rs.getInt("id"), rs.getString("post_title"), rs.getString("post_content"), rs.getString("post_date"), "/images/Screenshot 2023-05-01 215512.png");
                posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
        }
        
        
        
        
            public void modifier(Posts t) {
        try {
            String req = "update posts set post_title=? ,post_content=? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getPost_title());
            ps.setString(2, t.getPost_content());
    
            ps.setInt(3, (int) t.getId());
       
            ps.executeUpdate();
            System.out.println("Post modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
            
    public ObservableList<Posts> chercherVoyage(String chaine){
          String sql="SELECT * FROM posts WHERE (Post_title LIKE ? or post_content LIKE ? )";

            String ch=""+chaine+"%";
         System.out.println(sql);
            ObservableList<Posts> myList= FXCollections.observableArrayList();
        try {
           
            Statement ste= cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =cnx.prepareStatement(sql);  
            stee.setString(1, ch);
            stee.setString(2, ch);
      
   
         System.out.println(stee);

            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                
                
                Posts v = new Posts ();
                
                v.setPost_title(rs.getString(3));
                v.setPost_content(rs.getString(4));
                v.setPost_date(rs.getString(5));
             
                v.setPost_image(rs.getString(6));
   
                myList.add(v);
                System.out.println("titre trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
    
    public Pane createPostUI(Posts post) {
    // Create UI components for the post (e.g., ImageView, Labels, etc.)
    Image image = new Image("/images/In_the_Name_of_Love.jpeg");
ImageView imageView = new ImageView(image);
System.out.println("Image found: " + (image.isError() ? "No" : "Yes"));
  ImageView postImageView = new ImageView(new Image(post.getPost_image()));
    Label postTitleLabel = new Label(post.getPost_title());
    Label postContentLabel = new Label(post.getPost_content());
   postContentLabel.setTextFill(Color.color(1,1,1));
 postTitleLabel.setTextFill(Color.color(1,1,1));
    // Create a container to hold the UI components for this post
    VBox postContainer = new VBox( postImageView ,postTitleLabel, postContentLabel);
    postContainer.getStyleClass().add("post-container"); // Optional: Add a CSS class for styling

    return postContainer;
}
            
            
     
            
            
            
            
  
}


    

