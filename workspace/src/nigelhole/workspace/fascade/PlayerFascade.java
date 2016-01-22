package nigelhole.workspace.fascade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import nigelhole.workspace.entity.Player;
import nigelhole.workspace.entity.Registration;

public class PlayerFascade extends AbstractRemoveFacade<Player> {
	
	public PlayerFascade() {
		super(Player.class);
		
	}
	
	public List<Player> findTeamPlayers(Long teamId) {
		TypedQuery<Player> query = createNamedQuery("Player.findTeamPlayers");
		query.setParameter("teamId", teamId);
		try {
			return query.getResultList();
		} catch (NoResultException exception) {
			return new ArrayList<Player>(0);
		}
	}
	

}
