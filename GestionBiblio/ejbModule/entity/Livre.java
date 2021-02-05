package entity;

import java.io.Serializable;
import java.util.Collection;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Livre
 *
 */
@Entity

public class Livre implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Date date_edition;
	private Boolean Dispo;
	@ManyToOne
	@JoinColumn(name="id_Oeuv")
	private Oeuvre oeuvre;
	@Transient
	@OneToMany(mappedBy="livre")
	private Collection<Emprunt> livres;
	
	
	
	
	public Oeuvre getOeuvre() {
		return oeuvre;
	}

	public void setOeuvre(Oeuvre oeuvre) {
		this.oeuvre = oeuvre;
	}

	public Collection<Emprunt> getLivres() {
		return livres;
	}

	public void setLivres(Collection<Emprunt> livres) {
		this.livres = livres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate_edition() {
		return date_edition;
	}

	public void setDate_edition(Date date_edition) {
		this.date_edition = date_edition;
	}

	public Boolean getDispo() {
		return Dispo;
	}

	public void setDispo(Boolean dispo) {
		Dispo = dispo;
	}

	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

	public Livre() {
		super();
	}

	public Livre(int id, Date date_edition, Boolean dispo) {
		super();
		this.id = id;
		this.date_edition = date_edition;
		Dispo = dispo;
	}
   
}
