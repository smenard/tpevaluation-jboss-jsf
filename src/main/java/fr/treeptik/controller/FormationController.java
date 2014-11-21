package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Formation;
import fr.treeptik.service.FormationService;

@ManagedBean
@SessionScoped
public class FormationController {

	private Formation formation = new Formation();

	@EJB
	private FormationService formationService;

	private List<Formation> listFormation = new ArrayList<Formation>();

	@SuppressWarnings("rawtypes")
	private DataModel formations;

	public String prepareCreate() {
		formation = new Formation();
		return "createFormation";
	}

	public String doCreate() throws ServiceException {
		if (formation.getId() != null) {
			formationService.update(formation);
		} else {
			formationService.add(formation);
		}

		return "successCreation";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() throws ServiceException {
		Formation f = (Formation) formations.getRowData();
		formationService.delete(f.getId());
		formations = new ListDataModel();
		formations.setWrappedData(formationService.getAll());
		return "listAdmin";
	}

	public String doSelectUpdate() {
		formation = (Formation) formations.getRowData();
		return "createFormation";
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public List<Formation> getListFormation() {
		return listFormation;
	}

	public void setListFormation(List<Formation> listFormation) {
		this.listFormation = listFormation;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getFormations() {
		if (formations == null) {
			formations = new ListDataModel();
		}
		try {
			formations.setWrappedData(formationService.getAll());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formations;
	}

	@SuppressWarnings("rawtypes")
	public void setFormations(DataModel formations) {
		this.formations = formations;
	}
}
