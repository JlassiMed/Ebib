package MB;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;

import entity.Adherent;
import sessions.IRemote;

@ManagedBean(name="loginMB")
@RequestScoped
public class loginMB {
	
	@EJB
	private IRemote metier ; 
	 
	private String login ;
	private String password ; 
	private String message ; 
	public static String data;
	
	
	public void showMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Attention",msg );
         
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
	
	public void connecter() throws IOException
	{ 
		
		//return "acceuil?faces-redirect=true&login="+login;
/*
		Adherent ad=metier.getAdh_parLogin(login);
			if (ad!=null)
			{
				if(ad.getPassword().contentEquals(password))
				{
				
					try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("acceuil.xhtml");

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else  message ="Mot de passe erroné"; 
			}
			else message ="Utilisateur inexistant";
			
	
		
		showMessage(message);
		*/
		Adherent aa = null;
		List<Adherent>ladh= metier.Liste_Adherents();
		for(Adherent a:ladh)
		{
			if(a.getLogin().equals(login))
			aa=a;
		}
		if(aa==null)
				FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "user not found ", " Login Error!"));
		else 
		{
			if(aa.getPassword().equals(password))
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("acceuil.xhtml");
					data=login;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			else
				FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "pass incorrect ", " Login Error!"));

			
		}
		 
		
	}
	  
	public IRemote getMetier() {
		return metier;
	}

	public void setMetier(IRemote metier) {
		this.metier = metier;
	}

	public String getData() {
		return data;
	}

	public String getMessage() {
		return message;
	
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
