package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sessions.IRemote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import application.Main;
import entity.Adherent;
import entity.Auteur;
import entity.Categorie;
import entity.Emprunt;
import entity.Enseignant;
import entity.Etudiant;
import entity.Livre;
import entity.Oeuvre;
import entity.SupportMultimedia;
import entity.SupportPapier;
import javafx.scene.input.MouseEvent;
public class AcceuilController implements Initializable {
	 @FXML TextField Login_adh,Nom_adh,prenom_adh,email_adh,tlf_adh,cin_adh,adresse_adh,nbr_emprEnc_adh,anneeinscritOuDepartement,gradeOuFilliere,
	 Nom_aut,prenom_aut,
	 lib_cat,
	 tit_ouev,nbr_supp_ouev;
	 
	 //datagridciew emprunt
	 @FXML TableView<Emprunt> table_empr;
	 @FXML TableColumn<Emprunt, Integer> Col_idadh;
	 @FXML TableColumn<Emprunt, Integer>Col_idliv;
	 @FXML TableColumn<Emprunt, Date>Col_Dempr;
	 @FXML TableColumn<Emprunt, Date>Col_DretTheo;
	 @FXML TableColumn<Emprunt, Date>Col_DretEFC;
	 @FXML TableColumn<Emprunt,Integer>Col_nbrAvert;
	 
	 
	@FXML ComboBox<String> type_adh= new ComboBox<String>(),
			Blacklisted_adh=new ComboBox<String>(),
			combo_idaut_oeuv=new ComboBox<String>(),
					type_oeuv= new ComboBox<String>(),combo_categ_ouev=new ComboBox<String>(),
							combo_Ouev_Liv=new ComboBox<String>(),dispo_liv=new ComboBox<String>();
	@FXML ListView<String> ListAdh,list_aut,list_cat,list_oeuv,list_liv;
	@FXML Label Filliere_adh,annee_ins_adh,dept_adh,Grade_adh;
	@FXML Tab Tab_GO; 
	@FXML DatePicker date_edt_liv;
	Boolean itemcliked_adh=false;
	IRemote bean=Main.bean;
	
	
	@FXML
	public void Update()
	{
		
		list_liv.getItems().clear();
		ListAdh.getItems().clear();
		list_aut.getItems().clear();
		list_cat.getItems().clear();
		list_oeuv.getItems().clear();
		combo_Ouev_Liv.getItems().clear();
		combo_categ_ouev.getItems().clear();
		type_oeuv.getItems().clear();
		type_adh.getItems().clear();
		Blacklisted_adh.getItems().clear();
		combo_idaut_oeuv.getItems().clear();
		dispo_liv.getItems().clear();
		
		
		type_adh.getItems().addAll("Enseignant","Etudiant");
		Blacklisted_adh.getItems().addAll("Oui","Non");
		
		// remplissage liste adh
		 List<String> lps= bean.Liste_Adherents().stream().map(Adherent::getLogin).collect(Collectors.toList());
	        if(lps.size()>0)
		 	{
	        ObservableList<String> items = FXCollections.observableArrayList();
	        ListAdh.setItems(items);
	        for(int i=0;i<lps.size();i++)
	        {
	        items.add(lps.get(i));
	        }
		 	}
	     
	        // auteurs
	        
	        List<String> lpm=bean.Liste_Auteurs().stream().map(x->x.getId_aut()+","+x.getNom_aut()+" "+x.getPrenom_aut()).collect(Collectors.toList());
	        if(lpm.size()>0)
	        {ObservableList<String> auts = FXCollections.observableArrayList();
	        list_aut.setItems(auts);
	        for(int i=0;i<lpm.size();i++)
	        {
	        auts.add(lpm.get(i));
	        }
	        }
	        
	        // catégories
	        List<String> lpc= bean.Liste_Categories().stream().map(x-> x.getId_cat()+","+x.getLibelle()).collect(Collectors.toList());
	        if(lpc.size()>0)
	        {
	        ObservableList<String> cats = FXCollections.observableArrayList();
	        list_cat.setItems(cats);
	        for(int i=0;i<lpc.size();i++)
	        {
	        cats.add(lpc.get(i));
	        }
	        }
	        // oeuvre et ses combos
	        List<String> lo= bean.Liste_Oeuvres().stream().map(x->x.getId_Oeuv()+","+x.getTitre()).collect(Collectors.toList());
	        if(lo.size()>0)
	        {
	        ObservableList<String> os = FXCollections.observableArrayList();
	        list_oeuv.setItems(os);
	        for(int i=0;i<lo.size();i++)
	        {
	        os.add(lo.get(i));
	        }
	        }
	        
	        type_oeuv.getItems().addAll("SupportMultimedia","SupportPapier");
	        List<String> loa=bean.Liste_Auteurs().stream().map(x-> String.valueOf(x.getId_aut())).collect(Collectors.toList());
	        if(loa.size()>0)
	        combo_idaut_oeuv.getItems().addAll(loa);
	        List<String> loc=bean.Liste_Categories().stream().map(x-> x.getId_cat()+","+x.getLibelle()).collect(Collectors.toList());
	        if(loc.size()>0)
	        combo_categ_ouev.getItems().addAll(loc);
	        // livres
	        List<String> ll= bean.Liste_Livres().stream().map(x->x.getId()+","+x.getOeuvre().getTitre()).collect(Collectors.toList());
	        if(ll.size()>0)
	        {
	        ObservableList<String> os = FXCollections.observableArrayList();
	        list_liv.setItems(os);
	        for(int i=0;i<ll.size();i++)
	        {
	        os.add(ll.get(i));
	        }
	        }
	        List<String> lcat=bean.Liste_Oeuvres().stream().map(x->x.getId_Oeuv()+", "+x.getTitre()).collect(Collectors.toList());
	        combo_Ouev_Liv.getItems().addAll(lcat);
	        dispo_liv.getItems().addAll("Oui","Non");
	}
	
