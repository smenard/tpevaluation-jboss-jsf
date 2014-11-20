package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import fr.treeptik.model.User;
import fr.treeptik.service.UserService;

@ManagedBean
@SessionScoped
public class UserController {
	
	private User user;
	
	@EJB
	private UserService userService;
	
	private List<User> listUsers = new ArrayList<User>();
	
	
	
	public String logout() {
//	    ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
//	         .getSession(true)).invalidate();
	    getRequest().getSession().invalidate();
	     return "logout";
	}
	
	public boolean isAdmin(){
		boolean isAdmin =getRequest().isUserInRole("ADMIN");
		return isAdmin;
	}
	public HttpServletRequest getRequest(){
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	public String qui() {
		System.out.println(getRequest().getUserPrincipal().toString());
		return getRequest().getUserPrincipal().toString();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	

}
