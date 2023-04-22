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
        
        String sql ="insert into posts(post_title,post_content) values (?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getComment_content());
          
      
            ste.executeUpdate();
            System.out.println("Personne Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
         
        }
        }

 

    @Override
    public List<Comment> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        

}
