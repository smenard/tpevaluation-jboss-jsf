package fr.treeptik.service;

import java.util.List;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Formation;

public interface FormationService {
	public void add(Formation formation)throws ServiceException;
	public void update(Formation formation)throws ServiceException;
    public List<Formation> getAll()throws ServiceException;
    public Formation get(Integer id)throws ServiceException;
    public void delete(Integer formationId)throws ServiceException;
}
