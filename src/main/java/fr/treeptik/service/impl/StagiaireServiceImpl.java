package fr.treeptik.service.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.StagiaireDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.service.StagiaireService;

@Stateless
public class StagiaireServiceImpl implements StagiaireService{

	private SecureRandom random = new SecureRandom();
	@EJB
	private StagiaireDAO stagiaireDAO;
	@Override
	public void add(Stagiaire stagiaire) throws ServiceException {
		try {
			stagiaire.setRole("USER");
			stagiaireDAO.save(stagiaire);
		} catch (DAOException e) {
			throw new ServiceException(e, "StagiaireServiceImpl add");
		}
	}

	@Override
	public void update(Stagiaire stagiaire) throws ServiceException {
		try{
			stagiaire.setRole("USER");
			stagiaireDAO.update(stagiaire);
		}catch(DAOException e){
			throw new ServiceException(e,"StagiaireServiceImpl update");
		}
	}
	@Override
	public List<Stagiaire> getAll() throws ServiceException {
		try {
			return stagiaireDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e, "StagiaireServiceImpl getAll");
		}
	}

	@Override
	public Stagiaire get(Integer id) throws ServiceException {
		try {
			return stagiaireDAO.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException(e,"StagiaireServiceImpl get");
		}
	}

	@Override
	public void delete(Integer stagiaireId) throws ServiceException {
		try {
			stagiaireDAO.remove(get(stagiaireId));
		} catch (DAOException e) {
			throw new ServiceException(e, "StagiaireServiceImpl delete");
		}
	}

	@Override
	public String generatePassword() {
		return new BigInteger(130, random).toString(32).substring(0, 10);
	}

	@Override
	public Stagiaire findWithEmail(String email) throws ServiceException {
		try {
			return stagiaireDAO.findByEmail(email);
		} catch (DAOException e) {
			throw new ServiceException(e, "StagiaireServiceImpl findWithEmail : "+email);
		}
	}




}
