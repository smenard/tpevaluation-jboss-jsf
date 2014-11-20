package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.UserDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.User;
import fr.treeptik.service.UserService;
@Stateless
public class UserServiceImpl implements UserService{

	@EJB
	private UserDAO userDAO;
	@Override
	public void add(User user) throws ServiceException {
		try {
			userDAO.save(user);
		} catch (DAOException e) {
			throw new ServiceException(e,"UserServiceImpl add");
		}
	}

	@Override
	public void update(User user) throws ServiceException {
		try{
			userDAO.update(user);
		}catch(DAOException e){
			throw new ServiceException(e,"UserServiceImpl update");
		}
	}
	@Override
	public List<User> getAll() throws ServiceException {
		try {
			return userDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e, "UserServiceImpl getAll");
		}
	}

	@Override
	public User get(Integer id) throws ServiceException {
		try {
			return userDAO.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException(e,"UserServiceImpl get");
		}
	}

	@Override
	public void delete(Integer userId) throws ServiceException {
		try {
			userDAO.remove(get(userId));
		} catch (DAOException e) {
			throw new ServiceException(e, "UserServiceImpl delete");
		}
	}

	@Override
	public User findWithEmail(String email) throws ServiceException {
		try {
			return userDAO.findByEmail(email);
		} catch (DAOException e) {
			throw new ServiceException(e, "UserServiceImpl findWithEmail : "+email);
		}
	}


}
