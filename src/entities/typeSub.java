/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Fayechi
 */
public class typeSub {

    private int id;
    private String name, description, etat;

    public typeSub(int id, String name, String description, String etat) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.etat = etat;
    }

    public typeSub(String name, String description, String etat) {
        this.name = name;
        this.description = description;
        this.etat = etat;
    }

    public typeSub() {
    }
    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEtat() {
        return etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "typeSub{" + "id=" + id + ", name=" + name + ", description=" + description + ", etat=" + etat + '}';
    }

}
