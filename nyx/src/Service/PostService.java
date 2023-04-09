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
        
        String sql ="insert into posts(post_title,post_content) values (?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getPost_title());
            ste.setString(2, t.getPost_content());
      
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
                Posts p = new Posts(rs.getInt("id"), rs.getString("post_title"), rs.getString("post_content"),rs.getString("post_date"));
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
                Posts p = new Posts(rs.getInt("id"), rs.getString("post_title"), rs.getString("post_content"),rs.getString("post_date"));
                posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
        }
}
    

