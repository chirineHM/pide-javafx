/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author amine
 */
import java.time.LocalDate;

public class Panier {

  private int id;
  private int quantite;
  private LocalDate dateAjout;
  private ArrayList<Produit> produits;

  public Panier() {
      produits = new ArrayList<>();
  }

  public void ajouterProduit(Produit produit) {
      produits.add(produit);
  }

  public void supprimerProduit(Produit produit) {
      produits.remove(produit);
  }

  public ArrayList<Produit> getProduits() {
      return produits;
  }

  public Panier(int id, int quantite, LocalDate dateAjout) {
    this.id = id;
    this.quantite = quantite;
    this.dateAjout = dateAjout;
  }
  public Panier(  int quantite, LocalDate dateAjout) {
	    
	    this.quantite = quantite;
	    this.dateAjout = dateAjout;
	  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getQuantite() {
    return quantite;
  }

  public void setQuantite(int quantite) {
    this.quantite = quantite;
  }

  public LocalDate getDateAjout() {
    return dateAjout;
  }

  public void setDateAjout(LocalDate dateAjout) {
    this.dateAjout = dateAjout;
  }

@Override
public String toString() {
	return "Panier [id=" + id + ", quantite=" + quantite + ", dateAjout=" + dateAjout + "]";
}
}
