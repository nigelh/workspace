package nigelhole.workspace.fascade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractFacade<T> {

	protected final List<T> emptyList = new ArrayList<T>(0);

	private boolean cached = false;
	
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "workspace" );
    
    EntityManager entityManager = emfactory.createEntityManager( );

	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T find(Object id) {
		return getEntityManager().find(getEntityClass(), id);
	}

	public List<T> findAll() {
		CriteriaQuery<T> criteriaQuery = getEntityManager()
				.getCriteriaBuilder().createQuery(getEntityClass());
		criteriaQuery.select(criteriaQuery.from(getEntityClass()));
		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}

	public List<T> findRange(int start, int end) {
		CriteriaQuery<T> criteriaQuery = getEntityManager()
				.getCriteriaBuilder().createQuery(getEntityClass());
		criteriaQuery.select(criteriaQuery.from(getEntityClass()));
		TypedQuery<T> query = getEntityManager().createQuery(criteriaQuery);
		query.setMaxResults(end - start);
		query.setFirstResult(start);
		return query.getResultList();
	}

	public long count() {
		CriteriaQuery<Long> criteriaQuery = getEntityManager()
				.getCriteriaBuilder().createQuery(Long.class);
		criteriaQuery.select(getEntityManager().getCriteriaBuilder().count(
				criteriaQuery.from(getEntityClass())));
		return getEntityManager().createQuery(criteriaQuery).getSingleResult();
	}

	public void refresh(int id) {
		getEntityManager().refresh(find(id));
	}

	public void refresh(Object entity) {
		getEntityManager().refresh(entity);
	}

	public void flush() {
		getEntityManager().flush();
	}

	public EntityManager getEntityManager() {

		return entityManager;
	}

	public boolean isCached() {
		return cached;
	}

	public void setCached(boolean cached) {
		this.cached = cached;
	}

	public TypedQuery<T> createNamedQuery(String name  ) {
		TypedQuery<T> reply = getEntityManager().createNamedQuery(name, entityClass);
		return reply;
	}

	public Query createNativeQuery(String sql) {
		Query reply = getEntityManager().createNativeQuery(sql);
		return reply;
	}

	public StoredProcedureQuery createNamedStoredProcedureQuery(
			String procedureName) {
		StoredProcedureQuery reply = getEntityManager()
				.createNamedStoredProcedureQuery(procedureName);
		return reply;

	}

}