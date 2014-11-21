package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Evaluation;
import fr.treeptik.service.EvaluationService;

@ManagedBean
@SessionScoped
public class EvaluationController {
	private Evaluation evaluation = new Evaluation();

	@EJB
	private EvaluationService evaluationService;

	private List<Evaluation> listEvaluation = new ArrayList<Evaluation>();

	@SuppressWarnings("rawtypes")
	private DataModel evaluations;

	public String prepareCreate() {
		evaluation = new Evaluation();
		return "createEvaluation";
	}

	public String doCreate() throws ServiceException {
		if (evaluation.getId() != null) {
			evaluationService.update(evaluation);
		} else {
			evaluationService.add(evaluation);
		}

		return "successCreation";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() throws ServiceException {
		Evaluation f = (Evaluation) evaluations.getRowData();
		evaluationService.delete(f.getId());
		evaluations = new ListDataModel();
		evaluations.setWrappedData(evaluationService.getAll());
		return "listEvaluation";
	}

	public String doSelectUpdate() {
		evaluation = (Evaluation) evaluations.getRowData();
		return "createEvaluation";
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public List<Evaluation> getListEvaluation() {
		return listEvaluation;
	}

	public void setListEvaluation(List<Evaluation> listEvaluation) {
		this.listEvaluation = listEvaluation;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getEvaluations() {
		if (evaluations == null) {
			evaluations = new ListDataModel();
		}
		try {
			evaluations.setWrappedData(evaluationService.getAll());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return evaluations;
	}

	@SuppressWarnings("rawtypes")
	public void setEvaluations(DataModel evaluations) {
		this.evaluations = evaluations;
	}
}
