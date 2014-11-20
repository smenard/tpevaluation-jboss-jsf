package fr.treeptik.dao;

import java.util.List;
import java.util.Map;

import fr.treeptik.exception.DAOException;

public interface GenericDAO<T,PK>  {
	// toutes les méthodes sont public et abstract dans une interface
		T save(T entite)throws DAOException;
		T update(T entite)throws DAOException;
		void remove(T entite)throws DAOException;
		List<T> findAll()throws DAOException;
		Integer count()throws DAOException;
		T findByPk(PK id)throws DAOException;
		List<T> findWithParams(Map<String,Object> params) throws DAOException; // <String, Object> : nom de la colonne, valeur
}
