package entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Emprunt
 *
 */
@Entity
@IdClass(EmprPK.class)
public class Emprunt implements Serializable {
	@Id
	private int id_Liv;
	@Id
	private int id_Adh;
	
	private Date Date_empr;
	private Date Date_Ret_Theo;
	private Date Date_Ret_efc;
	private int Nbr_avert;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="id_Adh",insertable = false,updatable=false)
	private Adherent adherent;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="id_Liv",insertable = false,updatable=false)
	private Livre livre;
	
	private static final long serialVersionUID = 1L;

	public Emprunt() {
		super();
	}   
	public Date getDate_empr() {
		return this.Date_empr;
	}

	public void setDate_empr(Date Date_empr) {
		this.Date_empr = Date_empr;
	}   
	public Date getDate_Ret_Theo() {
		return this.Date_Ret_Theo;
	}

	public void setDate_Ret_Theo(Date Date_Ret_Theo) {
		this.Date_Ret_Theo = Date_Ret_Theo;
	}   
	public Date getDate_Ret_efc() {
		return this.Date_Ret_efc;
	}

	public void setDate_Ret_efc(Date Date_Ret_efc) {
		this.Date_Ret_efc = Date_Ret_efc;
	}   
	public int getNbr_avert() {
		return this.Nbr_avert;
	}

	public void setNbr_avert(int Nbr_avert) {
		this.Nbr_avert = Nbr_avert;
	}   
	
	public int getId_Liv() {
		return id_Liv;
	}
	public void setId_Liv(int id_Liv) {
		this.id_Liv = id_Liv;
	}
	public int getId_Adh() {
		return id_Adh;
	}
	public void setId_Adh(int id_Adh) {
		this.id_Adh = id_Adh;
	}
	public Adherent getAdherent() {
		return adherent;
	}
	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}
	public Livre getLivre() {
		return livre;
	}
	public void setLivre(Livre livre) {
		this.livre = livre;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Emprunt(int id_Liv, int id_Adh, Date date_empr, Date date_Ret_Theo, Date date_Ret_efc, int nbr_avert) {
		super();
		this.id_Liv = id_Liv;
		this.id_Adh = id_Adh;
		Date_empr = date_empr;
		Date_Ret_Theo = date_Ret_Theo;
		Date_Ret_efc = date_Ret_efc;
		Nbr_avert = nbr_avert;
	}
   
}