	public boolean testAlpha(String ch)
    {
    String alpha="azertyuiopqsdfghjklmwxcvbnAZERTYUIOPQSDFGHJKLMWXCVBN";
       for(int i=0;i<ch.length();i++)
      {
          if(alpha.contains(String.valueOf(ch.charAt(i)))==false)
              return false;
      }
        return true;
    }
	 public boolean testNum(String ch)
	    {
	    String num="0123456789";
	       for(int i=0;i<ch.length();i++)
	      {
	          if(num.contains(String.valueOf(ch.charAt(i)))==false)
	              return false;
	      }
	        return true;
	    }
	 public boolean testEmail(String ch)
	    {
		 if(ch.contains("@"))
			 return true;
		 return false;
	    }
	@FXML
    private void handleButtonActionAjout(ActionEvent event) {
           if(itemcliked_adh)
           {
        	   
        	   
        	   Login_adh.setDisable(false);
        	   type_adh.setDisable(false);
	           Blacklisted_adh.setDisable(true);
	           nbr_emprEnc_adh.setDisable(true);
	           email_adh.setDisable(false);
	           cin_adh.setDisable(false);
	           anneeinscritOuDepartement.setDisable(false);
	           	Grade_adh.setVisible(false);
           		dept_adh.setVisible(false);
           		Filliere_adh.setVisible(false);
           		annee_ins_adh.setVisible(false);
           		gradeOuFilliere.setVisible(false);
           		anneeinscritOuDepartement.setVisible(false);
           		
           		
	           Login_adh.setText("");
	           Nom_adh.setText("");
	           prenom_adh.setText("");
	           email_adh.setText("");
	           cin_adh.setText("");
	           tlf_adh.setText("");
	           adresse_adh.setText("");
	           nbr_emprEnc_adh.setText("0");
	           gradeOuFilliere.setText("");
          		anneeinscritOuDepartement.setText("");
	           
	           itemcliked_adh=false;
           ListAdh.getSelectionModel().select(-1);
           type_adh.getSelectionModel().select(-1);
           Blacklisted_adh.getSelectionModel().select(-1);
           }
           else
        	   
           {
        	    if(type_adh.getSelectionModel().getSelectedIndex()==-1)
               {
                   javax.swing.JOptionPane.showMessageDialog(null," Choisir le type de l'adhérent !");

               }
        	    else if(!Login_adh.getText().equals("") && !Nom_adh.getText().equals("")&&!prenom_adh.getText().equals("") 
        		   && !email_adh.getText().equals("")
        		   && !tlf_adh.getText().equals("") 
        		   && !cin_adh.getText().equals("") && !adresse_adh.getText().equals("") 
        		   && !anneeinscritOuDepartement.getText().equals("") && !gradeOuFilliere.getText().equals("") )
           {
           Adherent a= bean.getAdherentParLogin(Login_adh.getText());
           if(a != null)
           { 
               javax.swing.JOptionPane.showMessageDialog(null,"login existant");
           }
           
           else if(!testAlpha(Nom_adh.getText()) || !testAlpha(prenom_adh.getText()))
           {
               javax.swing.JOptionPane.showMessageDialog(null,"le nom et le prenom doivent contenir seulement des caractères alphabet !");
           }
           else if(!testEmail(email_adh.getText()))
           {
               javax.swing.JOptionPane.showMessageDialog(null,"Email invalide !");

           }
           else if(!testNum(tlf_adh.getText()) || tlf_adh.getText().length()!=8)
           {
               javax.swing.JOptionPane.showMessageDialog(null,"le numero de telephone doit contenir seulement des chiffres et de longueur 8 !");

           }
           else if(cin_adh.getText().length()!=8 || !testNum(cin_adh.getText()))
           {
               javax.swing.JOptionPane.showMessageDialog(null,"le cin doit être de longueur 8 et contient seulement des chiffres !");
           }
           else if(type_adh.getValue().equals("Enseignant") && (!testAlpha(anneeinscritOuDepartement.getText()) || !testAlpha(gradeOuFilliere.getText())) )
           {
               javax.swing.JOptionPane.showMessageDialog(null,"le departement et le grade doivent contenir seulement des caractères alphabet !");
           }
           else if(type_adh.getValue().equals("Etudiant") && (!testNum(anneeinscritOuDepartement.getText()) || anneeinscritOuDepartement.getText().length()!=4 || !testAlpha(gradeOuFilliere.getText())) )
           {
               javax.swing.JOptionPane.showMessageDialog(null,"l'année d'inscrit doit etre numerique de longueur 4 et la filliere doit contenir seulement des caractères alphabet !");
           } 	    
           else
           {
        		   
               
               try
               {
               
               if(type_adh.getValue().equals("Enseignant"))
               {
            	   Enseignant e= new Enseignant();
            	   e.setLogin(Login_adh.getText());
                   e.setNom(Nom_adh.getText());
                   e.setPrenom(prenom_adh.getText());
                   e.setEmail(email_adh.getText());
                   e.setCin(cin_adh.getText());
                   e.setTelephone(tlf_adh.getText());
                   e.setAdresse(adresse_adh.getText());
                   e.setNb_empr_enc(0);
                   e.setBlacklisted(false);
            	   e.setDepartement(anneeinscritOuDepartement.getText());
            	   e.setGrade(gradeOuFilliere.getText());
            	   bean.addEnseigniant(e);
               }
               else
               {
            	   Etudiant e= new Etudiant();
            	   e.setLogin(Login_adh.getText());
                   e.setNom(Nom_adh.getText());
                   e.setPrenom(prenom_adh.getText());
                   e.setEmail(email_adh.getText());
                   e.setCin(cin_adh.getText());
                   e.setTelephone(tlf_adh.getText());
                   e.setAdresse(adresse_adh.getText());
                   e.setNb_empr_enc(0);
                   e.setBlacklisted(false);
            	   e.setAnnee_inscrit(anneeinscritOuDepartement.getText());
            	   e.setFiliere(gradeOuFilliere.getText());
            	   bean.addEtudiant(e);
               }
               
                                
                ObservableList<String> items= ListAdh.getItems();
               items.clear();
      		 	List<String> lps= bean.Liste_Adherents().stream().map(Adherent::getLogin).collect(Collectors.toList());
               items = FXCollections.observableArrayList();
               ListAdh.setItems(items);
               for(int i=0;i<lps.size();i++)
                   {
                       items.add(lps.get(i));
                   }
               javax.swing.JOptionPane.showMessageDialog(null,"ajout Réussite");
               
               Login_adh.setDisable(false);
        	   type_adh.setDisable(false);
	           Blacklisted_adh.setDisable(true);
	           nbr_emprEnc_adh.setDisable(true);
	           email_adh.setDisable(false);
	           cin_adh.setDisable(false);
	           anneeinscritOuDepartement.setDisable(false);
	           	Grade_adh.setVisible(false);
           		dept_adh.setVisible(false);
           		Filliere_adh.setVisible(false);
           		annee_ins_adh.setVisible(false);
           		gradeOuFilliere.setVisible(false);
           		anneeinscritOuDepartement.setVisible(false);
           		
           		
	           Login_adh.setText("");
	           Nom_adh.setText("");
	           prenom_adh.setText("");
	           email_adh.setText("");
	           cin_adh.setText("");
	           tlf_adh.setText("");
	           adresse_adh.setText("");
	           nbr_emprEnc_adh.setText("0");
	           gradeOuFilliere.setText("");
          		anneeinscritOuDepartement.setText("");
	           
	           itemcliked_adh=false;
           ListAdh.getSelectionModel().select(-1);
           type_adh.getSelectionModel().select(-1);
           Blacklisted_adh.getSelectionModel().select(-1);
               
               }catch(Exception e)
               {
                   System.out.print(e.getMessage()+" in bouton ajout");
               }
                
                   
           }
           }
            else
                javax.swing.JOptionPane.showMessageDialog(null,"remplir tous les champs svp !");
               
           }

   }
	
