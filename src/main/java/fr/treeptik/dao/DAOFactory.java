package fr.treeptik.dao;

import fr.treeptik.dao.impl.AdministrateurJPADAO;
import fr.treeptik.dao.impl.StagiaireJPADAO;
import fr.treeptik.dao.impl.UserJPADAO;

public abstract class DAOFactory {
	
	public static AdministrateurDAO getAdministrateurDAO(){
		return new AdministrateurJPADAO();
	}
	public static StagiaireDAO getStagiaireDAO(){
		return new StagiaireJPADAO();
	}
	public static UserDAO getUserDAO(){
		return new UserJPADAO();
	}

}
