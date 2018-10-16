package org.ys.core.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="core_user")
public class CoreUser implements Serializable{
	private static final long serialVersionUID = 888042231706528089L;
	
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name="user_id",length=32)
	private String userId;
	@Column(name="user_name",length=256)
	private String userName;
	@Column(name="password",length=32)
	private String password;
	@Column(name="address",length=256)
	private String address;
	@Column(name="email",length=256)
	private String email;
	@Column(name="age",length=3)
	private int age;
	@Column(name="birthday")
	private Date birthday;
	@Column(name="comment_context",length=1024)
	private String commentContext;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCommentContext() {
		return commentContext;
	}
	public void setCommentContext(String commentContext) {
		this.commentContext = commentContext;
	}
}
