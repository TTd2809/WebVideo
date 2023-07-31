package poly.com.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import poly.com.untils.JpaUtils;

public class AbstractDao<T> {
	public static EntityManager entityManager =JpaUtils.getEntityManager();
	
	@SuppressWarnings("deprecation")
	@Override
	protected void finalize() throws Throwable {
		entityManager.close();
		super.finalize();
	}
	public T findById(Class<T> clazz, Integer id) {
		return entityManager.find(clazz, id);
	}
	
	public List<T> findAll(Class<T> clazz,boolean existIsActive){
		String entityName = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT o FORM ").append(entityName).append(" o");
		sql.append("SELECT u FROM "+entityName+ " u");
		if (existIsActive ==true) {
			sql.append(" WHERE isActive =1");
		}
		TypedQuery<T> query = entityManager.createQuery(sql.toString(),clazz);
		return query.getResultList();
	}
	
	public List<T> findAll(Class<T> clazz,boolean existIsActive,int pageNumber, int pageSize){
		String entityName = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT o FORM ").append(entityName).append(" o");
		if (existIsActive ==true) {
			sql.append(" WHERE o.isActive =1");
		}
		TypedQuery<T> query = entityManager.createQuery(sql.toString(),clazz);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	public T findOne(Class<T> clazz, String sql, Object ... params) {
		TypedQuery<T> query =entityManager.createQuery(sql,clazz);
		for(int i =0; i<params.length;i++) {
			query.setParameter(i, params[i]);
		}
		List<T> result =query.getResultList();
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}
	public List<T> findMany(Class<T> clazz, String sql, Object ... params) {
		TypedQuery<T> query =entityManager.createQuery(sql,clazz);
		for(int i =0; i<params.length;i++) {
			query.setParameter(i, params[i]);
		}
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Object []> findManyByNativeQuery( String sql, Object ... params) {
		Query query =entityManager.createNativeQuery(sql);
		for(int i =0; i<params.length;i++) {
			query.setParameter(i, params[i]);
		}
		return query.getResultList();
	}
	
	public T create(T entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			System.out.println("update succeed");
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			System.out.println("Fail update entity"+entity.getClass().getSimpleName());
			throw new RuntimeException();
		}
	}
	public T upadate(T entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			System.out.println("update succeed");
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			System.out.println("Fail update entity"+entity.getClass().getSimpleName());
			throw new RuntimeException();
		}
	}
	public T delete(T entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
			System.out.println("delete succeed");
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			System.out.println("Fail delete entity"+entity.getClass().getSimpleName());
			throw new RuntimeException();
		}
	}
	@SuppressWarnings("unchecked")
	public List<T> callStored(String namedStored,Map<String, Object> params){
		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(namedStored);
		params.forEach((key, value)	-> query.setParameter(key, value));
		return (List<T>) query.getResultList();
	}
}
