package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Administrateur;
import fr.treeptik.service.AdministrateurService;

@ManagedBean
@SessionScoped
public class AdministrateurController {
	
	private Administrateur administrateur = new Administrateur();

	@EJB
	private AdministrateurService administrateurService;
	
	private List<Administrateur> listeAdministrateurs = new ArrayList<Administrateur>();
	// DataModel
	@SuppressWarnings("rawtypes")
	private DataModel administrateurs;
	private int nbAdministrateurs;
	
	public String prepareCreate(){
		administrateur = new Administrateur();
		return "createAdmin";
	}
	public String doCreate() throws ServiceException{
		if(administrateur.getId()!=null){
			administrateurService.update(administrateur);
		}else{
			administrateurService.add(administrateur);
		}
		
		return "succesCreation";
	}
	
	public int getNbAdministrateurs(){
		nbAdministrateurs = administrateurs.getRowCount();
		return nbAdministrateurs;
	}
	@SuppressWarnings("rawtypes")
	public String doDelete() throws ServiceException{
		Administrateur admin = (Administrateur)administrateurs.getRowData();
		administrateurService.delete(admin.getId());
		administrateurs = new ListDataModel();
		administrateurs.setWrappedData(administrateurService.getAll());
		return "listAdmin";
	}
	
	public String doSelectUpdate(){
		administrateur = (Administrateur)administrateurs.getRowData();
		return "createAdmin";
	}
	@SuppressWarnings("rawtypes")
	public String doUpdate() throws ServiceException{
		administrateurService.add(administrateur);
		administrateurs = new ListDataModel();
		administrateurs.setWrappedData(administrateurService.getAll());
		return "messageUserUpdate";
	}
	public HttpServletRequest getRequest(){
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	public String qui(){
		System.out.println(getRequest().getUserPrincipal().toString());
		return getRequest().getUserPrincipal().toString();
	}
	public boolean isUserAdmin(){
		return this.getRequest().isUserInRole("ADMIN");
	}
	
	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}
	public List<Administrateur> getListeAdministrateurs() {
		return listeAdministrateurs;
	}
	public void setListeAdministrateurs(List<Administrateur> listeAdministrateurs) {
		this.listeAdministrateurs = listeAdministrateurs;
	}
	@SuppressWarnings("rawtypes")
	public DataModel getAdministrateurs(){
		if(administrateurs== null){
			administrateurs = new ListDataModel();
		}
			try {
				administrateurs.setWrappedData(administrateurService.getAll());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return administrateurs;
	}
	@SuppressWarnings("rawtypes")
	public void setAdministrateurs(DataModel administrateurs){
		this.administrateurs = administrateurs;
	}

	public void setNbAdministrateurs(int nbAdministrateurs) {
		this.nbAdministrateurs = nbAdministrateurs;
	}


	
}
