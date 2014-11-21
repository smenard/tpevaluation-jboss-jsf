package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.service.StagiaireService;
import fr.treeptik.utils.MailUtils;

@ManagedBean
@SessionScoped
public class StagiaireController {
	private Stagiaire stagiaire = new Stagiaire();
	@EJB
	private StagiaireService stagiaireService;
	@EJB
	private MailUtils mailUtils;
	private List<Stagiaire> listeStagiaire = new ArrayList<Stagiaire>();
	// DATAMODEL
	@SuppressWarnings("rawtypes")
	private DataModel stagiaires;
	public String prepareCreate(){
		stagiaire = new Stagiaire();
		return "createStagiaire";
	}
	public String doCreate() throws ServiceException{
		if(stagiaire.getId()!=null && stagiaire.getId()!=0){
			System.out.println("ID STAGIAIRE : "+stagiaire.getId()+" ***************************************");
			Stagiaire st = stagiaireService.get(stagiaire.getId());
			stagiaire.setPassword(st.getPassword());
			stagiaire.setDateInscription(st.getDateInscription());
			stagiaireService.update(stagiaire);
		}else{
			stagiaire.setId(null);
			stagiaire.setPassword(stagiaireService.generatePassword());
			stagiaire.setDateInscription(new Date());
			stagiaireService.add(stagiaire);
//			mailUtils.mailCreationUser(stagiaire);
		}
		
		return "successCreation";
	}
	@SuppressWarnings("rawtypes")
	public String doDelete() throws ServiceException{
		Stagiaire st = (Stagiaire)stagiaires.getRowData();
		stagiaireService.delete(st.getId());
		stagiaires = new ListDataModel();
		stagiaires.setWrappedData(stagiaireService.getAll());
		return "listStagiaire";
	}
	public String showPassword(){
		System.out.println(stagiaireService.generatePassword());
		return "accueil";
	}
	public String sendMail(){
		Stagiaire st = new Stagiaire();
		st.setEmail("bidon06081990@gmail.com");
		st.setPassword(stagiaireService.generatePassword());
		st.setNom("Menard");
		st.setPrenom("Samuel");
//		mailUtils.mailCreationUser(st);
		return "accueil";
	}
	public String doSelectUpdate(){
		stagiaire =(Stagiaire)stagiaires.getRowData();
		return "createStagiaire";
	}
	@SuppressWarnings("rawtypes")
	public String doUpdate() throws ServiceException{
		stagiaireService.add(stagiaire);
		stagiaires = new ListDataModel();
		stagiaires.setWrappedData(stagiaireService.getAll());
		return "messageStagiaireUpdate";
	}
	
	public Stagiaire getStagiaire() {
		return stagiaire;
	}
	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}
	public List<Stagiaire> getListeStagiaire() {
		return listeStagiaire;
	}
	public void setListeStagiaire(List<Stagiaire> listeStagiaire) {
		this.listeStagiaire = listeStagiaire;
	}
	@SuppressWarnings("rawtypes")
	public DataModel getStagiaires() {
		if(stagiaires == null){
			stagiaires = new ListDataModel();
		}
			try {
				stagiaires.setWrappedData(stagiaireService.getAll());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return stagiaires;
	}
	@SuppressWarnings("rawtypes")
	public void setStagiaires(DataModel stagiaires) {
		this.stagiaires = stagiaires;
	}

	
}
