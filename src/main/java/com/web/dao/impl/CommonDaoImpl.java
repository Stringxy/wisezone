package com.web.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.dao.CommonDao;
import com.web.dao.CommonSearchDao;
import com.web.util.PageUtil;



/**
 * 公共的数据访问层实现类
 * @author xy
 *
 * @param <T>
 */
public abstract class CommonDaoImpl<T> implements CommonDao<T> {
	@Autowired//自动查找的注解
	protected SessionFactory sessionFactory;
	
	@Resource(name = "commonSearchDaoImpl")
	public CommonSearchDao<T> commonSearch;

	private Class<T> entity = null;
	
	/**
	 * 构造函数
	 */
	public CommonDaoImpl(){
		//package com.web.dao.impl;
		//public class RwAdminDaoImpl extends CommonDaoImpl<RwAdmin>
		//this.getClass():com.web.dao.impl.RwAdminDaoImpl
		//this.getClass().getGenericSuperclass():com.web.dao.impl.CommonDaoImpl<RwAdmin>
		Type  type = this.getClass().getGenericSuperclass();
		if(type instanceof ParameterizedType){
			Type[] tps = ((ParameterizedType)type).getActualTypeArguments();
			entity = (Class<T>) tps[0];
		}
		
	}
	
	/**
	 * 添加
	 * @param obj
	 */
	public void insert(T obj){
//		//1.获取session
//	     Session session = sessionFactory.getCurrentSession();
//	     //2开启事务   Transaction trans =  session.beginTransaction();
//	      //trans.commit();    trans.rollback();省略
//	     session.save(obj);
		
		Session session = null;
		try{
			//1.获取session
			session = sessionFactory.getCurrentSession();
			
			//2.持久化操作
			session.save(obj);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 修改
	 * @param obj
	 */
	public void update(T obj){
		Session session = null;
		try{
			//1.获取session
			session = sessionFactory.getCurrentSession();
			
			//2.持久化操作
			session.update(obj);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 删除
	 * @param obj
	 */
	public void delete(T obj){
		Session session = null;
		try{
			//1.获取session
			session = sessionFactory.getCurrentSession();
			
			//2.持久化操作
			session.delete(obj);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void delete(int id){
		T obj = this.findById(id);
		Session session = null;
		try{
			//1.获取session
			session = sessionFactory.getCurrentSession();
			
			//2.持久化操作
			session.delete(obj);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public T findById(int id){
		Session session = null;
		T obj = null;
		try{
			//1.获取session
			session = sessionFactory.getCurrentSession();
			
			//2.持久化操作
			obj = (T) session.get(entity, id);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return obj;
	}

	
	
}
