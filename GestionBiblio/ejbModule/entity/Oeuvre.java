package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Oeuvre
 *
 */
@Entity

public class Oeuvre implements Serializable {

	   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_Oeuv;
	@Column(unique=true)
	private String Titre;
	private int NB_support;

	@ManyToOne
	@JoinColumn(name="id_aut")
	private Auteur auteur; 

	@ManyToOne
	@JoinColumn(name="id_cat")
	private Categorie categorie; 
	
	 
	
	@Transient
	@OneToMany(mappedBy="oeuvre")
	Collection<Livre> livres;
	@Transient
	@OneToMany(mappedBy="oeuvre")
	Collection<Reservation> oeuv_reservation;
	

	public OeuvJson getJsonOeuvFromOeuvre()
	{
		OeuvJson oj= new OeuvJson();
		oj.setCategorie(this.getCategorie().getLibelle());
		oj.setAuteur(this.getAuteur().getPrenom_aut()+" "+this.getAuteur().getNom_aut());
		oj.setId_Oeuv(this.getId_Oeuv());
		oj.setNB_support(this.getNB_support());
		oj.setTitre(this.getTitre());
		oj.setId_liv_dispo(-1);
		return oj;
	}
	
	public Auteur getAuteur() {
		return auteur;
	}
	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Collection<Livre> getLivres() {
		return livres;
	}
	public void setLivres(Collection<Livre> livres) {
		this.livres = livres;
	}
	public Collection<Reservation> getOeuv_reservation() {
		return oeuv_reservation;
	}
	public void setOeuv_reservation(Collection<Reservation> oeuv_reservation) {
		this.oeuv_reservation = oeuv_reservation;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public int getNB_support() {
		return NB_support;
	}
	public void setNB_support(int nB_support) {
		NB_support = nB_support;
	}
	public Oeuvre() {
		super();
	}   
	public int getId_Oeuv() {
		return this.id_Oeuv;
	}

	public void setId_Oeuv(int id_oeuv) {
		this.id_Oeuv = id_oeuv;
	}

	@Override
	public String toString() {
		return "Oeuvre [id_Oeuv=" + id_Oeuv + ", Titre=" + Titre + ", NB_support=" + NB_support + ", auteur=" + auteur
				+ ", categorie=" + categorie + "]";
	}
   
}
