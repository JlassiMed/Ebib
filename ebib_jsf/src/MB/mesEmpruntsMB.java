package MB;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entity.Adherent;
import entity.Emprunt;
import entity.Oeuvre;
import sessions.IRemote;

@ManagedBean(name="mesEmpruntsMB")
@ViewScoped
public class mesEmpruntsMB {
	private List<Emprunt> emprs;
    @EJB
   private IRemote metier;
	public List<Emprunt> getEmprs() {
		return emprs;
	}
	public void setEmprs(List<Emprunt> emprs) {
		this.emprs = emprs;
	}
	public IRemote getMetier() {
		return metier;
	}
	public void setMetier(IRemote metier) {
		this.metier = metier;
	}
	@PostConstruct
    void init()
    {	
		Adherent a= metier.getAdherentParLogin(loginMB.data.toString());
		emprs=metier.Liste_Emprunts().stream().filter(x->x.getId_Adh()==a.getId_Adh()).collect(Collectors.toList());
    }
    
}
