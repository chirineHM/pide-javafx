/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author amine
 */
public class Commande {

	  @Override
	public String toString() {
		return "Commande [id=" + id + ", panierId=" + panierId + ", dateCommande=" + dateCommande
				+ ", adresseLivraison=" + adresseLivraison + ", prixTotal=" + prixTotal + ", status=" + status + "]";
	}

	private int id;
	  private int panierId;
	  private LocalDateTime dateCommande;
	  private String adresseLivraison;
	  private double prixTotal;
	  private String status;

	  public Commande(int id, int panierId, LocalDateTime dateCommande, String adresseLivraison, double prixTotal, String status) {
	    this.id = id;
	    this.panierId = panierId;
	    this.dateCommande = dateCommande;
	    this.adresseLivraison = adresseLivraison;
	    this.prixTotal = prixTotal;
	    this.status = status;
	  }
	  public Commande(  int panierId, LocalDateTime dateCommande, String adresseLivraison, double prixTotal, String status) {
		     
		    this.panierId = panierId;
		    this.dateCommande = dateCommande;
		    this.adresseLivraison = adresseLivraison;
		    this.prixTotal = prixTotal;
		    this.status = status;
		  }

	  public Commande() {
 	}
	public int getId() {
	    return id;
	  }

	  public void setId(int id) {
	    this.id = id;
	  }

	  public int getPanierId() {
	    return panierId;
	  }

	  public void setPanierId(int panierId) {
	    this.panierId = panierId;
	  }

	  public LocalDateTime getDateCommande() {
	    return dateCommande;
	  }

	  public void setDateCommande(LocalDateTime dateCommande) {
	    this.dateCommande = dateCommande;
	  }

	  public String getAdresseLivraison() {
	    return adresseLivraison;
	  }

	  public void setAdresseLivraison(String adresseLivraison) {
	    this.adresseLivraison = adresseLivraison;
	  }

	  public double getPrixTotal() {
	    return prixTotal;
	  }

	  public void setPrixTotal(double prixTotal) {
	    this.prixTotal = prixTotal;
	  }

	  public String getStatus() {
	    return status;
	  }

	  public void setStatus(String status) {
	    this.status = status;
	  }
	}

 
