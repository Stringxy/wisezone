package com.web.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.dao.CommonSearchDao;
import com.web.util.PageUtil;

@Repository
public class CommonSearchDaoImpl<T> implements CommonSearchDao<T> {

	
	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<T> searchHql(String hql, Map<String, Object> params) {

		return this.searchHql(hql, params, null);
	}

	@Override
	public List<T> searchHql(String hql, Map<String, Object> params,
			PageUtil<T> paging) {
		Session session = null;
		Query query = null;
		List list = null;

		try {
			session = sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			if (params != null) {
				query=query.setProperties(params);
			}
			if (paging != null) {
				query.setFirstResult(paging.getBegin());
				query.setMaxResults(paging.getEnd());
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public Object searchUnique(String hql, Map<String, Object> params) {
		Object obj = null;
		Session session = null;
		Query query = null;

		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			if (params != null) {
				query=query.setProperties(params);
			}
			obj = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj;
	}

	public int searchCount(String hql, Map<String, Object> params) {
		Object obj = searchUnique(hql, params);
		if (obj != null) {
			return ((Long) obj).intValue();
		}
		return 0;
	}

	@Override
	public int hqlExecuteUpdate(String hql, Serializable... serializables) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < serializables.length; i++) {
			query.setParameter(i, serializables[i]);
		}
		return query.executeUpdate();
	}

	@Override
	public int hqlExecuteUpdate(String hql, List<Serializable> params) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i, params.get(i));
		}
		return query.executeUpdate();
	}

	@Override
	public int sqlExecuteUpdate(String sql, Serializable... serializables) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < serializables.length; i++) {
			query.setParameter(i, serializables[i]);
		}
		return query.executeUpdate();
	}

	@Override
	public int sqlExecuteUpdate(String sql, Map<String, Object> params) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if (params != null) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.executeUpdate();
	}

	@Override
	public List sqlQuery(String sql, Map<String, Object> params) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.list();
	}

	@Override
	public List sqlQuery(String sql, Map<String, Object> params,
			PageUtil<T> paging, Class<T> entityClass) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(entityClass);
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}

		query.setFirstResult(paging.getBegin());
		query.setMaxResults(paging.getEnd());

		return query.list();
	}

	@Override
	public Object sqlUnique(String sql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object sqlUnique(String sql, Serializable... params) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.uniqueResult();
	}

	@Override
	public List<T> criteriaSearch(Class<T> classEntity, List<Criterion> filter) {
		Criteria query = sessionFactory.getCurrentSession().createCriteria(
				classEntity);


		if (filter != null) {
			for (Criterion c : filter) {
                query.add(c);
			} 
		}
		
		return query.list();
	}
}
