package nigelhole.workspace.fascade;

public abstract class AbstractModifyFacade<T> extends AbstractFacade<T> {
	public AbstractModifyFacade(Class<T> entityClass) {
		super(entityClass);
	}

	public void create(T entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
	}

	public void edit(T entity) {
		getEntityManager().merge(entity);
	}

	public void begin() {
		getEntityManager().getTransaction().begin();
	}

	public void commit() {
		getEntityManager().getTransaction().commit();
	}

	public void rollback() {
		getEntityManager().getTransaction().rollback();
	}
}