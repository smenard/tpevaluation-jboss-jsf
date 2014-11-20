package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.QuestionnaireDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Questionnaire;
import fr.treeptik.service.QuestionnaireService;

@Stateless
public class QuestionnaireServiceImpl implements QuestionnaireService{

	@EJB
	private QuestionnaireDAO questionnaireDAO;
	@Override
	public void add(Questionnaire questionnaire) throws ServiceException {
		try{
			questionnaireDAO.save(questionnaire);
		}catch(DAOException e){
			throw new ServiceException(e, "QuestionnaireServiceImpl add : "+questionnaire);
		}
	}

	@Override
	public void update(Questionnaire questionnaire) throws ServiceException {
		try{
			questionnaireDAO.update(questionnaire);
		}catch(DAOException e){
			throw new ServiceException(e, "QuestionnaireServiceImpl update : "+questionnaire);
		}
	}

	@Override
	public List<Questionnaire> getAll() throws ServiceException {
		try{
			return questionnaireDAO.findAll();
		}catch(DAOException e){
			throw new ServiceException(e, "QuestionnaireServiceImpl getAll");
		}
		
	}

	@Override
	public Questionnaire get(Integer id) throws ServiceException {
		try {
			return questionnaireDAO.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException(e, "QuestionnaireServiceImpl get : "+id);
		}
	}

	@Override
	public void delete(Integer questionnaireId) throws ServiceException {
		try{
			questionnaireDAO.remove(get(questionnaireId));
		}catch(DAOException e){
			throw new ServiceException(e,"QuestionnaireServiceImpl delete : "+questionnaireId);
		}
	}

}
