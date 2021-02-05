package entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Categorie
 *
 */
@Entity

public class Categorie implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_cat;
	private String libelle;
	@OneToMany(mappedBy="categorie")
	Collection<Oeuvre> oeuvres;
	
	private static final long serialVersionUID = 1L;

	public Categorie() {
		super();
	}   
	public int getId_cat() {
		return this.id_cat;
	}

	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}   
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	@Override
	public String toString() {
		return "Categorie [id_cat=" + id_cat + ", libelle=" + libelle + "]";
	}
   
}
