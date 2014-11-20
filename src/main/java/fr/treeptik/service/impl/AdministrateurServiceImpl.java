package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.AdministrateurDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Administrateur;
import fr.treeptik.service.AdministrateurService;

@Stateless
public class AdministrateurServiceImpl implements AdministrateurService{

	@EJB
	private AdministrateurDAO administrateurDAO;
	@Override
	public void add(Administrateur administrateur) throws ServiceException {
		try {
			administrateur.setRole("ADMIN");
			administrateurDAO.save(administrateur);
		} catch (DAOException e) {
			throw new ServiceException(e,"AdministrateurServiceImpl add");
		}
	}

	@Override
	public void update(Administrateur administrateur) throws ServiceException {
		try{
			administrateur.setRole("ADMIN");
			administrateurDAO.update(administrateur);
		}catch(DAOException e){
			throw new ServiceException(e,"AdministrateurServiceImpl update");
		}
	}
	@Override
	public List<Administrateur> getAll() throws ServiceException {
		try {
			return administrateurDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e, "AdministrateurServiceImpl getAll");
		}
	}

	@Override
	public Administrateur get(Integer id) throws ServiceException {
		try {
			return administrateurDAO.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException(e, "AdministrateurServiceImpl get");
		}
	}

	@Override
	public void delete(Integer administrateurId) throws ServiceException {
		try {
			administrateurDAO.remove(get(administrateurId));
		} catch (DAOException e) {
			throw new ServiceException(e, "AdministrateurServiceImpl delete");
		}
	}

	@Override
	public Administrateur findWithEmail(String email) throws ServiceException {
		
		try {
			return administrateurDAO.findByEmail(email);
		} catch (DAOException e) {
			throw new ServiceException(e, "AdministrateurServiceImpl findWithEmail : "+email);
		}
	}


}
