package entity;

import javax.persistence.Entity;

@Entity
public class SupportMultimedia extends Oeuvre {
	private int Nb_dispo;
	private static final long serialVersionUID = 1L;

	public SupportMultimedia() {
		super();
	}   
	public int getNb_dispo() {
		return this.Nb_dispo;
	}

	public void setNb_dispo(int Nb_dispo) {
		this.Nb_dispo = Nb_dispo;
	}
	
	
}
