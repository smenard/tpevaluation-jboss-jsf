package fr.treeptik.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import fr.treeptik.dao.AdministrateurDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Administrateur;
import fr.treeptik.model.Stagiaire;

@Stateless
public class AdministrateurJPADAO extends GenericJPADAO<Administrateur, Integer> implements AdministrateurDAO{

	public AdministrateurJPADAO() {
		super(Administrateur.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Administrateur findByEmail(String email) throws DAOException {
		Administrateur administrateur = null;
		try{
			String queryString = "select distinct c from User c where c.email=:email";
			TypedQuery<Administrateur> query = em.createQuery(
					queryString, Administrateur.class).setParameter("email", email);
			if(query.getResultList().size()==0){
				return null;
			}
			administrateur = query.getSingleResult();
		}catch(PersistenceException e){
			throw new DAOException(e, "Erreur findByEmail : "+email);
		}
		
		return administrateur;
	}

}