	@FXML
    private void handleButtonActionUpdateUser(ActionEvent event)  {
        if(!itemcliked_adh || ListAdh.getSelectionModel().getSelectedIndex()==-1)
            javax.swing.JOptionPane.showMessageDialog(null,"veuillez selectionner un adhérent!");
        else
             if( !Nom_adh.getText().equals("")&&!prenom_adh.getText().equals("") 
          		   && !tlf_adh.getText().equals("") 
          		    && !adresse_adh.getText().equals("") 
          		   && !anneeinscritOuDepartement.getText().equals("") && !gradeOuFilliere.getText().equals("") )
        	{
            	 if(!testAlpha(Nom_adh.getText()) || !testAlpha(prenom_adh.getText()) || !testAlpha(adresse_adh.getText()) )
            	 	{
            		 	javax.swing.JOptionPane.showMessageDialog(null,"le nom et le prenom et l'adresse doivent contenir seulement des caractères alphabet !");
            	 	}
            	 else if(!testNum(tlf_adh.getText()) || tlf_adh.getText().length()!=8)
            	 {
                    javax.swing.JOptionPane.showMessageDialog(null,"le numero de telephone doit contenir seulement des chiffres et de longueur 8 !");
                    
            	 }
            	 else if(type_adh.getValue().equals("Enseignant") && (!testAlpha(anneeinscritOuDepartement.getText()) || !testAlpha(gradeOuFilliere.getText())) )
               {
                   javax.swing.JOptionPane.showMessageDialog(null,"le departement et le grade doivent contenir seulement des caractères alphabet !");
               }
               else if(type_adh.getValue().equals("Etudiant") && ( !testAlpha(gradeOuFilliere.getText())) )
               {
                   javax.swing.JOptionPane.showMessageDialog(null,"la filliere doit contenir seulement des caractères alphabet !");
               } 
               else
               {   		try{   
            	String selected= (String) ListAdh.getSelectionModel().getSelectedItem();
	            Adherent a = bean.getAdherentParLogin(selected);
	            if(a instanceof Enseignant)
	            {
	            	Enseignant e= (Enseignant) a;
	            	e.setNom(Nom_adh.getText());
	            	e.setPrenom(prenom_adh.getText());
	            	e.setAdresse(adresse_adh.getText());
	            	e.setTelephone(tlf_adh.getText());
	            	e.setDepartement(anneeinscritOuDepartement.getText());
	            	e.setGrade(gradeOuFilliere.getText());
	            	if(Blacklisted_adh.getValue().equals("Oui"))
	            	{
	            		e.setBlacklisted(true);
	            	}
	            	else e.setBlacklisted(false);
	            	bean.updateEnseignant(e);
	            }
	            else
	            {
	            	Etudiant e= (Etudiant) a;
	            	e.setNom(Nom_adh.getText());
	            	e.setPrenom(prenom_adh.getText());
	            	e.setAdresse(adresse_adh.getText());
	            	e.setTelephone(tlf_adh.getText());
	            	e.setFiliere(gradeOuFilliere.getText());
	            	if(Blacklisted_adh.getValue().equals("Oui"))
	            	{
	            		e.setBlacklisted(true);
	            	}
	            	else e.setBlacklisted(false);
	            	bean.updateEtudiant(e);
	            	
	            }
            	
                        javax.swing.JOptionPane.showMessageDialog(null,"adhrent modifié avec succés");
                        ObservableList<String> items= ListAdh.getItems();
                        items.clear();
     		 	List<String> lps= bean.Liste_Adherents().stream().map(Adherent::getLogin).collect(Collectors.toList());
               items = FXCollections.observableArrayList();
               for(int i=0;i<lps.size();i++)
                   {
                   items.add(lps.get(i));
                   }
               ListAdh.setItems(items);
          
               Login_adh.setDisable(false);
        	   type_adh.setDisable(false);
	           Blacklisted_adh.setDisable(true);
	           nbr_emprEnc_adh.setDisable(true);
	           email_adh.setDisable(false);
	           cin_adh.setDisable(false);
	           anneeinscritOuDepartement.setDisable(false);
	           	Grade_adh.setVisible(false);
           		dept_adh.setVisible(false);
           		Filliere_adh.setVisible(false);
           		annee_ins_adh.setVisible(false);
           		gradeOuFilliere.setVisible(false);
           		anneeinscritOuDepartement.setVisible(false);
           		
           		
	           Login_adh.setText("");
	           Nom_adh.setText("");
	           prenom_adh.setText("");
	           email_adh.setText("");
	           cin_adh.setText("");
	           tlf_adh.setText("");
	           adresse_adh.setText("");
	           nbr_emprEnc_adh.setText("0");
	           gradeOuFilliere.setText("");
          		anneeinscritOuDepartement.setText("");
	           
	           itemcliked_adh=false;
           ListAdh.getSelectionModel().select(-1);
           type_adh.getSelectionModel().select(-1);
           Blacklisted_adh.getSelectionModel().select(-1);
               
               
            }catch(Exception exx)
        	{
            System.out.print(exx.getMessage()+" in bouton modif_adh");
        	}
               }
        	}	
             else
        	{
         		javax.swing.JOptionPane.showMessageDialog(null,"remplir les champs vides !");
         	}
        }
   
	@FXML
    private void handleButtonActionDeleteUser(ActionEvent event)  {
        if(itemcliked_adh==false || ListAdh.getSelectionModel().getSelectedIndex()==-1)
            javax.swing.JOptionPane.showMessageDialog(null,"veuillez selectionner un utilisateur !");
        else
        { 
        	try {
        	String selected= (String) ListAdh.getSelectionModel().getSelectedItem();
            Adherent a = bean.getAdherentParLogin(selected);
            if(a instanceof Enseignant)
            {
            	Enseignant e= (Enseignant) a;
            	
            	bean.deleteEnseignant(e.getId_Adh());
            }
            else
            {
            	Etudiant e= (Etudiant) a;
    
            	bean.deleteEtudiant(e.getId_Adh());
            	
            }
            javax.swing.JOptionPane.showMessageDialog(null,"Adhérent supprimé avec succés");
            ObservableList<String> items= ListAdh.getItems();
            items.clear();
 		 	List<String> lps= bean.Liste_Adherents().stream().map(Adherent::getLogin).collect(Collectors.toList());
            items = FXCollections.observableArrayList();
            for(int i=0;i<lps.size();i++)
                {
                items.add(lps.get(i));
                }
            ListAdh.setItems(items);
            
            Login_adh.setDisable(false);
     	   type_adh.setDisable(false);
	           Blacklisted_adh.setDisable(true);
	           nbr_emprEnc_adh.setDisable(true);
	           email_adh.setDisable(false);
	           cin_adh.setDisable(false);
	           anneeinscritOuDepartement.setDisable(false);
	           	Grade_adh.setVisible(false);
        		dept_adh.setVisible(false);
        		Filliere_adh.setVisible(false);
        		annee_ins_adh.setVisible(false);
        		gradeOuFilliere.setVisible(false);
        		anneeinscritOuDepartement.setVisible(false);
        		
        		
	           Login_adh.setText("");
	           Nom_adh.setText("");
	           prenom_adh.setText("");
	           email_adh.setText("");
	           cin_adh.setText("");
	           tlf_adh.setText("");
	           adresse_adh.setText("");
	           nbr_emprEnc_adh.setText("0");
	           gradeOuFilliere.setText("");
       		anneeinscritOuDepartement.setText("");
	           
	           itemcliked_adh=false;
        ListAdh.getSelectionModel().select(-1);
        type_adh.getSelectionModel().select(-1);
        Blacklisted_adh.getSelectionModel().select(-1);
            
        	}catch(Exception ex)
        	{
        		System.out.println(" **** in delete user "+ex.getMessage());
        	}
        
             
              
     
        
        }
        }

   
	@FXML
	public void afficher()
	{
		if (type_adh.getValue().equals("Enseignant"))
	{
		Grade_adh.setVisible(true);
   		dept_adh.setVisible(true);
   		Filliere_adh.setVisible(false);
    	annee_ins_adh.setVisible(false);
	}
		
	else if (type_adh.getValue().equals("Etudiant"))
	{
		Filliere_adh.setVisible(true);
    	annee_ins_adh.setVisible(true);
    	Grade_adh.setVisible(false);
   		dept_adh.setVisible(false);
	}
	gradeOuFilliere.setVisible(true);
	anneeinscritOuDepartement.setVisible(true);
		
	}
	
