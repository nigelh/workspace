package nigelhole.workspace.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Registration database table.
 * 
 */
@Entity
@Table(name="Registration")
@NamedQuery(name="Registration.findByEmailPassword", query="SELECT r FROM Registration r where r.email = :email and r.password = :password")
public class Registration extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(name="date",nullable=false)

	private Date date;

	@Column(nullable=false, length=36)
	private String email;

	@Column(nullable=false, length=36)
	private String firstName;

	@Column(length=1024)
	private String message;

	@Column(nullable=false, length=36)
	private String mobile;

	@Column(nullable=false, length=36)
	private String password;

	@Column(length=36)
	private String phone;

	@Column(nullable=false, length=36)
	private String secondName;

	@Column(length=36)
	private String subject;

	public Registration() {
		super();
	}

	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSecondName() {
		return this.secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}