package fr.treeptik.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import fr.treeptik.dao.GenericDAO;
import fr.treeptik.exception.DAOException;

public abstract class GenericJPADAO<T, PK> implements GenericDAO<T, PK> {

	private Class<T> type;
	@PersistenceContext(unitName = "evaluation")
	protected EntityManager em;
	
	public GenericJPADAO(Class<T> type) {
		this.type = type;
	}

	@Override
	public T save(T entite) throws DAOException  {
		try{
			em.persist(entite);
		}catch(PersistenceException e){
			throw new DAOException(e, "Erreur save:"+entite);
		}
		
		return entite;
	}
	@Override
	public T update(T entite) throws DAOException {
		try{
			em.merge(entite);
		}catch(PersistenceException e){
			throw new DAOException(e, "Erreur update");
		}
		return entite;
	}

	@Override
	public void remove(T entite) throws DAOException {
		try{
			em.remove(entite);
		}catch(PersistenceException e){
			throw new DAOException(e, "Erreur remove");
		}
		

	}

	@Override
	public List<T> findAll() throws DAOException {
		List<T> liste = new ArrayList<T>();
		try{
			TypedQuery<T> query = em.createQuery("select c from "
					+ type.getSimpleName() + " c ", type);
			liste = query.getResultList();
		}catch(PersistenceException e){
			throw new DAOException(e, "Erreur FindAll");
		}
		return liste;
	}

	@Override
	public Integer count() throws DAOException {
		List<Long> liste = new ArrayList<Long>();
		try{
			TypedQuery<Long> query = em.createQuery(
					"select count(n) from "+type.getSimpleName()+" n", Long.class);
			liste = query.getResultList();
		}catch(PersistenceException e){
			throw new DAOException(e, "Erreur count ");
		}
		
		return liste.get(0).intValue();
	}

	@Override
	public T findByPk(PK id) throws DAOException {
		T c = null;
		try{
//			System.out.println(type.getSimpleName());
			String queryString = "select distinct c from "+type.getSimpleName()+" c where c.id=:id";
//			System.out.println(queryString);
			TypedQuery<T> query = em.createQuery(
					queryString, type).setParameter("id", id);
			if(query.getResultList().size()==0){
				return null;
			}
			c = query.getSingleResult();
		}catch(PersistenceException e){
			throw new DAOException(e, "Erreur findByPk");
		}
		
		return c;
	}

	@Override
	public List<T> findWithParams(Map<String, Object> params)
			throws DAOException {
		List<T> liste = new ArrayList<T>();
		try{
			StringBuilder requete = new StringBuilder("SELECT distinct c FROM "+type.getSimpleName()+" c ");
			if(!params.isEmpty()){
				requete.append("WHERE ");
				Set<String> set = params.keySet();
				for (String key : set) {
					requete.append(key).append("=").append("'").append(params.get(key)).append("'").append(" AND ");
				}
				// on supprime le dernier AND
				int lastAnd = requete.lastIndexOf("AND");
				requete.delete(lastAnd, lastAnd + 3);
			}
			TypedQuery<T> query = em.createQuery(
					requete.toString(), type);
			liste = query.getResultList();
		}catch(PersistenceException e){
			throw new DAOException(e, "findWithParams");
		}
		
		return liste;
	}

}
