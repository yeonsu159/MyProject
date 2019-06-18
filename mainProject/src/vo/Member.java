package vo;

import java.sql.Date;
import java.sql.Timestamp;
/*
Member 테이블 생성 SQL문
CREATE TABLE member(
		id VARCHAR(15) PRIMARY KEY,
		password VARCHAR(15) NOT NULL,
		name VARCHAR(15),
		birthday date,
		gender VARCHAR(5),
		email VARCHAR(30),
		address VARCHAR(60),
		tel VARCHAR(20),
		reg_date TIMESTAMP
);
*/

public class Member {
	
	private String id;
	private String password;
	private String name;
	private Date birthday;
	private String gender;
	private String email;
	private String address;
	private String tel;
	private Timestamp reg_date;
	
	public Member() {
		
	}

	public Member(String id, String password, String name, Date birthday, String gender, String email, String address,
			String tel) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.tel = tel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [id=").append(id).append(", password=").append(password).append(", name=").append(name)
				.append(", birthday=").append(birthday).append(", gender=").append(gender).append(", email=")
				.append(email).append(", address=").append(address).append(", tel=").append(tel).append(", reg_date=")
				.append(reg_date).append("]");
		return builder.toString();
	}

	
	
	
	
}
