/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.DatePicker;

/**
 *
 * @author hmida
 */
public class Posts {
     private static Posts instance;
     private int id;
     private String post_title , post_image, post_content,post_date; 

    public Posts() {
    }

    public Posts( String post_title, String post_image, String post_content, String post_date) {
        this.post_title = post_title;
        this.post_image = post_image;
        this.post_content = post_content;
        this.post_date = post_date;
    }

    public Posts(int id, String post_title, String post_content, String post_date ,String post_image) {
        this.id = id;
        this.post_title = post_title;
        this.post_image = post_image;
        this.post_content = post_content;
        this.post_date = post_date;
    }

    public Posts(String post_title, String post_content) {
        this.post_title = post_title;
        this.post_content = post_content;
    }

    public Posts(int id, String post_title, String post_content, String post_date) {
        this.id = id;
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_date = post_date;
    }
    

    public Posts(String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    

    
    
    
    
    
    
    
    
     @Override
    public String toString() {
        return "post{" + "id=" + id + ", titre=" + post_title + ", content=" + post_content +", date=" + post_date  + "date=" + post_image +"}";
    }

    
    
     public static Posts getInstance() {
        if (instance == null) {
            instance = new Posts();
        }
        return instance;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }


            
}
