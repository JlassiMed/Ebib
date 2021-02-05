package sessions;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.Adherent;
import entity.Auteur;
import entity.Categorie;
import entity.EmprPK;
import entity.Emprunt;
import entity.Enseignant;
import entity.Etudiant;
import entity.Livre;
import entity.OeuvJson;
import entity.Oeuvre;
import entity.Panier;
import entity.Reservation;
import entity.SupportMultimedia;
import entity.SupportPapier;

@Stateless
@Remote(IRemote.class)
@Local(ILocal.class)
public class EJBImpl implements ILocal, IRemote {

	@PersistenceContext
	private EntityManager em ;
	

	@Override
	public void deleteOeuvre(int id) {
		em.remove(em.find(Oeuvre.class, id));
	}

	@Override
	public List<Oeuvre> OeuvresParMotsCle(String mc) {
		
		/*Query q=em.createQuery("select o from Oeuvre o, Auteur a, Categorie c where o.id_aut=a.id_aut and o.id_cat=a.id_cat"
				+ "where o.Titre=:mc1 or a.nom_aut=:mc2 or a.prenom_aut=:mc3 or c.libelle=:mc4  or concat(a.nom_aut,' ',a.prenom_aut)=:mc5");
		q.setParameter("mc1", mc);
		q.setParameter("mc2", mc);
		q.setParameter("mc3", mc);
		q.setParameter("mc4", mc);
		q.setParameter("mc5", mc);
		return q.getResultList();
		*/
		return Liste_Oeuvres().stream().filter(x->x.getAuteur().getNom_aut().equals(mc)||x.getAuteur().getPrenom_aut().equals(mc)
				||x.getCategorie().getLibelle().equals(mc)||String.valueOf(x.getId_Oeuv()).equals(mc)
				||(x.getAuteur().getNom_aut()+" "+x.getAuteur().getPrenom_aut()).equals(mc)
				||x.getTitre().equals(mc)||(x.getAuteur().getPrenom_aut()+" "+x.getAuteur().getNom_aut()).equals(mc)).collect(Collectors.toList());
	}
	@Override
	public List<SupportMultimedia> Liste_SuppMulti() {
		try
		{
		Query q=em.createQuery("select s from SupportMultimedia s ");
		return q.getResultList();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage()+"liste vide support multimedia");
		}
		return null;
	}

	@Override
	public List<SupportPapier> Liste_SuppPapier() {
		try
		{
		Query q=em.createQuery("select s from SupportPapier s ");
		return q.getResultList();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage()+"liste vide support papier");
		}
		return null;
	}

	@Override
	public List<Oeuvre> Liste_Oeuvres() {
		List<Oeuvre> lc= new ArrayList<>();
		lc.addAll(Liste_SuppMulti());
		lc.addAll(Liste_SuppPapier());
		return lc;
	}

	@Override
	public void addEnseigniant(Enseignant e) {
		em.persist(e);
	}

	@Override
	public void updateEnseignant(Enseignant e) {
		em.merge(e);

	}

	@Override
	public void deleteEnseignant(int id) {
		Enseignant e=em.find(Enseignant.class, id);
		em.remove(e);

	}

	@Override
	public void addEtudiant(Etudiant e) {
		em.persist(e);

	}

	@Override
	public void updateEtudiant(Etudiant e) {
		em.merge(e);

	}

	@Override
	public void deleteEtudiant(int id) {
		Etudiant e=em.find(Etudiant.class, id);
		em.remove(e);
	}

	@Override
	public void addAuteur(Auteur a) {
		em.persist(a);

	}

	@Override
	public void updateAuteur(Auteur a) {
		em.merge(a);

	}

	@Override
	public void deleteAuteur(int id) {
		Auteur a=em.find(Auteur.class, id);
		em.remove(a);
	}

	@Override
	public void addCategorie(Categorie c) {
		em.persist(c);

	}

	@Override
	public void updateCategorie(Categorie c) {
		em.merge(c);

	}

	@Override
	public void deleteCategorie(int id) {
		Categorie c=em.find(Categorie.class, id);
		em.remove(c);
	}

	@Override
	public boolean addEmprunt(Emprunt e) {
		Livre l=em.find(Livre.class, e.getId_Liv());
		Adherent a= em.find(Adherent.class, e.getId_Adh());
		e.setAdherent(a);
		e.setLivre(l);
		em.persist(e);
		
		Oeuvre o=getOeuvre(l.getId());
		o.setNB_support(o.getNB_support()-1);
		em.merge(o);
		l.setDispo(false);
		em.merge(l);
		
		a.setNb_empr_enc(a.getNb_empr_enc()+1);
		em.merge(a);
		EmprPK epk= new EmprPK();
		epk.setId_Adh(e.getId_Adh());
		epk.setId_Liv(e.getId_Liv());
		if(em.find(Emprunt.class,epk)==null)
			return false;
		else return true;
	}

	@Override
	public void updateEmprunt(Emprunt e) {
		em.merge(e);

	}

	@Override
	public void deleteEmprunt(int id) {
		Emprunt e=em.find(Emprunt.class, id);
		em.remove(e);
	}

	@Override
	public void retourEmprunt(Emprunt e,Date date_ret) {
		e.setDate_Ret_efc(date_ret);
		em.merge(e);
		
		Livre l=e.getLivre();
		l.setDispo(true);
		em.merge(l);
		
		// à vérifier !!!!!!!!
		if(l.getOeuvre() instanceof SupportMultimedia  )
		{
			SupportMultimedia s= (SupportMultimedia)l.getOeuvre();
			s.setNb_dispo(s.getNb_dispo()+1);
			em.merge(s);
		}
		else
		{
			SupportPapier s= (SupportPapier)l.getOeuvre();
			s.setNb_dispo(s.getNb_dispo()+1);
			em.merge(s);
		}
		
	}

	@Override
	public void addLivre(Livre l, int Code_oeuvre) {
		Oeuvre o= em.find(Oeuvre.class,Code_oeuvre );
		l.setOeuvre(o);
		em.persist(l);
	}

	@Override
	public void updateLivre(Livre l) {

		em.merge(l);
	}

	@Override
	public void deleteLivre(int id) {
		Livre l= em.find(Livre.class, id);
		em.remove(l);
	}

	

	
	

	@Override
	public void addReservation(Reservation r, int id_oeuv,int id_adh) {
		r.setId_Adh(id_adh);
		r.setId_Oeuv(id_oeuv);
		r.setAdherent(em.find(Adherent.class, id_adh));
		r.setOeuvre(em.find(Oeuvre.class, id_oeuv));
		em.persist(r);
	}

	@Override
	public void updateReservation(Reservation r) {
		em.merge(r);
	}

	@Override
	public void deleteReservation(int id) {
		em.remove(em.find(Reservation.class, id));
	}



	@Override
	public List<Emprunt> Liste_Emprunts() {
		try {
		Query q=em.createQuery("select e from Emprunt e");
		return q.getResultList();
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage()+"liste vide emprunts");
	}
	return null;
	}

	@Override
	public List<Emprunt> Liste_Emprunts_parAdh(Adherent a) {
		try {
		Query q=em.createQuery("select e from Emprunt e where e.id_Adh=?1");
		q.setParameter(1,a.getId_Adh());
		return q.getResultList();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage()+"liste vide emprunts");
		}
		return null;
				
	}

	@Override
	public List<Livre> Liste_Livres() {
		Query q=em.createQuery("select l from Livre l");
		return q.getResultList();
	}

	@Override
	public List<Livre> liste_livres_parOeuvre(Oeuvre o) {
		/* Query q=em.createQuery("select l from Livre l where l.id_Oeuv=:?1");
		q.setParameter(1,o.getId_Oeuv());
		return q.getResultList();*/
		return Liste_Livres().stream().filter(x->x.getOeuvre().getId_Oeuv()==o.getId_Oeuv()).collect(Collectors.toList());
	}

	@Override
	public List<Livre> liste_livresDispo_parOeuvre(Oeuvre o) {
	/*	Query q=em.createQuery("select l from Livre l where l.id_Oeuv=:?1 and l.Dispo=?2");
		q.setParameter(1,o.getId_Oeuv());
		q.setParameter(2,true);
		return q.getResultList();
	*/
		return Liste_Livres().stream().filter(x->x.getOeuvre().getId_Oeuv()==o.getId_Oeuv()&&x.getDispo()==true).collect(Collectors.toList());

	}

	@Override
	public List<Adherent> Liste_Adherents() {
		List<Adherent> lc= new ArrayList<>();
		lc.addAll(Liste_Enseignants());
		lc.addAll(Liste_Etudiants());
		
		return lc;
	}

	@Override
	public List<Auteur> Liste_Auteurs() {
		Query q=em.createQuery("SELECT a FROM Auteur a",Auteur.class);
		return q.getResultList();
	}

	@Override
	public List<Categorie> Liste_Categories() {
		Query q=em.createQuery("select c from Categorie c");
		return q.getResultList();
	}

	@Override
	public List<Enseignant> Liste_Enseignants() {
		Query q=em.createQuery("select a from Enseignant a ");
		return q.getResultList();
	}

	@Override
	public List<Etudiant> Liste_Etudiants() {
		Query q=em.createQuery("select a from Etudiant a");
		return q.getResultList();
	}
	

	@Override
	public List<Reservation> Liste_Reservations() {
		Query q=em.createQuery("select r from Reservation r");		
		return q.getResultList();
	}

	@Override
	public void PasserPanier(Panier P) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isBlackListed(int id) {
		Adherent aa= em.find(Adherent.class, id);
		if(aa.getBlacklisted()==true)
			return true;
		return false;
	}

	@Override 
	public void addSuppPapier(SupportPapier o, int code_auteur, int Code_categorie) {
		o.setAuteur(em.find(Auteur.class, code_auteur));
		o.setCategorie(em.find(Categorie.class, Code_categorie));
		em.persist(o);
	}

	@Override
	public void addSuppPapier(SupportPapier o, int code_auteur, int Code_categorie,List<Livre> livres) {
		o.setAuteur(em.find(Auteur.class, code_auteur));
		o.setCategorie(em.find(Categorie.class, Code_categorie));
		o.setLivres(livres);
		em.persist(o);

	}

	@Override
	public void updateSuppPapier(SupportPapier o) {
		em.merge(o);
		// XXXXXXX
	}
	@Override
	public void addSuppMultimedia(SupportMultimedia o, int code_auteur, int Code_categorie) {
		o.setAuteur(em.find(Auteur.class, code_auteur));
		o.setCategorie(em.find(Categorie.class, Code_categorie));
		em.persist(o);
	}
	@Override
	public void addSuppMultimedia(SupportMultimedia o, int code_auteur, int Code_categorie,List<Livre> livres) {
		o.setAuteur(em.find(Auteur.class, code_auteur));
		o.setCategorie(em.find(Categorie.class, Code_categorie));
		o.setLivres(livres);
		em.persist(o);

	}

	@Override
	public void updateSuppMultimedia(SupportMultimedia o) {
		em.merge(o);
		// XXXXXXX

	}

	@Override
	public Auteur getAuteur(int id) {
		TypedQuery<Auteur> q=em.createQuery("SELECT a FROM Auteur a where a.id_aut=?1",Auteur.class);
		q.setParameter(1,id);
		try
		{return q.getResultList().get(0);}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public int getstring() {
		return 1;
	}

	@Override
	public Adherent getAdherentParLogin(String Login) {
	
		Query q=em.createQuery("select a from Adherent a where a.Login=?1");
			q.setParameter(1,Login);
			try
			{
			return (Adherent) q.getSingleResult();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			return null;
		
	}

	@Override
	public Oeuvre getOeuvre(int id_Oeuv) {
		
		if( em.find(SupportMultimedia.class,id_Oeuv)==null)
			return em.find(SupportPapier.class,id_Oeuv); 
			else
				return em.find(SupportMultimedia.class,id_Oeuv);
	}

	@Override
	public Livre getLivre(int id) {
		return em.find(Livre.class, id);
	}


	@Override
	public void addEmprunt(int id_Liv, int id_adh, Date Date_empr, Date Date_Ret_Theo) {
		Emprunt e= new Emprunt();
		Adherent a= em.find(Adherent.class, id_adh);
		Livre l= em.find(Livre.class, id_Liv);
//		Oeuvre o= em.find(Oeuvre.class, l.getId());
		
		e.setAdherent(a);
		e.setLivre(l);
		e.setDate_empr(Date_empr);
		e.setDate_Ret_Theo(Date_Ret_Theo);
		e.setId_Adh(id_adh);
		e.setId_Liv(id_Liv);
		e.setNbr_avert(0);
		em.persist(e);
	//	o.setNB_support(o.getNB_support()-1);
		l.setDispo(false);
		a.setNb_empr_enc(a.getNb_empr_enc()+1);
		//em.merge(o);
		em.merge(l);
		em.merge(a);
	}

	@Override
	public boolean veriflogin(String login, String pass) {
		Adherent aa = null;
		List<Adherent>ladh= Liste_Adherents();
		for(Adherent a:ladh)
		{
			if(a.getLogin().equals(login))
			aa=a;
		}
		if(aa==null)
			return false;
		else
			if(aa.getPassword().equals(pass))
				return true;	
		return false;

			
	}
	
	@Override
	public List<OeuvJson> Liste_OeuvreJson() {
		try
		{
		List<Oeuvre> lo= Liste_Oeuvres();
		List<OeuvJson> loj = lo.stream().map(x->x.getJsonOeuvFromOeuvre()).collect(Collectors.toList());
		
		for(OeuvJson oj:loj)
		{
			Oeuvre o= em.find(Oeuvre.class, oj.getId_Oeuv());
			List<Livre> ll= liste_livres_parOeuvre(o);
			for(Livre l:ll)
			{
				if(l.getDispo())
					{
					oj.setId_liv_dispo(l.getId());
					break;
					}
			}
		}
		return loj;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Adherent getAdherentParEmail(String email) {
		
		List<Adherent> a=Liste_Adherents().stream().filter(x->x.getEmail().equals(email)).collect(Collectors.toList());
		if(a.size()==0)
			return null;
		else
			return a.get(0);
	}
	}

	


	


