package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.FormationDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Formation;
import fr.treeptik.service.FormationService;
@Stateless
public class FormationServiceImpl implements FormationService{
	
	@EJB
	private FormationDAO formationDAO;

	@Override
	public void add(Formation formation) throws ServiceException {
		try {
			formationDAO.save(formation);
		} catch (DAOException e) {
			throw new ServiceException(e, "FormationServiceImpl add : "+formation);
		}
	}

	@Override
	public void update(Formation formation) throws ServiceException {
		try {
			formationDAO.update(formation);
		} catch (DAOException e) {
			throw new ServiceException(e,"FormationServiceImpl update : "+formation);
		}
	}

	@Override
	public List<Formation> getAll() throws ServiceException {
		try {
			return formationDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e, "FormationServiceImpl getAll");
		}
	}

	@Override
	public Formation get(Integer id) throws ServiceException {
		try {
			return formationDAO.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException(e, "FormationServiceImpl get : "+id);
		}
	}

	@Override
	public void delete(Integer formationId) throws ServiceException {
		try {
			formationDAO.remove(get(formationId));
		} catch (DAOException e) {
			throw new ServiceException(e, "FormationServiceImpl delete");
		}
	}

}