	boolean autItemSelected=false;
	@FXML
	private void handleButtonActionAjoutAut(ActionEvent event) throws IOException {
        
        if(autItemSelected==true)
       {
           Nom_aut.setText("");
           prenom_aut.setText("");
           
           autItemSelected=false;
           list_aut.getSelectionModel().select(-1);
       }
       else
       {
         if(Nom_aut.getText().isEmpty() || prenom_aut.getText().isEmpty())
         {
             javax.swing.JOptionPane.showMessageDialog(null,"remplir tous les champs svp !");
          
         }
         else if(!testAlpha(Nom_aut.getText()) ||!testAlpha(prenom_aut.getText()) )
         {
             javax.swing.JOptionPane.showMessageDialog(null,"le nom et le prenom doivent contenir que des lettres alphabétiques !");
         }
         else
       try
       {
         Auteur a = new Auteur();
       a.setNom_aut(Nom_aut.getText());
       a.setPrenom_aut(prenom_aut.getText());
       bean.addAuteur(a);
                     javax.swing.JOptionPane.showMessageDialog(null,"auteur ajouté avec succés");
                     Nom_aut.setText("");
                     prenom_aut.setText("");
          list_aut.getItems().clear();
	        List<String> lpm=bean.Liste_Auteurs().stream().map(x->x.getId_aut()+","+x.getNom_aut()+" "+x.getPrenom_aut()).collect(Collectors.toList());
       ObservableList<String> mots = FXCollections.observableArrayList();
       list_aut.setItems(mots);
       for(int i=0;i<lpm.size();i++)
       {
       mots.add(lpm.get(i));
       }
       autItemSelected=false;
       }
       catch(NumberFormatException e)
       {
           System.out.println("erreur d'ajout d'un auteur "+e.getMessage());
       }
       
       }
       
   }
	
	 @FXML
     private void handleButtonActionUpdateAut(ActionEvent event) throws IOException {
       
         if(list_aut.getSelectionModel().getSelectedIndex()==-1 || autItemSelected==false)
       {
           javax.swing.JOptionPane.showMessageDialog(null,"veuiller selectionner un auteur ! ");
       
       }else if(!testAlpha(Nom_aut.getText()) ||!testAlpha(prenom_aut.getText()) )
       {
           javax.swing.JOptionPane.showMessageDialog(null,"le nom et le prenom doivent contenir que des lettres alphabétiques !");
       }
       else
         if(Nom_aut.getText().isEmpty() || prenom_aut.getText().isEmpty() )
         {
                javax.swing.JOptionPane.showMessageDialog(null,"remplir tous les champs svp !");
          
         }
        
         else
       try
       {
    	   String selected= (String) list_aut.getSelectionModel().getSelectedItem();
           String[] sa= selected.split(",");
           int id_aut=Integer.parseInt(sa[0]);
          Auteur a=bean.getAuteur(id_aut);
       a.setNom_aut(Nom_aut.getText());
       a.setPrenom_aut(prenom_aut.getText());
       bean.updateAuteur(a);
                     javax.swing.JOptionPane.showMessageDialog(null,"auteur modfié avec succés");
                     Nom_aut.setText("");
                     prenom_aut.setText("");
          list_aut.getItems().clear();
	        List<String> lpm=bean.Liste_Auteurs().stream().map(x->x.getId_aut()+","+x.getNom_aut()+" "+x.getPrenom_aut()).collect(Collectors.toList());
       ObservableList<String> mots = FXCollections.observableArrayList();
       list_aut.setItems(mots);
       for(int i=0;i<lpm.size();i++)
       {
       mots.add(lpm.get(i));
       }
       autItemSelected=false;
       }
       catch(NumberFormatException e)
       {
           System.out.println("erreur modification auteur "+e.getMessage());
       }
      
   }

	 
	 @FXML
     private void handleButtonActionDeleteAut(ActionEvent event) throws IOException {
       if(list_aut.getSelectionModel().getSelectedIndex()==-1 || autItemSelected==false)
       {
           javax.swing.JOptionPane.showMessageDialog(null,"veuiller selectionner un auteur ! ");
       }
       else
         try
       {
        	 String selected= (String) list_aut.getSelectionModel().getSelectedItem();
             String[] sa= selected.split(",");
             int id_aut=Integer.parseInt(sa[0]);
       bean.deleteAuteur(id_aut);
                     javax.swing.JOptionPane.showMessageDialog(null,"auteur supprimé avec succés");
                     Nom_aut.setText("");
                     prenom_aut.setText("");
          list_aut.getItems().clear();
	        List<String> lpm=bean.Liste_Auteurs().stream().map(x->x.getId_aut()+","+x.getNom_aut()+" "+x.getPrenom_aut()).collect(Collectors.toList());
       ObservableList<String> mots = FXCollections.observableArrayList();
       list_aut.setItems(mots);
       for(int i=0;i<lpm.size();i++)
       {
       mots.add(lpm.get(i));
       }
       autItemSelected=false;
       }
       catch(NumberFormatException e)
       {
           System.out.println("erreur suppression auteur "+e.getMessage());
       }
      
   }
	boolean catItemSelected=false;
	@FXML
	private void handleButtonActionAjoutCat(ActionEvent event) throws IOException {
        
        if(catItemSelected==true)
       {
           lib_cat.setText("");
           
           catItemSelected=false;
           list_cat.getSelectionModel().select(-1);
       }
       else
       {
         if(lib_cat.getText().isEmpty() )
         {
             javax.swing.JOptionPane.showMessageDialog(null,"remplir le libellé svp !");
          
         }
         else if(!testAlpha(lib_cat.getText()) )
         {
             javax.swing.JOptionPane.showMessageDialog(null,"le libellé doit contenir que des lettres alphabétiques !");
         }
         else
       try
       {
         Categorie c= new Categorie();
         c.setLibelle(lib_cat.getText());
         bean.addCategorie(c);
                     javax.swing.JOptionPane.showMessageDialog(null,"catégorie ajoutée avec succés");
                     lib_cat.setText("");
          list_cat.getItems().clear();
	        List<String> lpm= bean.Liste_Categories().stream().map(x-> x.getId_cat()+","+x.getLibelle()).collect(Collectors.toList());
       ObservableList<String> mots = FXCollections.observableArrayList();
       list_cat.setItems(mots);
       for(int i=0;i<lpm.size();i++)
       {
       mots.add(lpm.get(i));
       }
       catItemSelected=false;
       }
       catch(NumberFormatException e)
       {
           System.out.println("erreur d'ajout d'une catégorie "+e.getMessage());
       }
       
       }
       
   }
	 
