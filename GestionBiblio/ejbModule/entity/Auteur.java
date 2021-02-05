package entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Auteur
 *
 */
@Entity
@Table(name="Auteur")
public class Auteur implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_aut;
	private String nom_aut;
	private String prenom_aut;
	@OneToMany(mappedBy="auteur")
	private Collection<Oeuvre> oeuvres;
	private static final long serialVersionUID = 1L;

	public Auteur() {
		super();
	}   
	
	public Auteur(int id_aut, String nom_aut, String prenom_aut) {
		super();
		this.id_aut = id_aut;
		this.nom_aut = nom_aut;
		this.prenom_aut = prenom_aut;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId_aut() {
		return this.id_aut;
	}

	public void setId_aut(int id_aut) {
		this.id_aut = id_aut;
	}   
	public String getNom_aut() {
		return this.nom_aut;
	}

	public void setNom_aut(String nom_aut) {
		this.nom_aut = nom_aut;
	}   
	public String getPrenom_aut() {
		return this.prenom_aut;
	}

	public void setPrenom_aut(String prenom_aut) {
		this.prenom_aut = prenom_aut;
	}

	@Override
	public String toString() {
		return "Auteur [id_aut=" + id_aut + ", nom_aut=" + nom_aut + ", prenom_aut=" + prenom_aut + "]";
	}
   
}
