package entity;

import javax.persistence.Entity;

@Entity
public class Enseignant extends Adherent {
	private String Grade;
	private String Departement;
	private static final long serialVersionUID = 1L;

	public Enseignant() {
		super();
	}   
	
	public String getGrade() {
		return this.Grade;
	}

	public void setGrade(String Grade) {
		this.Grade = Grade;
	}   
	public String getDepartement() {
		return this.Departement;
	}

	public void setDepartement(String Departement) {
		this.Departement = Departement;
	}
}
