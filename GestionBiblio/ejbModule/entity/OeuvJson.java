package entity;

import java.io.Serializable;


public class OeuvJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_Oeuv;
	private String Titre;
	private int NB_support;
	private String Categorie;
	private String Auteur;
	
	private  int id_liv_dispo;
	
	public int getId_Oeuv() {
		return id_Oeuv;
	}
	public void setId_Oeuv(int id_Oeuv) {
		this.id_Oeuv = id_Oeuv;
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
	public String getCategorie() {
		return Categorie;
	}
	public void setCategorie(String categorie) {
		Categorie = categorie;
	}
	public String getAuteur() {
		return Auteur;
	}
	public void setAuteur(String auteur) {
		Auteur = auteur;
	}
	public OeuvJson() {
		super();
	}
	
	@Override
	public String toString() {
		return "OeuvJson [id_Oeuv=" + id_Oeuv + ", Titre=" + Titre + ", NB_support=" + NB_support + ", Categorie="
				+ Categorie + ", Auteur=" + Auteur+ "]";
	}
	public int getId_liv_dispo() {
		return id_liv_dispo;
	}
	public void setId_liv_dispo(int id_liv_dispo) {
		this.id_liv_dispo = id_liv_dispo;
	}
	

	}
	
	

