package com.hgd.ebp.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class User {
	private int uid;
	 @NotEmpty(message="用户名不能为空")
	private String username;
	 @NotEmpty(message="密码不能为空")
	private String password;
	private String name;
	private String gender;
	private String idCard;
	private String address;
	private String telno;
	private Timestamp regTime;
	private double balance;
	private int status;
	private int age;
	//private String isCanUse;
	
	public User(){
		
	}
	
	public User(int uid, String username, String password, String name,
			String gender, String idCard, String address, String telno,
			Timestamp regTime, double balance, int status) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.idCard = idCard;
		this.address = address;
		this.telno = telno;
		this.regTime = regTime;
		this.balance = balance;
		this.status = status;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		int current_year = Integer.parseInt(sdf.format(date));
		String year = idCard.substring(6, 10);
		this.age = current_year - Integer.parseInt(year);
		System.out.println("+++++++++++++++++++"+age);
		//this.isCanUse = (status == 1 ? "禁用" : "启用") ;
	}
	
	public int getAge() {
		return age;
	}
	
	
	public void setAge(int age) {
		this.age = age;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public Timestamp getRegTime() {
		return regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

//	public String getStatus() {
//		return status == 1 ? "活动" : "禁用" ;
//	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return  status;
	}
	
	/*public String getIsCanUse() {
		return isCanUse;
	}*/
	
}
