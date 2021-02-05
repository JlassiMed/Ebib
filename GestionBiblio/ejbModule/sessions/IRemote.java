package sessions;
import java.sql.Date;
import java.util.List;

import javax.ejb.Remote;

import entity.*;
@Remote
public interface IRemote {
	
	public void addSuppPapier(SupportPapier o,int code_auteur,int Code_categorie,List<Livre> livres);
	public void updateSuppPapier(SupportPapier o);
	
	public void addSuppMultimedia(SupportMultimedia o,int code_auteur,int Code_categorie,List<Livre> livres);
	public void updateSuppMultimedia(SupportMultimedia o);
	public int getstring();
	public void deleteOeuvre(int id);
	public Oeuvre getOeuvre(int id_Oeuv);
	public boolean addEmprunt (Emprunt e);
	public void addEmprunt (int id_Liv,int id_adh,Date Date_empr,Date Date_Ret_Theo);
	public Adherent getAdherentParLogin(String Login);
	public Adherent getAdherentParEmail(String email);
	public void addEnseigniant (Enseignant e);
	public void updateEnseignant(Enseignant e);
	public void deleteEnseignant(int id);
	
	public void addEtudiant (Etudiant e);
	public void updateEtudiant(Etudiant e);
	public void deleteEtudiant(int id);

	
	public List<Oeuvre> OeuvresParMotsCle(String mc);
	
	public List<SupportMultimedia> Liste_SuppMulti();
	public List<SupportPapier> Liste_SuppPapier();
	public List<Auteur> Liste_Auteurs();
	public List<Oeuvre> Liste_Oeuvres();
	public List<Adherent> Liste_Adherents();
public List<Enseignant> Liste_Enseignants();
	
	public List<Etudiant> Liste_Etudiants();
	public void PasserPanier(Panier P);
	
	public Auteur getAuteur(int id);
	public void addAuteur (Auteur a);
	public void updateAuteur(Auteur a);
	public void deleteAuteur(int id);
	
	public void addLivre (Livre l,int Code_oeuvre);
	public void updateLivre(Livre l);
	public void deleteLivre(int id);
	public Livre getLivre(int id);
	public void addReservation (Reservation r,int id_oeuv,int id_adh);
	public void updateReservation(Reservation r);
	public void deleteReservation(int id);
	
	
	
	public List<Emprunt> Liste_Emprunts();
	public List<Emprunt> Liste_Emprunts_parAdh(Adherent a);
	
	public List<Livre> Liste_Livres(); 
	public List<Livre> liste_livres_parOeuvre(Oeuvre o);
	public List<Livre> liste_livresDispo_parOeuvre(Oeuvre o);
	public List<OeuvJson> Liste_OeuvreJson();
	
	
	
	public List<Categorie> Liste_Categories();
	public boolean isBlackListed(int id);
	
	public void addCategorie (Categorie c);
	public void updateCategorie(Categorie c);
	public void deleteCategorie(int id);
	
	
	
	
	public List<Reservation> Liste_Reservations();
	void addSuppPapier(SupportPapier o, int code_auteur, int Code_categorie);
	void addSuppMultimedia(SupportMultimedia o, int code_auteur, int Code_categorie);
	public boolean veriflogin(String login,String pass);
}
