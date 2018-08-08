package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {
	
	// Constructors
	
	public Actor(){
		super();
	}
	
	// Attributes
	private String name;
	private String surname;
	private String pictures;
	private String postalAddress;
	private String phoneNumber;
	private String email;
	
	@NotBlank
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@URL
	public String getPictures() {
		return pictures;
	}
	
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	
	
	public String getPostalAddress() {
		return postalAddress;
	}
	
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	
	// TODO: pattern?
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	// Relationships
	private UserAccount userAccount;
	private Collection<Tutorial> tutorials;
	
	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	@Valid
	@OneToMany(mappedBy="actor")
	public Collection<Tutorial> getTutorials() {
		return tutorials;
	}

	public void setTutorials(Collection<Tutorial> tutorials) {
		this.tutorials = tutorials;
	}
}
