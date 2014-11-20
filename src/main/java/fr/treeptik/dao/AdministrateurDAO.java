package fr.treeptik.dao;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Administrateur;

public interface AdministrateurDAO extends GenericDAO<Administrateur, Integer>{

	Administrateur findByEmail(String email)throws DAOException;
}
