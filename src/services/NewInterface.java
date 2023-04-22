/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.typeSub;
import java.util.List;

/**
 *
 * @author Fayechi
 */
public interface NewInterface<T> {
    public void ajouter(T t);
    
    public List<T> afficher();

  public void supprimer(int t);
public void modifier(T t);
 // public typeSub displayById(int id) ;
     // public typeSub getOneByName(String name) ;

    
}
