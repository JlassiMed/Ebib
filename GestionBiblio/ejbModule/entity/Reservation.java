package entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Reservation
 *
 */
@Entity
@IdClass(ResPK.class)
public class Reservation implements Serializable {

	@Id
	private int id_Oeuv;
	@Id
	private int id_Adh;
	
	private Date Date_Res;
	private Date Date_Anul;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="id_Adh",insertable = false,updatable=false)
	private Adherent adherent;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="id_Oeuv",insertable = false,updatable=false)
	private Oeuvre oeuvre;
	
	public Reservation() {
		super();
	}   
	public Date getDate_Res() {
		return this.Date_Res;
	}

	public void setDate_Res(Date Date_Res) {
		this.Date_Res = Date_Res;
	}   
	public Date getDate_Anul() {
		return this.Date_Anul;
	}

	public void setDate_Anul(Date Date_Anul) {
		this.Date_Anul = Date_Anul;
	}
	public int getId_Oeuv() {
		return id_Oeuv;
	}
	public void setId_Oeuv(int id_Oeuv) {
		this.id_Oeuv = id_Oeuv;
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
	public Oeuvre getOeuvre() {
		return oeuvre;
	}
	public void setOeuvre(Oeuvre oeuvre) {
		this.oeuvre = oeuvre;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
