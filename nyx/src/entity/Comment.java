/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author hmida
 */
public class Comment {
   
    int comment_id , post_id; 


    String comment_content, comment_date;
     private static Comment instance;

    public Comment() {
    }

    public Comment(int comment_id, int post_id, String comment_content, String comment_date) {
        this.comment_id = comment_id;
        this.post_id = post_id;
        this.comment_content = comment_content;
        this.comment_date = comment_date;
    }

    public Comment(int comment_id, String comment_content) {
        this.comment_id = comment_id;
        this.comment_content = comment_content;
    }

    
    
    
     @Override
    public String toString() {
        return "comment{" + "id=" + comment_id + ", titre=" + comment_content +"}";
    }
    
    
    
    
 public static Comment getInstance() {
        if (instance == null) {
            instance = new Comment();
        }
        return instance;
 }

    public Comment(int aInt, String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getComment_id() {
        return comment_id;
    }
    public int getPost_id() {
        return post_id;
    }

   
    public String getComment_content() {
        return comment_content;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }
     public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
}
