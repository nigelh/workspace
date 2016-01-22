package nigelhole.workspace.fascade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import nigelhole.workspace.entity.Registration;

public class RegistrationFascade extends AbstractRemoveFacade<Registration> {
	
	public RegistrationFascade() {
		super(Registration.class);
		
	}
	
	public List<Registration> findByEmailPassword(String email,String password) {
		TypedQuery<Registration> query = createNamedQuery("Registration.findByEmailPassword");
		query.setParameter("email", email);
		query.setParameter("password",password);
		try {
			return query.getResultList();
		} catch (NoResultException exception) {
			return new ArrayList<Registration>(0);
		}
	}

}
