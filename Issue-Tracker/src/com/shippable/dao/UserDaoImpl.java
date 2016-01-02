package com.shippable.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.shippable.dto.User;
import com.shippable.util.HibernateUtil;




/**
 * @author Soori Babu Meesala
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction ;
	
	@Override
	public User saveUser(User user) {
		Long userId = null;
		try{
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		transaction = session.getTransaction();
		transaction.begin();
		userId = (Long)session.save(user);
		transaction.commit();
		
		}catch(Exception e)
		{
			System.out.println("error "+e.getMessage());
			if(null != transaction)
			{
		       transaction.rollback();	
			}
		}
		user.setUserId(userId);
		return user;
	}

	@Override
	public User getUser(User user) {
		String hql = "select u from User u where u.emailId=:email and password=:pwd";
		Query query = null;
		User validUser = null;
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			transaction = session.getTransaction();
			transaction.begin();
			query = session.createQuery(hql);
			query.setParameter("email", user.getEmailId());
			query.setParameter("pwd", user.getPassword());
			validUser = (User)query.uniqueResult();
			transaction.commit();
			}catch(Exception e)
			{
				System.out.println("error "+e.getMessage());
				if(null != transaction)
				{
			       transaction.rollback();	
				}
			}

		return validUser;		
	}

	@Override
	public User updateUser(User user) {
	
		try{
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		transaction = session.getTransaction();
		transaction.begin();
		session.saveOrUpdate(user);
		transaction.commit();
		
		}catch(Exception e)
		{
			System.out.println("error "+e.getMessage());
			if(null != transaction)
			{
		       transaction.rollback();	
			}
			return null;
		}
		return user;

	}

	@Override
	public Boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
