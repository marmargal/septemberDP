package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class ContactSection extends DomainEntity {
	
	// Constructors

	public ContactSection() {
		super();
	}
	
	// Attributes
	
	private String email;
	private String phoneNumber;
	private Integer pageNumber;
	
	@Email
	@NotBlank
	@SafeHtml
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@SafeHtml
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	// Relationships
	
	private Application application;
	
	@Valid
	@ManyToOne
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}

}
