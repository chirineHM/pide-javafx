package entities;

public class Categorie {
	private int id ;
	private String nom_c,description_c ;
public Categorie(int id, String nom_c, String description_c) {
		super();
		this.id = id;
		this.nom_c = nom_c;
		this.description_c = description_c;
	}
@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom_c=" + nom_c + ", description_c=" + description_c + "]";
	}
public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom_c() {
		return nom_c;
	}
	public void setNom_c(String nom_c) {
		this.nom_c = nom_c;
	}
	public String getDescription_c() {
		return description_c;
	}
	public void setDescription_c(String description_c) {
		this.description_c = description_c;
	}

}
