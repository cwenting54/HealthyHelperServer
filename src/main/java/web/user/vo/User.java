package web.user.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class User {
	private int userId;
	private String account;
	private String password;
	private String username;
	private String userEmail;
	private String phoneno;
	private Integer gender;
	private Date birthday;
	private Integer roleID;
	private byte[] certificate;
	private Timestamp registrationDate;
	private byte[] userIcon;
	private String cPassword;

	public User() {
	}

	public User(int userId, String account, String password, String username, String userEmail, String phoneno,
			Integer gender, Date birthday, Integer roleID, byte[] certificate, Timestamp registrationDate,
			byte[] userIcon) {
		this.userId = userId;
		this.account = account;
		this.password = password;
		this.username = username;
		this.userEmail = userEmail;
		this.phoneno = phoneno;
		this.gender = gender;
		this.birthday = birthday;
		this.roleID = roleID;
		this.certificate = certificate;
		this.registrationDate = registrationDate;
		this.userIcon = userIcon;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public byte[] getCertificate() {
		return certificate;
	}

	public void setCertificate(byte[] certificate) {
		this.certificate = certificate;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public byte[] getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(byte[] userIcon) {
		this.userIcon = userIcon;
	}

	public String getcPassword() {
		return cPassword;
	}

	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", account=" + account + ", password=" + password + ", username=" + username
				+ ", userEmail=" + userEmail + ", phoneno=" + phoneno + ", gender=" + gender + ", birthday=" + birthday
				+ ", roleID=" + roleID + ", certificate=" + Arrays.toString(certificate) + ", registrationDate="
				+ registrationDate + ", userIcon=" + Arrays.toString(userIcon) + ", cPassword=" + cPassword + "]";
	}
}
