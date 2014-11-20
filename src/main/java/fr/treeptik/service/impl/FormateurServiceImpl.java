package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.FormateurDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Formateur;
import fr.treeptik.service.FormateurService;

@Stateless
public class FormateurServiceImpl implements FormateurService{

	@EJB
	private FormateurDAO formateurDAO;
	@Override
	public void add(Formateur formateur) throws ServiceException {
		try{
			formateurDAO.save(formateur);
		}catch(DAOException e){
			throw new ServiceException(e, "FormateurService Impl add : "+formateur);
		}
		
	}

	@Override
	public void update(Formateur formateur) throws ServiceException {
		try{
			formateurDAO.update(formateur);
		}catch(DAOException e){
			throw new ServiceException(e, "FormateurServiceImpl update : "+formateur);
		}
	}

	@Override
	public List<Formateur> getAll() throws ServiceException {
		try{
			return formateurDAO.findAll();
		}catch(DAOException e){
			throw new ServiceException(e, "FormateurServiceImpl getAll");
		}
		
	}

	@Override
	public Formateur get(Integer id) throws ServiceException {
		try{
			return formateurDAO.findByPk(id);
		}catch(DAOException e){
			throw new ServiceException(e, "FormateurServiceImpl get : "+id);
		}
		
	}

	@Override
	public void delete(Integer formateurId) throws ServiceException {
		try{
			formateurDAO.remove(get(formateurId));
		}catch(DAOException e){
			throw new ServiceException(e, "FormateurServiceImpl delete :"+formateurId);
		}
	}

}
