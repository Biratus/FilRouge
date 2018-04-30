package fr.dta.formafond.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.formafond.model.PrimeModel;

@Transactional
@Repository
public class PrimeDAO<T extends PrimeModel> {

	private Class<T> klass;
	protected String table;
	
	@PersistenceContext
	protected EntityManager em;
	
	public PrimeDAO() {}

	public PrimeDAO(Class<T> kl) {
		klass = kl;
		table=klass.getName();
	}
	
	public T save(T t) {
		if(t.getId()==0) em.persist(t);
		
		else if(!em.contains(t)) em.merge(t);
		return t;
	}
	
	public T get(long id) {
		T t = em.find(klass, id);		
		return t;
	}
	
	public boolean remove(T t) {
		try {
			em.remove(t);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean remove(long id) {
		return remove(get(id));
	}
	
	public List<T> getAll() {
		TypedQuery<T> query = em.createQuery("from "+table, klass);
		return query.getResultList();
	}
	
}