	@FXML
    private void handleButtonActionDeleteCat(ActionEvent event) throws IOException {
      if(list_cat.getSelectionModel().getSelectedIndex()==-1 || catItemSelected==false )
      {
          javax.swing.JOptionPane.showMessageDialog(null,"veuiller selectionner une catégorie ! ");
      }
      else
        try
      {
       	 String selected= (String) list_cat.getSelectionModel().getSelectedItem();
            String[] sa= selected.split(",");
            int id_cat=Integer.parseInt(sa[0]);
            bean.deleteCategorie(id_cat);
                    javax.swing.JOptionPane.showMessageDialog(null,"Catégorie supprimée avec succés");
                    lib_cat.setText("");
         list_cat.getItems().clear();
         List<String> lpm= bean.Liste_Categories().stream().map(x-> x.getId_cat()+","+x.getLibelle()).collect(Collectors.toList());
         ObservableList<String> mots = FXCollections.observableArrayList();
         list_cat.setItems(mots);
         for(int i=0;i<lpm.size();i++)
         {
         mots.add(lpm.get(i));
         }
         catItemSelected=false;
      }
      catch(NumberFormatException e)
      {
          System.out.println("erreur suprression d'une catégorie "+e.getMessage());
      }
     
  }
	 
	boolean oeuvItemSelected=false;
	@FXML
	private void handleButtonActionAjoutOeuv(ActionEvent event) throws IOException {
        
        if(oeuvItemSelected==true)
       {
        	tit_ouev.setText("");
        	nbr_supp_ouev.setText("");
           
        	oeuvItemSelected=false;
        	
        	combo_idaut_oeuv.setDisable(false);
        	combo_categ_ouev.setDisable(false);
        	type_oeuv.setDisable(false);
        	
           list_oeuv.getSelectionModel().select(-1);
           type_oeuv.getSelectionModel().select(-1);
           combo_idaut_oeuv.getSelectionModel().select(-1);
           combo_categ_ouev.getSelectionModel().select(-1);
       }
       else
       {
    	   if(type_oeuv.getSelectionModel().getSelectedIndex()==-1)
           {
               javax.swing.JOptionPane.showMessageDialog(null," Choisir le type de l'oeuvre !");

           }
    	   else if(combo_idaut_oeuv.getSelectionModel().getSelectedIndex()==-1)
           {
               javax.swing.JOptionPane.showMessageDialog(null," Choisir l'id de l'auteur !");

           }
    	   else if(combo_categ_ouev.getSelectionModel().getSelectedIndex()==-1)
           {
               javax.swing.JOptionPane.showMessageDialog(null," Choisir une catégorie !");

           }
    	   else if(tit_ouev.getText().isEmpty() || nbr_supp_ouev.getText().isEmpty())
         {
             javax.swing.JOptionPane.showMessageDialog(null,"remplir tous les champs svp !");
          
         }
         else if(!testAlpha(tit_ouev.getText()) ||!testNum(nbr_supp_ouev.getText()) )
         {
             javax.swing.JOptionPane.showMessageDialog(null,"le titre doit contenir que des lettres alphabétiques et le nombre de supports doit etre numérique !");
         }
         else
       try
       {
         
         String selected= combo_categ_ouev.getValue();
         String[] sa= selected.split(",");
         int id_cat=Integer.parseInt(sa[0]);
         if(type_oeuv.getValue().equals("SupportMultimedia"))
         {
        	 SupportMultimedia sm= new SupportMultimedia();
        	 sm.setTitre(tit_ouev.getText());
        	 sm.setNB_support(Integer.parseInt(nbr_supp_ouev.getText()));
        	 sm.setNb_dispo(Integer.parseInt(nbr_supp_ouev.getText()));
        	 bean.addSuppMultimedia(sm, Integer.parseInt(combo_idaut_oeuv.getValue()),id_cat);
         }
         else
         {
        	 SupportPapier sm= new SupportPapier();
        	 sm.setTitre(tit_ouev.getText());
        	 sm.setNB_support(Integer.parseInt(nbr_supp_ouev.getText()));
        	 sm.setNb_dispo(Integer.parseInt(nbr_supp_ouev.getText()));
        	 bean.addSuppPapier(sm, Integer.parseInt(combo_idaut_oeuv.getValue()),id_cat);
         }
       
                     javax.swing.JOptionPane.showMessageDialog(null,"Oeuvre ajoutée avec succés");
                     nbr_supp_ouev.setText("");
                     tit_ouev.setText("");
          list_oeuv.getItems().clear();
	        List<String> lo= bean.Liste_Oeuvres().stream().map(x->x.getId_Oeuv()+","+x.getTitre()).collect(Collectors.toList());
	        if(lo.size()>0)
	        {
	        ObservableList<String> os = FXCollections.observableArrayList();
	        list_oeuv.setItems(os);
	        for(int i=0;i<lo.size();i++)
	        {
	        os.add(lo.get(i));
	        }
	        }
	        oeuvItemSelected=false;
	        list_oeuv.getSelectionModel().select(-1);
	        type_oeuv.getSelectionModel().select(-1);
	        combo_idaut_oeuv.getSelectionModel().select(-1);
	        combo_categ_ouev.getSelectionModel().select(-1);
	        
       }
       catch(NumberFormatException e)
       {
           System.out.println("erreur d'ajout d'une oeuvre "+e.getMessage());
       }
       
       }
       
   }
	
