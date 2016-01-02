package com.shippable.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.shippable.dto.Issue;
import com.shippable.dto.User;
import com.shippable.util.HibernateUtil;

@Repository
public class IssueDaoImpl implements IssueDao{

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction ;
	
	@Override
	public Issue saveIssue(Issue issue) {
		Long issueId = null;
		try{
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		transaction = session.getTransaction();
		transaction.begin();
		issueId = (Long)session.save(issue);
		transaction.commit();
		
		}catch(Exception e)
		{
			System.out.println("error "+e.getMessage());
			if(null != transaction)
			{
		       transaction.rollback();	
			}
		}
		issue.setIssueId(issueId);
		return issue;
	}

	@Override
	public Issue getIssue(Issue issue) {
		String hql = "from Issue i where i.issueId=:id ";
		Query query = null;
		Issue valideIssue = null;
		try{
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		transaction = session.getTransaction();
		transaction.begin();
        query = session.createQuery(hql);
        query.setParameter("id", issue.getIssueId());
        valideIssue = (Issue)query.uniqueResult();
		transaction.commit();
		
		}catch(Exception e)
		{
			System.out.println("error "+e.getMessage());
			if(null != transaction)
			{
		       transaction.rollback();	
			}
		}
		return valideIssue;

	}

	@Override
	public Issue updateIssue(Issue issue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteIssue(Issue issue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Issue> getAllIssues() {
		String hql = "from Issue";
		Query query = null;
		List<Issue> issuesList = null;
		try{
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		transaction = session.getTransaction();
		transaction.begin();
        query = session.createQuery(hql);
        issuesList = query.list();
		transaction.commit();
		
		}catch(Exception e)
		{
			System.out.println("error "+e.getMessage());
			if(null != transaction)
			{
		       transaction.rollback();	
			}
		}
		return issuesList;
	}

	@Override
	public List<Issue> getSortedOpenIssues(String hql,Date fromDate, Date toDate){

		
		Query query = null;
		List<Issue> issuesList = null;
		try{
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		transaction = session.getTransaction();
		transaction.begin();
        query = session.createQuery(hql);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        issuesList = query.list();
		transaction.commit();
		
		}catch(Exception e)
		{
			System.out.println("error "+e.getMessage());
			if(null != transaction)
			{
		       transaction.rollback();	
			}
		}
		return issuesList;

	}

}
