package nigelhole.workspace.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the Players database table.
 * 
 */
@Entity
@Table(name="Players")

public class Player  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	

	@Column(length=45)
	private String name;

	@Column(length=45)
	private String number;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="TeamId", referencedColumnName="Id")
	@JsonIgnore
	private Team team;

	public Player() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}