	 @FXML
     private void handleButtonActionUpdateOeuv(ActionEvent event) throws IOException {
       
         if(list_oeuv.getSelectionModel().getSelectedIndex()==-1 || oeuvItemSelected==false)
       {
           javax.swing.JOptionPane.showMessageDialog(null,"veuiller selectionner une oeuvre ! ");
       
       } 
	   else if(combo_categ_ouev.getSelectionModel().getSelectedIndex()==-1)
       {
           javax.swing.JOptionPane.showMessageDialog(null," Choisir une catégorie !");

       }
	   else if(tit_ouev.getText().isEmpty() || nbr_supp_ouev.getText().isEmpty())
     {
         javax.swing.JOptionPane.showMessageDialog(null,"remplir tous les champs svp !");
      
     }
     else if(!testAlpha(tit_ouev.getText()) ||!testNum(nbr_supp_ouev.getText()) )
     {
         javax.swing.JOptionPane.showMessageDialog(null,"le titre doit contenir que des lettres alphabétiques et le nombre de supports doit etre numérique !");
     }
     else
       try
       {	
    	   String selected= (String) list_oeuv.getSelectionModel().getSelectedItem();
           String[] sa= selected.split(",");
           int id_Oeuv= Integer.parseInt(sa[0]);
    	   if(bean.getOeuvre(id_Oeuv) instanceof SupportMultimedia)
           {
    		   SupportMultimedia o= (SupportMultimedia) bean.getOeuvre(id_Oeuv);
    	   o.setTitre(tit_ouev.getText());
    	   o.setNB_support(Integer.parseInt(nbr_supp_ouev.getText()));
    	   bean.updateSuppMultimedia(o);
           }
    	   else
    	   {
    		   SupportPapier o= (SupportPapier) bean.getOeuvre(id_Oeuv);
        	   o.setTitre(tit_ouev.getText());
        	   o.setNB_support(Integer.parseInt(nbr_supp_ouev.getText()));
        	   bean.updateSuppPapier(o);
    	   }
           javax.swing.JOptionPane.showMessageDialog(null,"Oeuvre mise à jour avec succés !");
           
           
           
           tit_ouev.setText("");
           nbr_supp_ouev.setText("");
          
           oeuvItemSelected=false;
       	
       		combo_idaut_oeuv.setDisable(false);
       		combo_categ_ouev.setDisable(false);
       		type_oeuv.setDisable(false);
       	
       		list_oeuv.getSelectionModel().select(-1);
            type_oeuv.getSelectionModel().select(-1);
            combo_idaut_oeuv.getSelectionModel().select(-1);
            combo_categ_ouev.getSelectionModel().select(-1);
       		
       		list_oeuv.getItems().clear();
       		List<String> lo= bean.Liste_Oeuvres().stream().map(x->x.getId_Oeuv()+","+x.getTitre()).collect(Collectors.toList());
	        if(lo.size()>0)
	        {
	        ObservableList<String> os = FXCollections.observableArrayList();
	        list_oeuv.setItems(os);
	        for(int i=0;i<lo.size();i++)
	        {
	        os.add(lo.get(i));
	        }
	        }

    	   
       }
       catch(NumberFormatException e)
       {
           System.out.println("erreur dans la modification de l'oeuvre "+e.getMessage());
       }
      
   }
	
	 @FXML
	    private void handleButtonActionDeleteOeuv(ActionEvent event) throws IOException {
		 if(list_oeuv.getSelectionModel().getSelectedIndex()==-1 || oeuvItemSelected==false)
	       {
	           javax.swing.JOptionPane.showMessageDialog(null,"veuiller selectionner une oeuvre ! ");
	       
	       } 
		   
	      else
	        try
	      {
	        	String selected= (String) list_oeuv.getSelectionModel().getSelectedItem();
	            String[] sa= selected.split(",");
	            int id_Oeuv= Integer.parseInt(sa[0]);
	            bean.deleteOeuvre(id_Oeuv);;
	                    javax.swing.JOptionPane.showMessageDialog(null,"oeuvre supprimée avec succés");
	                    tit_ouev.setText("");
	                    nbr_supp_ouev.setText("");
	                   
	                    oeuvItemSelected=false;
	                	
	                		combo_idaut_oeuv.setDisable(false);
	                		combo_categ_ouev.setDisable(false);
	                		type_oeuv.setDisable(false);
	                	
	                		list_oeuv.getSelectionModel().select(-1);
	                     type_oeuv.getSelectionModel().select(-1);
	                     combo_idaut_oeuv.getSelectionModel().select(-1);
	                     combo_categ_ouev.getSelectionModel().select(-1);
	                		
	                		list_oeuv.getItems().clear();
	                		List<String> lo= bean.Liste_Oeuvres().stream().map(x->x.getId_Oeuv()+","+x.getTitre()).collect(Collectors.toList());
	         	        if(lo.size()>0)
	         	        {
	         	        ObservableList<String> os = FXCollections.observableArrayList();
	         	        list_oeuv.setItems(os);
	         	        for(int i=0;i<lo.size();i++)
	         	        {
	         	        os.add(lo.get(i));
	         	        }
	         	        }

	      }
	      catch(NumberFormatException e)
	      {
	          System.out.println("erreur suprression d'une oeuvre "+e.getMessage());
	      }
	     
	  }
	 
