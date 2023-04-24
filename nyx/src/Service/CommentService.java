/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import entity.Comment;
import entity.Posts;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.Myconnection;

/**
 *
 * @author hmida
 */
public class CommentService implements NewInterface <Comment> {
    

  Connection cnx;

  public CommentService() {
        cnx = Myconnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Comment t) {
        
        String sql ="insert into posts(commet_content,comment_date) values (?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getComment_content());
          
             ste.setString(2, t.getComment_date());
            ste.executeUpdate();
            System.out.println("comment added ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
         
        }
        }
    
     public ObservableList<Comment> getall() {
        ObservableList<Comment> comments = FXCollections.observableArrayList();
        try {
            String req = "select * from comments";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
                Comment p = new Comment(rs.getInt("comment_id"), rs.getString("comment_content"));
                comments.add(p);
            }
            System.out.print(comments);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return comments;
        }

  
  

    @Override
    public void supprimer(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public List<Comment> afficherr() {
          List<Comment> comments = new ArrayList<>();
        try {
            String req = "select * from comments" ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Comment p = new Comment();
                p.setComment_id(rs.getInt(1));
                p.setComment_content(rs.getString("comment_content"));
     
     
               comments.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return comments;
    }

    @Override
    public List<Comment> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
        

}
