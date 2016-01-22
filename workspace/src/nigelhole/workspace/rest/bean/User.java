package nigelhole.workspace.rest.bean;

public class User {
	
	private long   id;
	private String userName;
	private String firstName;
	private String secondName;
	private String email;
	private String password;
	private String status;
	private String phoneNumber;
	private String mobileNumber;

	public User() {
		
	}
	
	public User(Long id,String userName, String firstName, String secondName,
			String email, String password, String status, String phoneNumber,
			String mobileNumber) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		this.password = password;
		this.status = status;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "User [id="+id+",userName=" + userName + ", firstName=" + firstName
				+ ", secondName=" + secondName + ", email=" + email
				+ ", password=" + password + ", status=" + status
				+ ", phoneNumber=" + phoneNumber + ", mobileNumber="
				+ mobileNumber + "]";
	}

}
