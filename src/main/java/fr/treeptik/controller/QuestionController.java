package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Question;
import fr.treeptik.service.QuestionService;

@ManagedBean
@SessionScoped
public class QuestionController {

	private Question question = new Question();

	@EJB
	private QuestionService questionService;

	private List<Question> listQuestion = new ArrayList<Question>();

	@SuppressWarnings("rawtypes")
	private DataModel questions;

	public String prepareCreate() {
		question = new Question();
		return "createQuestion";
	}

	public String doCreate() throws ServiceException {
		if (question.getId() != null) {
			questionService.update(question);
		} else {
			questionService.add(question);
		}

		return "successCreation";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() throws ServiceException {
		Question f = (Question) questions.getRowData();
		questionService.delete(f.getId());
		questions = new ListDataModel();
		questions.setWrappedData(questionService.getAll());
		return "listQuestion";
	}

	public String doSelectUpdate() {
		question = (Question) questions.getRowData();
		return "createQuestion";
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Question> getListQuestion() {
		return listQuestion;
	}

	public void setListQuestion(List<Question> listQuestion) {
		this.listQuestion = listQuestion;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getQuestions() {
		if (questions == null) {
			questions = new ListDataModel();
		}
		try {
			questions.setWrappedData(questionService.getAll());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questions;
	}

	@SuppressWarnings("rawtypes")
	public void setQuestions(DataModel questions) {
		this.questions = questions;
	}
}
