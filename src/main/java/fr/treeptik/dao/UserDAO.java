package fr.treeptik.dao;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Administrateur;
import fr.treeptik.model.User;

public interface UserDAO extends GenericDAO<User, Integer>{

	User findByEmail(String email)throws DAOException;
}
