/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.entities;

/**
 *
 * @author medfaroukbenbelgacem
 */
import java.sql.Date;

public class Evenements {
    // Champ pour l'identifiant de l'événement
    private int id;
    // Champ pour le nombre de participants à l'événement
    private int nb_part;
    // Champ pour le nom de l'événement
    private String nom_even;
    // Champ pour la description de l'événement
    private String desc_event;
    // Champ pour la catégorie de l'événement
    private String categorie_even;
    // Champ pour le nom du fichier image de l'événement
    private String image_even;
    // Champ pour la date de l'événement
    private Date date;
    
    // Constructeur par défaut de la classe Evenements
//    public Evenements() {
//        // Initialisation des champs
//        id = 0;
//        nb_part = 0;
//        nom_even = "";
//        desc_event = "";
//        categorie_even = "";
//        image_even = "";
//        date = 
//    }

    public Evenements(int id, int nb_part, String nom_even, String desc_event, String categorie_even, String image_even, Date date) {
        this.id = id;
        this.nb_part = nb_part;
        this.nom_even = nom_even;
        this.desc_event = desc_event;
        this.categorie_even = categorie_even;
        this.image_even = image_even;
        this.date = date;
    }

    public Evenements(int nb_part ,String nom_even, String desc_event, String categorie_even, String image_even, Date date) {
        this.nb_part = nb_part;
        this.nom_even = nom_even;
        this.desc_event = desc_event;
        this.categorie_even = categorie_even;
        this.image_even = image_even;
        this.date = date;
    }
    
    // Getters et setters pour chaque champ
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb_part() {
        return nb_part;
    }

    public void setNb_part(int nb_part) {
        this.nb_part = nb_part;
    }

    public String getNom_even() {
        return nom_even;
    }

    public void setNom_even(String nom_even) {
        this.nom_even = nom_even;
    }

    public String getDesc_event() {
        return desc_event;
    }

    public void setDesc_event(String desc_event) {
        this.desc_event = desc_event;
    }

    public String getCategorie_even() {
        return categorie_even;
    }

    public void setCategorie_even(String categorie_even) {
        this.categorie_even = categorie_even;
    }

    public String getImage_even() {
        return image_even;
    }

    public void setImage_even(String image_even) {
        this.image_even = image_even;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Evenements{" + "id=" + id + ", nb_part=" + nb_part + ", nom_even=" + nom_even + ", desc_event=" + desc_event + ", categorie_even=" + categorie_even + ", image_even=" + image_even + ", date=" + date + '}';
    }
    
}
