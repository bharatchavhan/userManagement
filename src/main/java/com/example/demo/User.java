package com.example.demo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "User_Info")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userName;
	private String surname;
	private String address;
	private int pincode;
	private Date dateOfBirth;
	private Date joiningDate;
	private boolean isactive = true;

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public User(int userId, String userName, String surname, String address, int pincode, Date dateOfBirth,
			Date joiningDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.surname = surname;
		this.address = address;
		this.pincode = pincode;
		this.dateOfBirth = dateOfBirth;
		this.joiningDate = joiningDate;

	}

}
