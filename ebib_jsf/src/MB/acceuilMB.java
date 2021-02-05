package MB;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import entity.*;
import sessions.IRemote;
@ManagedBean(name="acceuilMB")
@ViewScoped
public class acceuilMB  {
	
		private List<Oeuvre> oeuvs;
	     @EJB
	    private IRemote metier;
	    private String mc; 
	     
	    public void showMessage(String msg) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Attention",msg );
	  
	        PrimeFaces.current().dialog().showMessageDynamic(message);
	    }
	    public String getMc() {
			return mc;
		}

		public void setMc(String mc) {
			this.mc = mc;
		}

		@PostConstruct
	    void init()
	    {	
			//oeuvs=metier.Liste_Oeuvres();
	    }
	     
	    public List<Oeuvre> getoeuvreparmc(String mc)
	    {
	    	return metier.OeuvresParMotsCle(mc);
	    }
	     
	    
	 

		public List<Oeuvre> getOeuvs() {
			return oeuvs;
		}

		public void setOeuvs(List<Oeuvre> oeuvs) {
			this.oeuvs = oeuvs;
		}


		public IRemote getMetier() {
			return metier;
		}

		public void setMetier(IRemote metier) {
			this.metier = metier;
		}
	 /*
		public void filtrer() throws IOException
		{
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR," !!!!!!!!!!!!!!!!", " Login Error!"));

				
				
			
			/*if(!mc.equals(""))
			{
				try {
					//FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");

					oeuvs=getoeuvreparmc(mc);
				
				FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR, oeuvs.size()+"!!!!!!!!!!!!!!!!", " Login Error!"));
				
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
				{
				FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "chaine vide ", " Login Error!"));
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
				}
			
		}
*/
		public String tousoeuv()
		{
			oeuvs=metier.Liste_Oeuvres();
			init();
			return "success";
		} 
		public String mesemprunts()
		{
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("mesemprunts.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "success";
		} 
		
		public String filtrer()
		{
			List<Oeuvre> l1=metier.OeuvresParMotsCle(mc);
			
						if(l1==null)
						{
							return "fail";		
						}
						else 
						{
							oeuvs=l1;
							init();
							return "success";
							}
		}
		
		
		public String emprunter(String id) throws IOException
		{	
			
			//showMessage("vous étes parmi les personnes bloqués");
			Oeuvre x= metier.getOeuvre(Integer.parseInt(id.toString()));
			Adherent ad=metier.getAdherentParLogin(loginMB.data.toString());
			System.out.println(ad.toString());
			System.out.println(x.toString());

			if(ad.getBlacklisted())
			{
				showMessage("vous étes parmi les personnes bloqués");
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("acceuil.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return "fail";
			}
			else if(ad.getNb_empr_enc()>=3)	
			{
				showMessage("vous avez déja atteint le nombre maximum des emprunts");
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");

				return "fail";
			}
			else {
				
			
		    	Livre livre = new Livre();
		    	List<Livre> lv=metier.liste_livres_parOeuvre(x);
		    	
		    	if(lv==null || lv.size()==0)
		    	{
		    		showMessage("pas de livre disponible ou liste vide");
		    		return "fail";
		    	}
		    	else {
		    		
		    		
		    		
		    	livre = lv.get(0);
		    	
		    	/*Date dt = new Date();
		    	
		    	e.setDate_empr(dt);
		    	dt.setMonth(1);
		    	e.setDate_retour_theorique(dt);*/
		    	
		    	java.sql.Date d=new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		    	java.sql.Date dd= new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		    	metier.addEmprunt(livre.getId(), ad.getId_Adh(),d,dd);
		       
		       showMessage("emprunt ajouté !");
			FacesContext.getCurrentInstance().getExternalContext().redirect("acceuil.xhtml");
		       return "success";
		    	}
			
		    	
			}
			  
		}
		
	
}