	 boolean livItemSelected=false;
		@FXML
		private void handleButtonActionAjoutLiv(ActionEvent event) throws IOException {
	        
	        if(livItemSelected==true)
	       {
	        	
	        	livItemSelected=false;
	        	combo_Ouev_Liv.setDisable(false);
	        	date_edt_liv.setValue(null);
	           list_liv.getSelectionModel().select(-1);
	           combo_Ouev_Liv.getSelectionModel().select(-1);
	           dispo_liv.getSelectionModel().select(-1);
	       }
	       else
	       {
	    	   if(combo_Ouev_Liv.getSelectionModel().getSelectedIndex()==-1)
	           {
	               javax.swing.JOptionPane.showMessageDialog(null," Choisir  l'oeuvre !");

	           }
	    	   else if(dispo_liv.getSelectionModel().getSelectedIndex()==-1)
	           {
	               javax.swing.JOptionPane.showMessageDialog(null," Choisir disponiblité livre !");

	           }
	    	   else if(date_edt_liv.getValue()==null)
	         {
	             javax.swing.JOptionPane.showMessageDialog(null,"remplir date édition !");
	          
	         }
	         else
	       try
	       {
	         
	         String selected= combo_Ouev_Liv.getValue();
	         String[] sa= selected.split(",");
	         int id_oeuv=Integer.parseInt(sa[0]);
	         Livre l= new Livre();
	         if(dispo_liv.getValue().equals("Oui"))
	        	 l.setDispo(true);
	         else
	        	 l.setDispo(false);
             Date d = Date.valueOf(date_edt_liv.getValue());
             l.setDate_edition(d);
             bean.addLivre(l,id_oeuv);
	                     javax.swing.JOptionPane.showMessageDialog(null,"livre ajouté avec succés");
	                     livItemSelected=false;
	     	        	combo_Ouev_Liv.setDisable(false);
	     	        	date_edt_liv.setValue(null);
	     	           list_liv.getSelectionModel().select(-1);
	     	           combo_Ouev_Liv.getSelectionModel().select(-1);
	     	           dispo_liv.getSelectionModel().select(-1);
	     	           
	     	    list_liv.getItems().clear();
		        List<String> ll= bean.Liste_Livres().stream().map(x->x.getId()+","+x.getOeuvre().getTitre()).collect(Collectors.toList());
		        if(ll.size()>0)
		        {
		        ObservableList<String> os = FXCollections.observableArrayList();
		        list_liv.setItems(os);
		        for(int i=0;i<ll.size();i++)
		        {
		        os.add(ll.get(i));
		        }
		        }
		        
	       }
	       catch(NumberFormatException e)
	       {
	           System.out.println("erreur d'ajout d'un livre "+e.getMessage());
	       }
	       
	       }
	       
	   }
		@FXML
	     private void handleButtonActionUpdateLiv(ActionEvent event) throws IOException {
	       
	         if(list_liv.getSelectionModel().getSelectedIndex()==-1 || livItemSelected==false)
	       {
	           javax.swing.JOptionPane.showMessageDialog(null,"veuiller selectionner un livre ! ");
	       
	       } 

	    	   else if(date_edt_liv.getValue()==null)
	         {
	             javax.swing.JOptionPane.showMessageDialog(null,"remplir date édition !");
	          
	         }
	         else
	       try
	       {	
	    	   String selected= (String) list_liv.getSelectionModel().getSelectedItem();
	           String[] sa= selected.split(",");
	           int id_liv= Integer.parseInt(sa[0]);
	    	   Livre l= bean.getLivre(id_liv);
	    	   if(dispo_liv.getSelectionModel().getSelectedIndex()==0)
	    		   l.setDispo(true);
	    	   else l.setDispo(false);
	    	   Date d = Date.valueOf(date_edt_liv.getValue());
	             l.setDate_edition(d);
	             bean.updateLivre(l);
	           javax.swing.JOptionPane.showMessageDialog(null,"Livre mise à jour avec succés !");
	           livItemSelected=false;
	        	combo_Ouev_Liv.setDisable(false);
	        	date_edt_liv.setValue(null);
	           list_liv.getSelectionModel().select(-1);
	           combo_Ouev_Liv.getSelectionModel().select(-1);
	           dispo_liv.getSelectionModel().select(-1);
	           
	    list_liv.getItems().clear();
       List<String> ll= bean.Liste_Livres().stream().map(x->x.getId()+","+x.getOeuvre().getTitre()).collect(Collectors.toList());
       if(ll.size()>0)
       {
       ObservableList<String> os = FXCollections.observableArrayList();
       list_liv.setItems(os);
       for(int i=0;i<ll.size();i++)
       {
       os.add(ll.get(i));
       }
       }
	           	    	   
	       }
	       catch(NumberFormatException e)
	       {
	           System.out.println("erreur dans la modification de livre "+e.getMessage());
	       }
	      
	   }
		
		@FXML
	     private void handleButtonActionDeleteLiv(ActionEvent event) throws IOException {
	       
	         if(list_liv.getSelectionModel().getSelectedIndex()==-1 || livItemSelected==false)
	       {
	           javax.swing.JOptionPane.showMessageDialog(null,"veuiller selectionner un livre ! ");
	       
	       } 
	         else
	       try
	       {	
	    	   String selected= (String) list_liv.getSelectionModel().getSelectedItem();
	           String[] sa= selected.split(",");
	           int id_liv= Integer.parseInt(sa[0]);
	    	   bean.deleteLivre(id_liv);
	           javax.swing.JOptionPane.showMessageDialog(null,"Livre supprimé avec succés !");
	           livItemSelected=false;
	        	combo_Ouev_Liv.setDisable(false);
	        	date_edt_liv.setValue(null);
	           list_liv.getSelectionModel().select(-1);
	           combo_Ouev_Liv.getSelectionModel().select(-1);
	           dispo_liv.getSelectionModel().select(-1);
	           
	    list_liv.getItems().clear();
      List<String> ll= bean.Liste_Livres().stream().map(x->x.getId()+","+x.getOeuvre().getTitre()).collect(Collectors.toList());
      if(ll.size()>0)
      {
      ObservableList<String> os = FXCollections.observableArrayList();
      list_liv.setItems(os);
      for(int i=0;i<ll.size();i++)
      {
      os.add(ll.get(i));
      }
      }
	           	    	   
	       }
	       catch(NumberFormatException e)
	       {
	           System.out.println("erreur dans la modification de livre "+e.getMessage());
	       }
	      
	   }
	 
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		type_adh.getItems().addAll("Enseignant","Etudiant");
		Blacklisted_adh.getItems().addAll("Oui","Non");
		// remplissage liste adh
		 List<String> lps= bean.Liste_Adherents().stream().map(Adherent::getLogin).collect(Collectors.toList());
	        if(lps.size()>0)
		 	{
	        ObservableList<String> items = FXCollections.observableArrayList();
	        ListAdh.setItems(items);
	        for(int i=0;i<lps.size();i++)
	        {
	        items.add(lps.get(i));
	        }
		 	}
	    
	        ListAdh.setOnMouseClicked((MouseEvent event) -> {
	        	
	        	itemcliked_adh=true;
	        	
	        	gradeOuFilliere.setVisible(true);
	        	anneeinscritOuDepartement.setVisible(true);
	        	
	            String selected= (String) ListAdh.getSelectionModel().getSelectedItem();
	            Adherent a = bean.getAdherentParLogin(selected);
	            if(a instanceof Enseignant)
	            {
	            	type_adh.setValue("Enseignant");
	            	gradeOuFilliere.setText(((Enseignant) a).getGrade());
	            	anneeinscritOuDepartement.setText(((Enseignant) a).getDepartement());
	           
	            	Grade_adh.setVisible(true);
	            	dept_adh.setVisible(true);
	            	Filliere_adh.setVisible(false);
	            	annee_ins_adh.setVisible(false);
	            	anneeinscritOuDepartement.setDisable(false);
	            }
	            else
	            {
	            	type_adh.setValue("Etudiant");
	            	gradeOuFilliere.setText(((Etudiant)a).getFiliere());
	            	anneeinscritOuDepartement.setText(((Etudiant)a).getAnnee_inscrit());
	            	Filliere_adh.setVisible(true);
	            	annee_ins_adh.setVisible(true);
	            	Grade_adh.setVisible(false);
	            	dept_adh.setVisible(false);
	            	anneeinscritOuDepartement.setDisable(true);
	            }
	            
	            if(a.getBlacklisted())
	            {
	            	Blacklisted_adh.setValue("Oui");
	            }
	            else
	            {
	            	Blacklisted_adh.setValue("Non");
	            }
	            
	            cin_adh.setDisable(true);
	            type_adh.setDisable(true);
	            Blacklisted_adh.setDisable(false);
	            Login_adh.setDisable(true);
	            nbr_emprEnc_adh.setDisable(true);
	            email_adh.setDisable(true);
	            
	            
	            Login_adh.setText(a.getLogin());
	            Nom_adh.setText(a.getNom());
	            prenom_adh.setText(a.getPrenom());
	            email_adh.setText(a.getEmail());
	            tlf_adh.setText(a.getTelephone());
	            cin_adh.setText(a.getCin());
	            adresse_adh.setText(a.getAdresse());
	            nbr_emprEnc_adh.setText(Integer.toString(a.getNb_empr_enc()));
	            System.out.println(a.toString());
	           
	        });
	
