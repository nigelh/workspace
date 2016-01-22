package nigelhole.workspace.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Team database table.
 * 
 */
@Entity
@Table(name="Team")
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	

	@Column(length=45)
	private String city;

	@Column(length=45)
	private String competition;

	@Temporal(TemporalType.DATE)
	private Date created;

	@Column(length=45)
	private String name;

	@Column(length=45)
	private String owner;

	//bi-directional many-to-one association to Player
	@OneToMany(targetEntity=Player.class,mappedBy="team",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Player> players;

	public Team() {
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompetition() {
		return this.competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	@Temporal(TemporalType.DATE)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player addPlayer(Player player) {
		getPlayers().add(player);
		player.setTeam(this);

		return player;
	}

	public Player removePlayer(Player player) {
		getPlayers().remove(player);
		player.setTeam(null);

		return player;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}