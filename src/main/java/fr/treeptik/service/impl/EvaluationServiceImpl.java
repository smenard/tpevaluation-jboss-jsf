package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.EvaluationDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Evaluation;
import fr.treeptik.service.EvaluationService;

@Stateless
public class EvaluationServiceImpl implements EvaluationService{

	@EJB
	private EvaluationDAO evaluationDAO;
	@Override
	public void add(Evaluation evaluation) throws ServiceException {
		try{
			evaluationDAO.save(evaluation);
		}catch(DAOException e){
			throw new ServiceException(e,"EvaluationService Impl save :"+evaluation);
		}
		
	}

	@Override
	public void update(Evaluation evaluation) throws ServiceException {
		try{
			evaluationDAO.update(evaluation);
		}catch(DAOException e){
			throw new ServiceException(e,"EvaluationServiceImpl update : "+evaluation);
		}
		
	}

	@Override
	public List<Evaluation> getAll() throws ServiceException {
		try{
			return evaluationDAO.findAll();
		}catch(DAOException e){
			throw new ServiceException(e,"EvaluationServiceImpl getAll");
		}
		
	}

	@Override
	public Evaluation get(Integer id) throws ServiceException {
		try{
			return evaluationDAO.findByPk(id);
		}catch(DAOException e){
			throw new ServiceException(e, "EvaluationServiceImpl get : "+id);
		}
		
	}

	@Override
	public void delete(Integer evaluationId) throws ServiceException {
		try{
			evaluationDAO.remove(get(evaluationId));
		}catch(DAOException e){
			throw new ServiceException(e, "EvaluationServiceImpl delete : "+evaluationId);
		}
	}

}
