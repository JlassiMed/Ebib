package entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Adherent
 *
 */
@Entity

public class Adherent implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_Adh;
	@Column(unique=true)
	private String Login;
	@Column(unique=true)
	private String cin;
	
	private String password;
	private String nom;
	private String prenom;
	private String adresse;
	@Column(unique=true)
	private String Email;
	private String telephone;
	private int nb_empr_enc;
	private Boolean blacklisted;
    @Transient  
	@OneToMany(mappedBy="adherent")
	private Collection<Emprunt> adherents;
    @Transient  
	@OneToMany(mappedBy="adherent")
	private Collection<Reservation> adh_reservation ;
	
	private static final long serialVersionUID = 1L;

	
	public Adherent() {
		super();
	}   
	
	public int getId_Adh() {
		return id_Adh;
	}

	public void setId_Adh(int id_Adh) {
		this.id_Adh = id_Adh;
	}
	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public Collection<Emprunt> getAdherents() {
		return adherents;
	}

	public void setAdherents(Collection<Emprunt> adherents) {
		this.adherents = adherents;
	}

	public String getLogin() {
		return this.Login;
	}

	public void setLogin(String Login) {
		this.Login = Login;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}   
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}   
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}   
	public String getEmail() {
		return this.Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}   
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getNb_empr_enc() {
		return nb_empr_enc;
	}
	public void setNb_empr_enc(int nb_empr_enc) {
		this.nb_empr_enc = nb_empr_enc;
	}
	public Boolean getBlacklisted() {
		return blacklisted;
	}
	public void setBlacklisted(Boolean blacklisted) {
		this.blacklisted = blacklisted;
	}

	@Override
	public String toString() {
		return "Adherent [id_Adh=" + id_Adh + ", Login=" + Login + ", cin=" + cin + ", password=" + password + ", nom="
				+ nom + ", prenom=" + prenom + ", adresse=" + adresse + ", Email=" + Email + ", telephone=" + telephone
				+ ", nb_empr_enc=" + nb_empr_enc + ", blacklisted=" + blacklisted + "]";
	}
   
}
