package fr.treeptik.dao.impl;

import javax.ejb.Stateless;

import fr.treeptik.dao.FormationDAO;
import fr.treeptik.model.Formation;
@Stateless
public class FormationJPADAO extends GenericJPADAO<Formation, Integer> implements FormationDAO{

	public FormationJPADAO() {
		super(Formation.class);
		// TODO Auto-generated constructor stub
	}

}
