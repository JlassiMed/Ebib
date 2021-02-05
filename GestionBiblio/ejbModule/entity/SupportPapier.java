package entity;

import entity.Oeuvre;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: SupportPapier
 *
 */
@Entity

public class SupportPapier extends Oeuvre implements Serializable {

	private int Nb_dispo;
	private static final long serialVersionUID = 1L;

	public SupportPapier() {
		super();
	}
	public int getNb_dispo() {
		return this.Nb_dispo;
	}

	public void setNb_dispo(int Nb_dispo) {
		this.Nb_dispo = Nb_dispo;
	}
	
}
