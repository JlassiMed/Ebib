package entity;

import javax.persistence.Entity;

@Entity
public class Etudiant extends Adherent {
	private String Filiere;
	private String Annee_inscrit;
	private static final long serialVersionUID = 1L;

	public Etudiant() {
		super();
	}   
	public String getFiliere() {
		return this.Filiere;
	}

	public void setFiliere(String Filiere) {
		this.Filiere = Filiere;
	}   
	public String getAnnee_inscrit() {
		return this.Annee_inscrit;
	}

	public void setAnnee_inscrit(String Annee_inscrit) {
		this.Annee_inscrit = Annee_inscrit;
	}
}