	        List<String> lpm=bean.Liste_Auteurs().stream().map(x->x.getId_aut()+","+x.getNom_aut()+" "+x.getPrenom_aut()).collect(Collectors.toList());
	        if(lpm.size()>0)
	        {ObservableList<String> auts = FXCollections.observableArrayList();
	        list_aut.setItems(auts);
	        for(int i=0;i<lpm.size();i++)
	        {
	        auts.add(lpm.get(i));
	        }
	        }
	        list_aut.setOnMouseClicked((MouseEvent event) -> {
	            
	            autItemSelected=true;
	             String selected= (String) list_aut.getSelectionModel().getSelectedItem();
	             String[] sa= selected.split(",");
	             int id_aut=Integer.parseInt(sa[0]);
	            Auteur a=bean.getAuteur(id_aut);
	            Nom_aut.setText(a.getNom_aut());
	            prenom_aut.setText(a.getPrenom_aut());
	           
	        });
	        
	        List<String> lpc= bean.Liste_Categories().stream().map(x-> x.getId_cat()+","+x.getLibelle()).collect(Collectors.toList());
	        if(lpc.size()>0)
	        {
	        ObservableList<String> cats = FXCollections.observableArrayList();
	        list_cat.setItems(cats);
	        for(int i=0;i<lpc.size();i++)
	        {
	        cats.add(lpc.get(i));
	        }
	        }
	        list_cat.setOnMouseClicked((MouseEvent event) -> {
	            
	            catItemSelected=true;
	             String selected= (String) list_cat.getSelectionModel().getSelectedItem();
	             String[] sa= selected.split(",");
	            lib_cat.setText(sa[1]);
	            
	        });
	        
	        // liste Oeuvres
	        
	        
	        List<String> lo= bean.Liste_Oeuvres().stream().map(x->x.getId_Oeuv()+","+x.getTitre()).collect(Collectors.toList());
	        if(lo.size()>0)
	        {
	        ObservableList<String> os = FXCollections.observableArrayList();
	        list_oeuv.setItems(os);
	        for(int i=0;i<lo.size();i++)
	        {
	        os.add(lo.get(i));
	        }
	        }
	        
	        type_oeuv.getItems().addAll("SupportMultimedia","SupportPapier");
	        List<String> loa=bean.Liste_Auteurs().stream().map(x-> String.valueOf(x.getId_aut())).collect(Collectors.toList());
	        if(loa.size()>0)
	        combo_idaut_oeuv.getItems().addAll(loa);
	        List<String> loc=bean.Liste_Categories().stream().map(x-> x.getId_cat()+","+x.getLibelle()).collect(Collectors.toList());
	        if(loc.size()>0)
	        combo_categ_ouev.getItems().addAll(loc);
	        list_oeuv.setOnMouseClicked((MouseEvent event) -> {
	            
	        	combo_idaut_oeuv.setDisable(true);
	        	combo_categ_ouev.setDisable(true);
	        	type_oeuv.setDisable(true);
	            oeuvItemSelected=true;
	             String selected= (String) list_oeuv.getSelectionModel().getSelectedItem();
	             String[] sa= selected.split(",");
	             int id_Oeuv= Integer.parseInt(sa[0]);
	            Oeuvre o= bean.getOeuvre(id_Oeuv);
	            tit_ouev.setText(o.getTitre());
	            nbr_supp_ouev.setText(o.getNB_support()+"");
	            if(o instanceof SupportMultimedia)
	            {
	            	type_oeuv.getSelectionModel().select(0);
	            }else 
	            	type_oeuv.getSelectionModel().select(1);
	            
	            combo_idaut_oeuv.setValue(o.getAuteur().getId_aut()+"");
	            combo_categ_ouev.setValue(o.getCategorie().getId_cat()+","+o.getCategorie().getLibelle());
	        });
	        
	        List<String> ll= bean.Liste_Livres().stream().map(x->x.getId()+","+x.getOeuvre().getTitre()).collect(Collectors.toList());
	        if(ll.size()>0)
	        {
	        ObservableList<String> os = FXCollections.observableArrayList();
	        list_liv.setItems(os);
	        for(int i=0;i<ll.size();i++)
	        {
	        os.add(ll.get(i));
	        }
	        }
	        List<String> lcat=bean.Liste_Oeuvres().stream().map(x->x.getId_Oeuv()+", "+x.getTitre()).collect(Collectors.toList());
	        combo_Ouev_Liv.getItems().addAll(lcat);
	        dispo_liv.getItems().addAll("Oui","Non");
	        
	        list_liv.setOnMouseClicked((MouseEvent event) -> {
	            
	        	livItemSelected=true;
 	        	combo_Ouev_Liv.setDisable(true);
	             String selected= (String) list_liv.getSelectionModel().getSelectedItem();
	             String[] sa= selected.split(",");
	             int id_liv= Integer.parseInt(sa[0]);
	            Livre l= bean.getLivre(id_liv);
	            Oeuvre o= l.getOeuvre();
	            combo_Ouev_Liv.setValue(o.getId_Oeuv()+", "+o.getTitre());
	            if(l.getDispo())
	            	dispo_liv.getSelectionModel().select(0);
	            else
	            	dispo_liv.getSelectionModel().select(1);
	            LocalDate l1 = LocalDate.parse(l.getDate_edition().toString());
	            date_edt_liv.setValue(l1);
	        });
	        
	      
	        // tableview
	        
	        ObservableList<Emprunt> Emprlist= FXCollections.observableArrayList();
	        try {
	        	List<Emprunt> le= bean.Liste_Emprunts();
	        	for(Emprunt e:le)
	        	{
	        		Emprlist.add(e);
	        	}
	        }catch(Exception e)
	        {
	        	System.out.println(e.getMessage());
	        }
	       Col_idadh.setCellValueFactory(new PropertyValueFactory<>("id_Adh"));
	   	 	Col_idliv.setCellValueFactory(new PropertyValueFactory<>("id_Liv"));
	   	 	Col_Dempr.setCellValueFactory(new PropertyValueFactory<>("Date_empr"));
	   	 	Col_DretTheo.setCellValueFactory(new PropertyValueFactory<>("Date_Ret_Theo"));
	   	 	Col_DretEFC.setCellValueFactory(new PropertyValueFactory<>("Date_Ret_efc"));
	   	 	Col_nbrAvert.setCellValueFactory(new PropertyValueFactory<>("Nbr_avert"));
	        
	        
	        table_empr.setItems(Emprlist);
	        
	}
	
	
	
}
