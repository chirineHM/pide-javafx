/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author amine
 */public class Produit {

	  private int id;
	  private int categorieId;
	  private String nom;
	  private String description;
	  private double prix;
	  private int quantite;
	  private String image;
	  private Integer quantiteDansPanier;
	  private String image_code_qr ; 
	  
	  
	  public Produit(int categorieId, String nom, String description, double prix, int quantite, String image,
			String image_code_qr) {
		super();
		this.categorieId = categorieId;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.image = image;
		this.image_code_qr = image_code_qr;
	}

	public String getImage_code_qr() {
		return image_code_qr;
	}

	public void setImage_code_qr(String image_code_qr) {
		this.image_code_qr = image_code_qr;
	}

	public Produit(int id, int categorieId, String nom, String description, double prix, int quantite, String image, Integer quantiteDansPanier) {
	    this.id = id;
	    this.categorieId = categorieId;
	    this.nom = nom;
	    this.description = description;
	    this.prix = prix;
	    this.quantite = quantite;
	    this.image = image;
	    this.quantiteDansPanier = quantiteDansPanier;
	  }

	  public int getId() {
	    return id;
	  }

	  public void setId(int id) {
	    this.id = id;
	  }

	  public int getCategorieId() {
	    return categorieId;
	  }

	  public void setCategorieId(int categorieId) {
	    this.categorieId = categorieId;
	  }

	  public String getNom() {
	    return nom;
	  }

	  public void setNom(String nom) {
	    this.nom = nom;
	  }

	  public String getDescription() {
	    return description;
	  }

	  public void setDescription(String description) {
	    this.description = description;
	  }

	  public double getPrix() {
	    return prix;
	  }

	  public void setPrix(double prix) {
	    this.prix = prix;
	  }

	  public int getQuantite() {
	    return quantite;
	  }

	  public void setQuantite(int quantite) {
	    this.quantite = quantite;
	  }

	  public String getImage() {
	    return image;
	  }

	  public void setImage(String image) {
	    this.image = image;
	  }

	  public Integer getQuantiteDansPanier() {
	    return quantiteDansPanier;
	  }

	  public void setQuantiteDansPanier(Integer quantiteDansPanier) {
	    this.quantiteDansPanier = quantiteDansPanier;
	  }

	@Override
	public String toString() {
		return "Produit [id=" + id + ", categorieId=" + categorieId + ", nom=" + nom + ", description=" + description
				+ ", prix=" + prix + ", quantite=" + quantite + ", image=" + image + ", quantiteDansPanier="
				+ quantiteDansPanier + "]";
	}
	}
