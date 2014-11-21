package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Session;
import fr.treeptik.service.SessionService;

@ManagedBean
@SessionScoped
public class SessionController {
	
	private Session session = new Session();
	
	@EJB
	private SessionService sessionService;
	
	private List<Session> listSession = new ArrayList<Session>();
	
	@SuppressWarnings("rawtypes")
	private DataModel sessions;
	
	public String prepareCreate(){
		session = new Session();
		return "createSession";
	}
	public String doCreate() throws ServiceException{
		if(session.getId()!=null){
			sessionService.update(session);
		}else{
			sessionService.add(session);
		}
		
		return "successCreation";
	}
	@SuppressWarnings("rawtypes")
	public String doDelete() throws ServiceException{
		Session f = (Session)sessions.getRowData();
		sessionService.delete(f.getId());
		sessions = new ListDataModel();
		sessions.setWrappedData(sessionService.getAll());
		return "listAdmin";
	}
	public String doSelectUpdate(){
		session = (Session)sessions.getRowData();
		return "createSession";
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public List<Session> getListSession() {
		return listSession;
	}
	public void setListSession(List<Session> listSession) {
		this.listSession = listSession;
	}
	@SuppressWarnings("rawtypes")
	public DataModel getSessions() {
		if(sessions== null){
			sessions = new ListDataModel();
		}
			try {
				sessions.setWrappedData(sessionService.getAll());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return sessions;
	}
	@SuppressWarnings("rawtypes")
	public void setSessions(DataModel sessions) {
		this.sessions = sessions;
	}
	

}
