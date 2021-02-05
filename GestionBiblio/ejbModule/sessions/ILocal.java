package sessions;
import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import entity.*;
@Local
public interface ILocal {
	
		

	
	
	
	
	
	// update concerne prolonger empr, ajouter des averts, blacklister l'adh s'il dépasse la date retour theo et s'il  dépasse un mois de cet date il reste tjrs blacklisted
	public void updateEmprunt(Emprunt e);
	public void deleteEmprunt(int id);
	public void retourEmprunt(Emprunt e,Date date_ret);
	
	
	
	
	public void PasserPanier(Panier P);
}
