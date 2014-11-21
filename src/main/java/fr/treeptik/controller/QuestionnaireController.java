package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Questionnaire;
import fr.treeptik.service.QuestionnaireService;
@ManagedBean
@SessionScoped
public class QuestionnaireController {
	private Questionnaire questionnaire = new Questionnaire();

	@EJB
	private QuestionnaireService questionnaireService;

	private List<Questionnaire> listQuestionnaire = new ArrayList<Questionnaire>();

	@SuppressWarnings("rawtypes")
	private DataModel questionnaires;

	public String prepareCreate() {
		questionnaire = new Questionnaire();
		return "createQuestionnaire";
	}

	public String doCreate() throws ServiceException {
		if (questionnaire.getId() != null) {
			questionnaireService.update(questionnaire);
		} else {
			questionnaireService.add(questionnaire);
		}

		return "successCreation";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() throws ServiceException {
		Questionnaire f = (Questionnaire) questionnaires.getRowData();
		questionnaireService.delete(f.getId());
		questionnaires = new ListDataModel();
		questionnaires.setWrappedData(questionnaireService.getAll());
		return "listQuestionnaire";
	}

	public String doSelectUpdate() {
		questionnaire = (Questionnaire) questionnaires.getRowData();
		return "createQuestionnaire";
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public List<Questionnaire> getListQuestionnaire() {
		return listQuestionnaire;
	}

	public void setListQuestionnaire(List<Questionnaire> listQuestionnaire) {
		this.listQuestionnaire = listQuestionnaire;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getQuestionnaires() {
		if (questionnaires == null) {
			questionnaires = new ListDataModel();
		}
		try {
			questionnaires.setWrappedData(questionnaireService.getAll());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionnaires;
	}

	@SuppressWarnings("rawtypes")
	public void setQuestionnaires(DataModel questionnaires) {
		this.questionnaires = questionnaires;
	}
}
