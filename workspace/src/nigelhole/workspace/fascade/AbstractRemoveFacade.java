package nigelhole.workspace.fascade;

public abstract class AbstractRemoveFacade<T> extends AbstractModifyFacade<T> {
	public AbstractRemoveFacade(Class<T> entityClass) {
		super(entityClass);
	}

	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

}