/**
 * 
 */
package com.youtube.dao.custom;

import java.io.Serializable;

/**
 * @author Asanka Siriwardena
 *
 */
public class ContactInfoDTO implements Serializable {
	
	private static final long serialVersionUID = -3269397947626791311L;
	
	private String firstName;
	private String lastName;
	private String telephone;
	private String email;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
